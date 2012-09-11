package uk.ac.ebi.age.model.impl.v1;

import java.io.Serializable;
import java.lang.ref.Reference;

import uk.ac.ebi.age.AgeResolver;
import uk.ac.ebi.age.model.AgeRelationClass;
import uk.ac.ebi.age.model.AgeRelationClassPlug;
import uk.ac.ebi.age.model.ModuleKey;
import uk.ac.ebi.age.model.Plug;
import uk.ac.ebi.age.util.ReferenceFactory;

public class AgeRelationInverseCustomClassPlug implements Serializable, Plug, AgeRelationClassPlug
{
 private static final long serialVersionUID = 1L;
 
 private final String className;
 private transient Reference<AgeRelationClass> ageRelationClass;
 private final ModuleKey modKey;
 
 public AgeRelationInverseCustomClassPlug(AgeRelationClass relClass, ModuleKey mk)
 {
  ageRelationClass=ReferenceFactory.getReference( relClass );

  className = relClass.getInverseRelationClass().getName();
  
  modKey = mk;
 }

 @Override
 public void unplug()
 {
  ageRelationClass = null;
 }
 
 @Override
 public boolean plug( AgeResolver rslv )
 {
  AgeRelationClass invCls = rslv.getCustomAgeRelationClass(className, modKey);
  
  if( invCls != null )
   return false;
  
  ageRelationClass = ReferenceFactory.getReference( invCls.getInverseRelationClass() );
  
  return true;
 }
 
 @Override
 public AgeRelationClass getAgeRelationClass()
 {
  if( ageRelationClass == null )
   return null;
  
  return ageRelationClass.get();
 }

 @Override
 public boolean isPlugged()
 {
  return ageRelationClass!=null && ageRelationClass.get() != null ;
 }
}
