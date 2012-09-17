package uk.ac.ebi.age.model.impl.v1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import uk.ac.ebi.age.AgeResolver;
import uk.ac.ebi.age.model.AgeAbstractClass;
import uk.ac.ebi.age.model.AgeAnnotation;
import uk.ac.ebi.age.model.AgeClass;
import uk.ac.ebi.age.model.AgeClassPlug;
import uk.ac.ebi.age.model.AgeContextSemanticElement;
import uk.ac.ebi.age.model.AgeCustomRelationClass;
import uk.ac.ebi.age.model.AgeRelationClass;
import uk.ac.ebi.age.model.AgeRelationClassPlug;
import uk.ac.ebi.age.model.AttributeAttachmentRule;
import uk.ac.ebi.age.model.ContextSemanticModel;
import uk.ac.ebi.age.model.writable.AgeRelationClassWritable;

import com.pri.util.Extractor;
import com.pri.util.collection.ExtractorCollection;

class CustomAgeRelationClassImpl implements AgeRelationClassWritable, AgeContextSemanticElement, Serializable, AgeCustomRelationClass
{
 private static final long serialVersionUID = 3L;
 
 private static Extractor<AgeRelationClassPlug, AgeRelationClass> clsExtr = new Extractor<AgeRelationClassPlug, AgeRelationClass>()
   {

    @Override
    public AgeRelationClass extract(AgeRelationClassPlug obj)
    {
     return obj.getAgeRelationClass();
    }
   };

 
 private final String name;
 private final AgeClassPlug ownerClass;
 private final AgeClassPlug rangeClass;
 private CustomAgeRelationClassImpl inverse;
 private boolean implicit = false;
 private final ContextSemanticModel model;
 
 private Collection<AgeRelationClassPlug> superClassPlugs;

 public CustomAgeRelationClassImpl(String name, ContextSemanticModel sm, AgeClass range, AgeClass owner)
 {
  this( name, sm, range, owner, null);
 }

 private CustomAgeRelationClassImpl(String name, ContextSemanticModel sm, AgeClass range, AgeClass owner, CustomAgeRelationClassImpl inv)
 {
  model = sm;
  
  this.name=name;
  
  rangeClass = sm.getAgeClassPlug(range);

  ownerClass = sm.getAgeClassPlug(owner);
  
  if( inv == null )
  {
   inverse = new CustomAgeRelationClassImpl( "!"+name, sm, range, owner, this );
   inverse.setImplicit(true);
  }
  else
   inverse = inv;
 }

 protected void setInverseClass( CustomAgeRelationClassImpl inv )
 {
  inverse = inv;
 }
 
 @Override
 public AgeRelationClass getInverseRelationClass()
 {
  return inverse;
 }
 
 @Override
 public String getName()
 {
  return name;
 }

 @Override
 public Collection<AgeClass> getDomain()
 {
  return Collections.singletonList(ownerClass.getAgeClass());
 }


 @Override
 public Collection<AgeClass> getRange()
 {
  return Collections.singletonList(rangeClass.getAgeClass());
 }
 
 @Override
 public boolean isCustom()
 {
  return true;
 }

 @Override
 public boolean isWithinDomain(AgeClass key)
 {
  return key.equals(ownerClass.getAgeClass());
 }

 @Override
 public boolean isWithinRange(AgeClass key)
 {
  return key.isClassOrSubclassOf(rangeClass.getAgeClass());
 }

 @Override
 public Collection<AgeRelationClass> getSubClasses()
 {
  return null;
 }

 @Override
 public Collection<AgeRelationClass> getSuperClasses()
 {
  if( superClassPlugs == null )
   return null;
  
  return new ExtractorCollection<AgeRelationClassPlug, AgeRelationClass>(superClassPlugs, clsExtr );
 }

 @Override
 public boolean isClassOrSubclassOf(AgeAbstractClass cl)
 {
  if( cl.equals(this) && cl.isCustom() == isCustom() )
   return true;
  
  if( superClassPlugs == null )
   return false;
  
  for( AgeRelationClassPlug supclsp : superClassPlugs )
   if( supclsp.getAgeRelationClass().isClassOrSubclassOf(cl) )
    return true;
  
  return false;
 }

 @Override
 public boolean isImplicit()
 {
  return implicit;
 }
 
 @Override
 public void setImplicit( boolean b )
 {
  implicit=b;
 }

 @Override
 public void resetModel()
 {
  ownerClass.unplug();
  rangeClass.unplug();
  inverse.resetModel();
 }


 @Override
 public boolean isAbstract()
 {
  return false;
 }

 @Override
 public Collection<AgeAnnotation> getAnnotations()
 {
  return Collections.emptyList();
 }

 @Override
 public boolean isFunctional()
 {
  return false;
 }

 @Override
 public boolean isInverseFunctional()
 {
  return false;
 }

 @Override
 public boolean isSymmetric()
 {
  return false;
 }

 @Override
 public boolean isTransitive()
 {
  return false;
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
 public void addAnnotation(AgeAnnotation ant)
 {
  throw new UnsupportedOperationException();
 }

 @Override
 public void addAttributeAttachmentRule(AttributeAttachmentRule atatRule)
 {
  throw new UnsupportedOperationException();
 }

 @Override
 public void addSubClass(AgeRelationClassWritable makeRelationsBranch)
 {
  throw new UnsupportedOperationException();
 }

 @Override
 public void addSuperClass(AgeRelationClassWritable supcls)
 {
  if( superClassPlugs == null )
   superClassPlugs = new ArrayList<AgeRelationClassPlug>(4);
  
  superClassPlugs.add( getSemanticModel().getAgeRelationClassPlug(supcls) );
 }

 @Override
 public void addDomainClass(AgeClass dmCls)
 {
  throw new UnsupportedOperationException();
 }

 @Override
 public void addRangeClass(AgeClass dmCls)
 {
  throw new UnsupportedOperationException();
 }

 @Override
 public void setInverseRelationClass(AgeRelationClass ageEl)
 {
  throw new UnsupportedOperationException();
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
 public void setFunctional(boolean functional)
 {
  throw new UnsupportedOperationException();
 }

 @Override
 public void setInverseFunctional(boolean inverseFunctional)
 {
  throw new UnsupportedOperationException();
 }

 @Override
 public void setSymmetric(boolean symmetric)
 {
  throw new UnsupportedOperationException();
 }

 @Override
 public void setTransitive(boolean transitive)
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
 public AgeRelationClass getAgeRelationClass()
 {
  return this;
 }

}
