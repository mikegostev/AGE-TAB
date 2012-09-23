package uk.ac.ebi.age.model.impl.v1;

import java.io.Serializable;
import java.lang.ref.Reference;

import uk.ac.ebi.age.model.AgeClass;
import uk.ac.ebi.age.model.AgeRelation;
import uk.ac.ebi.age.model.AgeRelationClass;
import uk.ac.ebi.age.model.AttributedClass;
import uk.ac.ebi.age.model.ContextSemanticModel;
import uk.ac.ebi.age.model.RelationClassRef;
import uk.ac.ebi.age.model.ResolveScope;
import uk.ac.ebi.age.model.writable.AgeExternalRelationWritable;
import uk.ac.ebi.age.model.writable.AgeObjectWritable;
import uk.ac.ebi.age.model.writable.AgeRelationWritable;
import uk.ac.ebi.age.model.writable.DataModuleWritable;
import uk.ac.ebi.age.util.ReferenceFactory;

public class AgeExternalRelationImpl extends AttributedObject implements AgeExternalRelationWritable, Serializable
{
 private static final long serialVersionUID = 1L;

 private final RelationClassRef relClassRef; 
 private String objId;
 private AgeObjectWritable sourceObject;
 private transient Reference<AgeExternalRelationWritable> invRelation;
 private transient Reference<AgeObjectWritable> target;
 private boolean infered;
 private ResolveScope tgtScope;

 protected AgeExternalRelationImpl(RelationClassRef cRef, AgeObjectWritable srcOb, String id, ResolveScope scp)
 {
  relClassRef=cRef;

  objId=id;
  sourceObject=srcOb;
  tgtScope=scp;
 }

 @Override
 public AgeRelationClass getAgeElClass()
 {
  AgeRelationClass cls = relClassRef.getAgeElClass();
  
  if( cls == null )
   if( ! relClassRef.getPlug().plug( getSourceObject().getDataModule().getResolver() ) )
    return null;
  
  return relClassRef.getAgeElClass();
 }
 
 @Override
 public RelationClassRef getClassReference()
 {
  return relClassRef;
 }

 @Override
 public AgeObjectWritable getTargetObject()
 {
  
  AgeObjectWritable tgt = null;
  
  if( target != null )
  {
   tgt = target.get();
   
   if( tgt != null )
    return tgt;
  }
  
  DataModuleWritable dm = getSourceObject().getDataModule();

  if( getTargetResolveScope() != ResolveScope.GLOBAL || getTargetResolveScope() != ResolveScope.GLOBAL_FALLBACK )
   tgt = dm.getResolver().getClusterScopeObject(objId, dm.getClusterId());

  if( tgt == null && getTargetResolveScope() == ResolveScope.CLUSTER )
   return null;
  
  tgt = dm.getResolver().getGlobalScopeObject(objId);
  
  if( getTargetResolveScope() == ResolveScope.GLOBAL_FALLBACK )
  {
   if( tgt != null )
   {
    
    if( getAgeElClass().getDomain() != null )
    {
     boolean found = false;
     for( AgeClass dc : getAgeElClass().getDomain() )
     {
      if( tgt.getAgeElClass().isClassOrSubclassOf(dc) )
      {
       found = true;
       break;
      }
     }
     
     if( ! found )
      tgt = null;
    }
   }
   
   if( tgt == null )
    tgt = dm.getResolver().getClusterScopeObject(objId, dm.getClusterId());
 
  }
  
  if( tgt != null )
   target = ReferenceFactory.getReference(tgt);
  
  return tgt;
 }
 
 @Override
 public AgeObjectWritable getSourceObject()
 {
  return sourceObject;
 }

 
 @Override
 public String getTargetObjectId()
 {
  return objId;
 }

 @Override
 public int getOrder()
 {
  return relClassRef.getOrder();
 }


 @Override
 public void setTargetObject(AgeObjectWritable obj)
 {
  objId=obj.getId();
  target = ReferenceFactory.getReference(obj);
 }


 @Override
 public void setInferred(boolean inf)
 {
  infered=inf;
 }

 @Override
 public boolean isInferred()
 {
  return infered;
 }
 

 @Override
 public AttributedClass getAttributedClass()
 {
  return getAgeElClass();
 }
 
 @Override
 public AgeRelationWritable createClone( AgeObjectWritable src )
 {
  AgeExternalRelationImpl clone = new AgeExternalRelationImpl(relClassRef, src, getTargetObjectId(), tgtScope);
  clone.infered = infered;
  
  cloneAttributes(clone);
  
  return clone;
 }

 @Override
 public AgeExternalRelationWritable getInverseRelation()
 {
  AgeExternalRelationWritable rel = null;
  
  if( invRelation != null )
  {
   rel = invRelation.get();
   
   if( rel != null)
    return rel;
  }
  
  for( AgeRelation r : getTargetObject().getRelations()  )
  {
   // Comparing object ID before actual object comparing to minimize unnecessary module loading 
   if( r.getAgeElClass().getInverseRelationClass() == getAgeElClass() && r.getTargetObjectId().equals(getSourceObject().getId() ) && r.getTargetObject() == getSourceObject() )
   {
    rel = (AgeExternalRelationWritable)r;
    break;
   }
  }
  
  if( rel == null )
   return null;
  
  invRelation = ReferenceFactory.getReference(rel);
  
  return rel;
 }

 @Override
 public void setInverseRelation(AgeRelationWritable invRl)
 {
  throw new UnsupportedOperationException();
 }

 @Override
 public void setInverseRelation(AgeExternalRelationWritable invr)
 {
  invRelation= ReferenceFactory.getReference(invr);
 }

 @Override
 public void setSourceObject(AgeObjectWritable ageObject)
 {
  sourceObject=ageObject;
 }

 @Override
 public ContextSemanticModel getSemanticModel()
 {
  return sourceObject.getSemanticModel();
 }

 @Override
 public ResolveScope getTargetResolveScope()
 {
  return tgtScope;
 }


 @Override
 public void setTargetResolveScope(ResolveScope scp)
 {
  tgtScope = scp;
 }
}

