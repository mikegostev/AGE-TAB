package uk.ac.ebi.age.model.impl.v1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import uk.ac.ebi.age.model.AgeAnnotationClass;
import uk.ac.ebi.age.model.SemanticModel;
import uk.ac.ebi.age.model.writable.AgeAnnotationClassWritable;

class AgeAnnotationClassImpl extends AgeAbstractClassImpl implements AgeAnnotationClassWritable, Serializable
{
 private static final long serialVersionUID = 1L;
 
 private String name;

 private boolean isAbstract;



 private final Collection<AgeAnnotationClass> subClasses = new HashSet<AgeAnnotationClass>();
 private final Collection<AgeAnnotationClass> superClasses = new HashSet<AgeAnnotationClass>();

 private Collection<String> aliases;

 protected AgeAnnotationClassImpl()
 {
  super(null);
 }
 
 public AgeAnnotationClassImpl(String name, SemanticModel sm)
 {
  super(sm);
  this.name=name;
 }

// public AgeAttribute createAttribute()
// {
//  getSemanticModel().
//  throw new dev.NotImplementedYetException();
// }

// public boolean validateValue(String val)
// {
//  // TODO Auto-generated method stub
//  throw new dev.NotImplementedYetException();
// }

 @Override
 public String getName()
 {
  return name;
 }
 
 @Override
 public void addSuperClass( AgeAnnotationClassWritable spCls )
 {
  if( superClasses.add(spCls) )
   spCls.addSubClass(this);
 }

 @Override
 public void addSubClass( AgeAnnotationClassWritable sbCls )
 {
  if( subClasses.add(sbCls) )
   sbCls.addSuperClass(this);
 }

 @Override 
 public Collection<AgeAnnotationClass> getSuperClasses()
 {
  return superClasses;
 }
 
 @Override
 public Collection<AgeAnnotationClass> getSubClasses()
 {
  return subClasses;
 }

 @Override
 public boolean isCustom()
 {
  return false;
 }

 @Override
 public boolean isAbstract()
 {
  return isAbstract;
 }

 @Override
 public void setAbstract(boolean isAbstract)
 {
  this.isAbstract = isAbstract;
 }

 @Override
 public void addAlias(String ali)
 {
  if( aliases == null )
   aliases = new ArrayList<String>( 5 );
  
  aliases.add(ali);
 }

 @Override
 public Collection<String> getAliases()
 {
  return aliases;
 }
 
}

