package uk.ac.ebi.age.model.impl.v1;

import java.io.Serializable;

import uk.ac.ebi.age.model.AgeClass;
import uk.ac.ebi.age.model.AgeClassPlug;
import uk.ac.ebi.age.model.ContextSemanticModel;

public class ClassRefImpl implements uk.ac.ebi.age.model.ClassRef, Serializable
{
 private static final long serialVersionUID = 3L;

 private final int order;
 private final String heading;
 private final AgeClassPlug classPlug;
 private final boolean horizontal;
 private final ContextSemanticModel model;

 public ClassRefImpl(AgeClassPlug cp, int order, String headingl, boolean horiz, ContextSemanticModel md )
 {
  super();
  this.order = order;
  this.heading = headingl;
  this.classPlug = cp;
  horizontal = horiz;
  model = md;
 }


 @Override
 public int getOrder()
 {
  return order;
 }

 @Override
 public String getHeading()
 {
  return heading;
 }

 @Override
 public AgeClass getAgeClass()
 {
  return classPlug.getAgeClass();
 }

 @Override
 public boolean isHorizontal()
 {
  return horizontal;
 }


 @Override
 public ContextSemanticModel getSemanticModel()
 {
  return model;
 }


 @Override
 public AgeClassPlug getPlug()
 {
  return classPlug;
 }

}
