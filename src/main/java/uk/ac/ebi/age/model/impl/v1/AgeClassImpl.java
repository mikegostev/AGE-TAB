package uk.ac.ebi.age.model.impl.v1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import uk.ac.ebi.age.model.AgeAbstractClass;
import uk.ac.ebi.age.model.AgeClass;
import uk.ac.ebi.age.model.RelationRule;
import uk.ac.ebi.age.model.SemanticModel;
import uk.ac.ebi.age.model.writable.AgeClassWritable;
import uk.ac.ebi.age.util.Collector;

import com.pri.util.collection.CollectionsUnion;

class AgeClassImpl extends AgeAbstractClassImpl implements AgeClassWritable, Serializable 
{
 private static final long serialVersionUID = 1L;

 private final String name;
 private String idPrefix;

 private boolean isAbstract;
 
 private Collection<String> aliases;

 private final Collection<AgeClass> subClasses = new HashSet<AgeClass>();
 private final Collection<AgeClass> superClasses = new HashSet<AgeClass>();

 private Collection<RelationRule> relationRules;
 
 public AgeClassImpl(String name, String pfx, SemanticModel sm)
 {
  super( sm );
  this.name=name;

  if( pfx == null )
   idPrefix = name.substring(0,1);
  else
   idPrefix=pfx;
 }


 
 @Override
 public void addSuperClass( AgeClassWritable spCls )
 {
  if( superClasses.add(spCls) )
   spCls.addSubClass(this);
 }

 @Override
 public void addSubClass( AgeClassWritable sbCls )
 {
  if( subClasses.add(sbCls) )
   sbCls.addSuperClass(this);
 }

 
 @Override
 public Collection<AgeClass> getSuperClasses()
 {
  return superClasses;
 }
 
 @Override
 public Collection<AgeClass> getSubClasses()
 {
  return subClasses;
 }

 @Override
 public String getName()
 {
  return name;
 }

 @Override
 public boolean isCustom()
 {
  return false;
 }

 

 @Override
 public String getIdPrefix()
 {
  return idPrefix;
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
 public Collection<RelationRule> getRelationRules()
 {
  return relationRules;
 }



 @Override
 public Collection<RelationRule> getAllRelationRules()
 {
  Collection<Collection<RelationRule>> allRest = new ArrayList<Collection<RelationRule>>(10);
  
  Collector.collectFromHierarchy(this,allRest, new Collector<Collection<RelationRule>>(){

   @Override
   public Collection<RelationRule> get(AgeAbstractClass cls)
   {
    Collection<RelationRule> restr = ((AgeClass)cls).getRelationRules();
    return restr==null||restr.size()==0?null:restr;
   }} );
  
  return new CollectionsUnion<RelationRule>(allRest);
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
 public void addRelationRule(RelationRule mrr)
 {
  if( relationRules == null )
   relationRules = new ArrayList<RelationRule>();
  
  relationRules.add(mrr);
 }

}
