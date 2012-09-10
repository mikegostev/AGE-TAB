package uk.ac.ebi.age.model;

import java.util.Collection;

public interface AgeAttributeClass extends AgePropertyClass, AgeSemanticElement, AgeAbstractClass, AttributedClass
{
 DataType getDataType();

 @Override
 String getName();

 @Override
 boolean isCustom();

 AgeClass getOwningClass();

 
 @Override
 Collection<AgeAttributeClass> getSuperClasses();
 @Override
 Collection<AgeAttributeClass> getSubClasses();

 AgeClass getTargetClass();
}
