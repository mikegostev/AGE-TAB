package uk.ac.ebi.age.model.impl.v1;

import java.io.Serializable;

import uk.ac.ebi.age.AgeResolver;
import uk.ac.ebi.age.model.AgeAttributeClass;
import uk.ac.ebi.age.model.AgeAttributeClassPlug;
import uk.ac.ebi.age.model.Plug;

class AgeAttributeClassPlugPluggable implements Plug, Serializable, AgeAttributeClassPlug
{
 private static final long serialVersionUID = 1L;

 private final String className;
 private transient AgeAttributeClass ageAttributeClass = null;
 
 public AgeAttributeClassPlugPluggable(AgeAttributeClass attrClass)
 {
  ageAttributeClass=attrClass;
  className = attrClass.getName();
 }

 @Override
 public void unplug()
 {
  ageAttributeClass = null;
 }
 
 @Override
 public boolean plug( AgeResolver rslv )
 {
  ageAttributeClass = rslv.getDefinedAgeAttributeClass(className);
  
  if( ageAttributeClass != null )
   return true;
  
  return false;
 }
 
 @Override
 public AgeAttributeClass getAgeAttributeClass()
 {
  return ageAttributeClass;
 }

 @Override
 public boolean isPlugged()
 {
  return ageAttributeClass!=null;
 }
}
