package uk.ac.ebi.age.model.impl.v1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import uk.ac.ebi.age.model.AgeAttributeClass;
import uk.ac.ebi.age.model.AgeClass;
import uk.ac.ebi.age.model.DataType;
import uk.ac.ebi.age.model.SemanticModel;
import uk.ac.ebi.age.model.writable.AgeAttributeClassWritable;

class AgeAttributeClassImpl extends AgeAbstractClassImpl implements AgeAttributeClassWritable, Serializable
{
 private static final long serialVersionUID = 1L;
 
 private DataType dataType;
 private String name;

 private boolean isAbstract;

 private Collection<String> aliases;
 
 private AgeClass targetClass;

 private final Collection<AgeAttributeClass> subClasses = new HashSet<AgeAttributeClass>();
 private final Collection<AgeAttributeClass> superClasses = new HashSet<AgeAttributeClass>();
// private Collection<AgeRestriction> attributeRestrictions = new LinkedList<AgeRestriction>();

 protected AgeAttributeClassImpl()
 {
  super(null);
 }
 
 public AgeAttributeClassImpl(String name, DataType type, SemanticModel sm)
 {
  super(sm);
  dataType=type;
  this.name=name;
 }

 @Override
 public DataType getDataType()
 {
  return dataType;
 }

 @Override
 public void setDataType(DataType dataType)
 {
  this.dataType = dataType;
 }

 @Override
 public String getName()
 {
  return name;
 }
 
 @Override
 public void addSuperClass( AgeAttributeClassWritable cl )
 {
  if( superClasses.add(cl) )
   cl.addSubClass(this);
 }

 @Override
 public void addSubClass( AgeAttributeClassWritable cl )
 {
  if( subClasses.add(cl) )
   cl.addSuperClass(this);
 }

 
 @Override
 public Collection<AgeAttributeClass> getSuperClasses()
 {
  return superClasses;
 }
 
 
 @Override
 public Collection<AgeAttributeClass> getSubClasses()
 {
  return subClasses;
 }

 @Override
 public AgeClass getOwningClass()
 {
  return null;
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

 @Override
 public AgeClass getTargetClass()
 {
  return targetClass;
 }

 @Override
 public void setTargetClass(AgeClass targetClass)
 {
  this.targetClass = targetClass;
 }

}

