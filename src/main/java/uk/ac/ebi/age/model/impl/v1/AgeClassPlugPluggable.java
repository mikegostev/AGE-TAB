package uk.ac.ebi.age.model.impl.v1;

import java.io.Serializable;

import uk.ac.ebi.age.AgeResolver;
import uk.ac.ebi.age.model.AgeClass;
import uk.ac.ebi.age.model.AgeClassPlug;
import uk.ac.ebi.age.model.Plug;

class AgeClassPlugPluggable implements Serializable, Plug, AgeClassPlug
{
 private static final long serialVersionUID = 1L;

 private final String className;
 private transient AgeClass ageClass;
 
 public AgeClassPlugPluggable(AgeClass cls)
 {
  className = cls.getName();
  ageClass = cls;
 }

 @Override
 public void unplug()
 {
  ageClass = null;
 }
 
 @Override
 public boolean plug( AgeResolver rslv )
 {
  ageClass = rslv.getDefinedAgeClass(className);
  
  if( ageClass != null )
   return true;
  
  return false;
 }
 
 @Override
 public AgeClass getAgeClass()
 {
  return ageClass;
 }

 @Override
 public boolean isPlugged()
 {
  return ageClass!=null;
 }
}
