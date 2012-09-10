package uk.ac.ebi.age.model;

import java.util.Collection;

public interface AgeAbstractClass
{
 Collection<? extends AgeAbstractClass> getSuperClasses();
 Collection<? extends AgeAbstractClass> getSubClasses();
 
 boolean isClassOrSubclass( AgeAbstractClass cl );
 
 String getName();

 boolean isAbstract();
 boolean isCustom();
 
 Collection<AgeAnnotation> getAnnotations();
 
 Collection<String> getAliases();
}
