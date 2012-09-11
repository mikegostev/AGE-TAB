package uk.ac.ebi.age.model.impl.v1;

import java.io.Serializable;

import uk.ac.ebi.age.AgeResolver;
import uk.ac.ebi.age.model.AgeRelationClass;
import uk.ac.ebi.age.model.AgeRelationClassPlug;
import uk.ac.ebi.age.model.Plug;

public class AgeRelationInverseClassPlug implements Serializable, Plug, AgeRelationClassPlug
{
 private static final long serialVersionUID = 1L;
 
 private final String className;
 private transient AgeRelationClass ageRelationClass;
 
 public AgeRelationInverseClassPlug(AgeRelationClass relClass)
 {
  ageRelationClass=relClass;

  className = relClass.getInverseRelationClass().getName();
 }

 @Override
 public void unplug()
 {
  ageRelationClass = null;
 }
 
 @Override
 public boolean plug( AgeResolver rslv )
 {
  AgeRelationClass invCls = rslv.getDefinedAgeRelationClass(className);
  
  if( invCls == null )
   return false;

  ageRelationClass = invCls.getInverseRelationClass();
  
  return true;
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
