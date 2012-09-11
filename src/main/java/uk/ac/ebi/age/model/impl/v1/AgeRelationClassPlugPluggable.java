package uk.ac.ebi.age.model.impl.v1;

import java.io.Serializable;

import uk.ac.ebi.age.AgeResolver;
import uk.ac.ebi.age.model.AgeRelationClass;
import uk.ac.ebi.age.model.AgeRelationClassPlug;
import uk.ac.ebi.age.model.Plug;

public class AgeRelationClassPlugPluggable implements Serializable, Plug, AgeRelationClassPlug
{
 private static final long serialVersionUID = 1L;
 
 private final String className;
 private transient AgeRelationClass ageRelationClass;
 
 public AgeRelationClassPlugPluggable(AgeRelationClass relClass)
 {
  ageRelationClass=relClass;
  className = relClass.getName();
 }

 @Override
 public void unplug()
 {
  ageRelationClass = null;
 }
 
 @Override
 public boolean plug( AgeResolver rslv )
 {
  ageRelationClass = rslv.getDefinedAgeRelationClass(className);
  
  if( ageRelationClass != null )
   return true;
  
  return false;
 }
 
 @Override
 public AgeRelationClass getAgeRelationClass()
 {
  return ageRelationClass;
 }

 @Override
 public boolean isPlugged()
 {
  return ageRelationClass!=null;
 }
}
