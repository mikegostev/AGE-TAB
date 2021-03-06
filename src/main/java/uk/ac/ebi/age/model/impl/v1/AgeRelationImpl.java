package uk.ac.ebi.age.model.impl.v1;

import java.io.Serializable;

import uk.ac.ebi.age.model.AgeRelationClass;
import uk.ac.ebi.age.model.AttributedClass;
import uk.ac.ebi.age.model.ContextSemanticModel;
import uk.ac.ebi.age.model.RelationClassRef;
import uk.ac.ebi.age.model.writable.AgeObjectWritable;
import uk.ac.ebi.age.model.writable.AgeRelationWritable;

public class AgeRelationImpl extends AttributedObject implements AgeRelationWritable, Serializable
{
 private static final long serialVersionUID = 4L;
 
 private final RelationClassRef relClassRef;
 private final AgeObjectWritable source;
 private final AgeObjectWritable target;
 private AgeRelationWritable invRelation;
 private boolean inferred=false;
 
 protected AgeRelationImpl(RelationClassRef cref, AgeObjectWritable sourceObj, AgeObjectWritable targetObj)
 {
  relClassRef= cref;
  source=sourceObj;
  target=targetObj;
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
  return target;
 }

 @Override
 public AgeObjectWritable getSourceObject()
 {
  return source;
 }

 @Override
 public int getOrder()
 {
  return relClassRef.getOrder();
 }

 @Override
 public void setInferred( boolean inf )
 {
  inferred = inf;
 }

 
 @Override
 public boolean isInferred()
 {
  return inferred;
 }


 @Override
 public AttributedClass getAttributedClass()
 {
  return getAgeElClass();
 }

 @Override
 public AgeRelationWritable createClone( AgeObjectWritable src )
 {
  AgeRelationImpl clone = new AgeRelationImpl(relClassRef, src, getTargetObject());
  
  cloneAttributes(clone);
  clone.setInferred( isInferred() );
  
  return clone;
 }

 @Override
 public AgeRelationWritable getInverseRelation()
 {
  return invRelation;
 }

 @Override
 public void setInverseRelation(AgeRelationWritable invRl)
 {
  invRelation = invRl;
 }

 @Override
 public ContextSemanticModel getSemanticModel()
 {
  return getSourceObject().getSemanticModel();
 }

 @Override
 public String getTargetObjectId()
 {
  return getTargetObject().getId();
 }
}
