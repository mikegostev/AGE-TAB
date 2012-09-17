package uk.ac.ebi.age.model.impl.v1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import uk.ac.ebi.age.AgeResolver;
import uk.ac.ebi.age.model.AgeAbstractClass;
import uk.ac.ebi.age.model.AgeAnnotation;
import uk.ac.ebi.age.model.AgeClass;
import uk.ac.ebi.age.model.AgeClassPlug;
import uk.ac.ebi.age.model.AgeContextSemanticElement;
import uk.ac.ebi.age.model.AgeCustomClass;
import uk.ac.ebi.age.model.AttributeAttachmentRule;
import uk.ac.ebi.age.model.ContextSemanticModel;
import uk.ac.ebi.age.model.RelationRule;
import uk.ac.ebi.age.model.writable.AgeClassWritable;

import com.pri.util.Extractor;
import com.pri.util.collection.ExtractorCollection;

class CustomAgeClassImpl implements AgeClassWritable, Serializable, AgeContextSemanticElement, AgeCustomClass
{
 private static final long serialVersionUID = 3L;
 
 private static Extractor<AgeClassPlug, AgeClass> clsExtr = new Extractor<AgeClassPlug, AgeClass>()
   {

    @Override
    public AgeClass extract(AgeClassPlug obj)
    {
     return obj.getAgeClass();
    }
   };

 
 private final ContextSemanticModel model;

 private final String name;
 
 private String idPrefix;
 private Collection<AgeClassPlug> superClassPlugs;

 private Collection<AgeAnnotation> annotations;


 public CustomAgeClassImpl(String name, String pfx, ContextSemanticModel sm)
 {
  model=sm;
  
  this.name=name;
  
  if( pfx == null )
   idPrefix = name.substring(0,1);
  else
   idPrefix=pfx;
  
 }

 @Override
 public Collection<AgeClass> getSuperClasses()
 {
  if( superClassPlugs == null )
   return null;
  
  return new ExtractorCollection<AgeClassPlug, AgeClass>(superClassPlugs, clsExtr );
 }
 
 @Override
 public Collection<AgeClass> getSubClasses()
 {
  return null;
 }

 @Override
 public String getName()
 {
  return name;
 }

 @Override
 public boolean isCustom()
 {
  return true;
 }


 @Override
 public String getIdPrefix()
 {
  return idPrefix;
 }

 @Override
 public boolean isAbstract()
 {
  return false;
 }

 @Override
 public Collection<RelationRule> getRelationRules()
 {
  return null;
 }

 @Override
 public Collection<RelationRule> getAllRelationRules()
 {
  return null;
 }

 @Override
 public Collection<AttributeAttachmentRule> getAttributeAttachmentRules()
 {
  return null;
 }

 @Override
 public Collection<AttributeAttachmentRule> getAllAttributeAttachmentRules()
 {
  return null;
 }


 @Override
 public void addSubClass(AgeClassWritable cls)
 {
  throw new UnsupportedOperationException();
 }
 
 @Override
 public void addSuperClass(AgeClassWritable supcls)
 {
  if( superClassPlugs == null )
   superClassPlugs = new ArrayList<AgeClassPlug>(4);
  
  superClassPlugs.add( getSemanticModel().getAgeClassPlug(supcls) );
 }


 @Override
 public void setAbstract(boolean b)
 {
  throw new UnsupportedOperationException();
 }

 @Override
 public void addAlias(String ali)
 {
  throw new UnsupportedOperationException();
 }

 @Override
 public void addRelationRule(RelationRule mrr)
 {
  throw new UnsupportedOperationException();
 }

 @Override
 public Collection<String> getAliases()
 {
  return null;
 }

 @Override
 public ContextSemanticModel getSemanticModel()
 {
  return model;
 }

 @Override
 public boolean isClassOrSubclassOf(AgeAbstractClass cl)
 {
  if( cl.equals(this) && cl.isCustom() == isCustom() )
   return true;
  
  if( superClassPlugs == null )
   return false;
  
  for( AgeClassPlug supclsp : superClassPlugs )
   if( supclsp.getAgeClass().isClassOrSubclassOf(cl) )
    return true;
  
  return false;
 }

 @Override
 public Collection<AgeAnnotation> getAnnotations()
 {
  return annotations;
 }

 @Override
 public void addAnnotation(AgeAnnotation annt)
 {
  if( annotations == null )
   annotations = new ArrayList<AgeAnnotation>(8);
  
  annotations.add(annt);
 }

 @Override
 public void addAttributeAttachmentRule(AttributeAttachmentRule atatRule)
 {
  throw new UnsupportedOperationException();
 }

 @Override
 public void unplug()
 {
 }

 @Override
 public boolean plug( AgeResolver rslv )
 {
  return true;
 }

 @Override
 public boolean isPlugged()
 {
  return true;
 }

 @Override
 public AgeClass getAgeClass()
 {
  return this;
 }

}
