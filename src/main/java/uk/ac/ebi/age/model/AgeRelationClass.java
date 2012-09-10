package uk.ac.ebi.age.model;

import java.util.Collection;

public interface AgeRelationClass extends AgePropertyClass, AgeAbstractClass, AgeSemanticElement, AttributedClass
{
 @Override
 Collection<AgeRelationClass> getSuperClasses();
 @Override
 Collection<AgeRelationClass> getSubClasses();

 @Override
 String getName();

 boolean isWithinRange(AgeClass key);
 boolean isWithinDomain(AgeClass key);

 Collection<AgeClass> getRange();
 Collection<AgeClass> getDomain();

 @Override
 boolean isCustom();

 AgeRelationClass getInverseRelationClass();
 
 boolean isImplicit();
 
 public void resetModel();
 
 boolean isFunctional();
 boolean isInverseFunctional();
 boolean isSymmetric();
 boolean isTransitive();

}
