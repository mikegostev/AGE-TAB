package uk.ac.ebi.age.model;

import java.util.Collection;

import uk.ac.ebi.age.model.writable.AgeAnnotationClassWritable;
import uk.ac.ebi.age.model.writable.AgeAnnotationWritable;
import uk.ac.ebi.age.model.writable.AgeAttributeClassWritable;
import uk.ac.ebi.age.model.writable.AgeClassWritable;
import uk.ac.ebi.age.model.writable.AgeRelationClassWritable;
import uk.ac.ebi.age.model.writable.AttributeAttachmentRuleWritable;
import uk.ac.ebi.age.model.writable.QualifierRuleWritable;
import uk.ac.ebi.age.model.writable.RelationRuleWritable;

public interface SemanticModel
{
 static final String ROOT_CLASS_NAME = "Class";
 static final String ROOT_ATTRIBUTE_CLASS_NAME = "Attribute";
 static final String ROOT_RELATION_CLASS_NAME = "Relation";
 static final String ROOT_ANNOTATION_CLASS_NAME = "Annotation";
 static final String ROOT_CLASS_ID = "__RootAgeClass";
 static final String ROOT_ATTRIBUTE_CLASS_ID = "__RootAgeAttributeClass";
 static final String ROOT_RELATION_CLASS_ID = "__RootAgeRelationClass";
 static final String ROOT_ANNOTATION_CLASS_ID = "__RootAgeAnnotationClass";

 int getIdGen();
 void setIdGen( int id );
 
 AgeClassWritable createAgeClass(String name, String pfx, AgeClass parent);
 AgeClassWritable createAgeClass(String name, Collection<String> aliases, String pfx, AgeClass parent);

 AgeAttributeClassWritable createAgeAttributeClass(String name, DataType type, AgeAttributeClass parent);
 AgeAttributeClassWritable createAgeAttributeClass(String name, Collection<String> aliases, DataType type, AgeAttributeClass parent);

 AgeRelationClassWritable createAgeRelationClass(String name, AgeRelationClass parent);
 AgeRelationClassWritable createAgeRelationClass(String name, Collection<String> aliases,  AgeRelationClass parent);

 AgeAnnotationClassWritable createAgeAnnotationClass(String name, AgeAnnotationClass parent);
 AgeAnnotationClassWritable createAgeAnnotationClass(String name, Collection<String> aliases, AgeAnnotationClass parent);

 
 AgeAnnotationWritable createAgeAnnotation(AgeAnnotationClass cls);

 ContextSemanticModel createContextSemanticModel();


 AttributeAttachmentRuleWritable createAttributeAttachmentRule(RestrictionType type);

 RelationRuleWritable createRelationRule(RestrictionType type);
 QualifierRuleWritable createQualifierRule();

 AgeClass getDefinedAgeClass(String name);

 AgeRelationClass getDefinedAgeRelationClass(String name);

 AgePropertyClass getDefinedAgeClassProperty(String name);

 AgeAttributeClass getDefinedAgeAttributeClass(String attrClass);

 
 ModelFactory getModelFactory();
 void setModelFactory( ModelFactory mf );

 Collection< ? extends AgeClass> getAgeClasses();
 
 
 AgeClass getRootAgeClass();

 AgeAttributeClass getRootAgeAttributeClass();

 AgeRelationClass getRootAgeRelationClass();

 AgeAnnotationClass getRootAgeAnnotationClass();

 void addAnnotation(AgeAnnotation ant);
 Collection<AgeAnnotation> getAnnotations();


}
