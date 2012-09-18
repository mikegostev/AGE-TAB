package uk.ac.ebi.age.model;


public interface AgeExternalObjectAttribute extends AgeObjectAttribute, Resolvable
{

 @Override
 public String getTargetObjectId();
 @Override
 ResolveScope getTargetResolveScope();
 
 IdScope getResolvedScope();
}
