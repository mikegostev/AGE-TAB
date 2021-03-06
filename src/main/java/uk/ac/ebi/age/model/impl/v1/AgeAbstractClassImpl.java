package uk.ac.ebi.age.model.impl.v1;

import java.util.ArrayList;
import java.util.Collection;

import uk.ac.ebi.age.model.AgeAbstractClass;
import uk.ac.ebi.age.model.AgeAnnotation;
import uk.ac.ebi.age.model.AttributeAttachmentRule;
import uk.ac.ebi.age.model.SemanticModel;
import uk.ac.ebi.age.util.Collector;

import com.pri.util.collection.CollectionsUnion;

abstract class AgeAbstractClassImpl extends AgeSemanticElementImpl implements  AgeAbstractClass
{
 private static final long serialVersionUID = 1L;

 private Collection<AgeAnnotation> annotations;
 
 private Collection<AttributeAttachmentRule> atatRules;



 public AgeAbstractClassImpl( SemanticModel model)
 {
  super(model);
 }



 @Override
 public boolean isClassOrSubclassOf( AgeAbstractClass cl )
 {
  if( cl.equals(this) && cl.isCustom() == isCustom()  && cl.getSemanticModel() == getSemanticModel() )
   return true;
  
  if( getSuperClasses() == null )
   return false;
  
  for( AgeAbstractClass supcls : getSuperClasses() )
   if( supcls.isClassOrSubclassOf(cl) )
    return true;
  
  return false;
 }


 @Override
 public Collection<AgeAnnotation> getAnnotations()
 {
  return annotations;
 }


 public void addAnnotation(AgeAnnotation annt)
 {
  if( annotations == null )
   annotations = new ArrayList<AgeAnnotation>(8);
  
  annotations.add(annt);
 }
 
 public void removeAnnotation(AgeAnnotation annt)
 {
  annotations.remove(annt);
 }
 
 @Override
 public Collection<AttributeAttachmentRule> getAttributeAttachmentRules()
 {
  return atatRules;
 }
 
 public void addAttributeAttachmentRule(AttributeAttachmentRule atatRule)
 {
  if( atatRules == null )
   atatRules = new ArrayList<AttributeAttachmentRule>();
  
  atatRules.add(atatRule);
 }
 
 @Override
 public Collection<AttributeAttachmentRule> getAllAttributeAttachmentRules()
 {
  Collection<Collection<AttributeAttachmentRule>> allRest = new ArrayList<Collection<AttributeAttachmentRule>>(10);
  
  Collector.collectFromHierarchy(this,allRest, new Collector<Collection<AttributeAttachmentRule>>(){

   @Override
   public Collection<AttributeAttachmentRule> get(AgeAbstractClass cls)
   {
    Collection<AttributeAttachmentRule> restr = cls.getAttributeAttachmentRules();
    return restr==null||restr.size()==0?null:restr;
   }} );
  
  return new CollectionsUnion<AttributeAttachmentRule>(allRest);
 }
 

 @Override
 public String toString()
 {
  if( isCustom() )
   return "{"+getName()+"}";
  
  return getName();
 }
}
