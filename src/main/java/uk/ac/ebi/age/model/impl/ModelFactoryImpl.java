package uk.ac.ebi.age.model.impl;

import java.io.Serializable;

import uk.ac.ebi.age.model.AgeAnnotationClass;
import uk.ac.ebi.age.model.AgeAttributeClass;
import uk.ac.ebi.age.model.AgeAttributeClassPlug;
import uk.ac.ebi.age.model.AgeClass;
import uk.ac.ebi.age.model.AgeClassPlug;
import uk.ac.ebi.age.model.AgeRelationClass;
import uk.ac.ebi.age.model.AgeRelationClassPlug;
import uk.ac.ebi.age.model.AttributeClassRef;
import uk.ac.ebi.age.model.ClassRef;
import uk.ac.ebi.age.model.ContextSemanticModel;
import uk.ac.ebi.age.model.DataType;
import uk.ac.ebi.age.model.ModelFactory;
import uk.ac.ebi.age.model.RelationClassRef;
import uk.ac.ebi.age.model.ResolveScope;
import uk.ac.ebi.age.model.RestrictionType;
import uk.ac.ebi.age.model.SemanticModel;
import uk.ac.ebi.age.model.writable.AgeAnnotationClassWritable;
import uk.ac.ebi.age.model.writable.AgeAnnotationWritable;
import uk.ac.ebi.age.model.writable.AgeAttributeClassWritable;
import uk.ac.ebi.age.model.writable.AgeAttributeWritable;
import uk.ac.ebi.age.model.writable.AgeClassWritable;
import uk.ac.ebi.age.model.writable.AgeExternalRelationWritable;
import uk.ac.ebi.age.model.writable.AgeObjectWritable;
import uk.ac.ebi.age.model.writable.AgeRelationClassWritable;
import uk.ac.ebi.age.model.writable.AgeRelationWritable;
import uk.ac.ebi.age.model.writable.AttributeAttachmentRuleWritable;
import uk.ac.ebi.age.model.writable.AttributedWritable;
import uk.ac.ebi.age.model.writable.DataModuleWritable;
import uk.ac.ebi.age.model.writable.QualifierRuleWritable;
import uk.ac.ebi.age.model.writable.RelationRuleWritable;

public class ModelFactoryImpl  implements Serializable, ModelFactory 
{
 private static final long serialVersionUID = 1L;

 private static ModelFactoryImpl instance = new ModelFactoryImpl();

 private ModelFactoryImpl()
 {}
 
 public static ModelFactory getInstance()
 {
  return instance;
 }

 
 private final ModelFactory v1factory = uk.ac.ebi.age.model.impl.v1.ModelFactoryImpl.getInstance();
 
 @Override
 public SemanticModel createModelInstance()
 {
  SemanticModel mod = v1factory.createModelInstance();
  
  mod.setModelFactory( this );
  
  return mod;
 }
 
 @Override
 public DataModuleWritable createDataModule( ContextSemanticModel sm )
 {
  return v1factory.createDataModule( sm );
 }

 @Override
 public AgeObjectWritable createAgeObject( ClassRef clsR, String id)
 {
  return v1factory.createAgeObject(clsR, id);
 }

 @Override
 public AgeClassWritable createAgeClass(String name, String pfx, SemanticModel sm)
 {
  return v1factory.createAgeClass(name, pfx, sm);
 }

 @Override
 public AgeRelationClassWritable createAgeRelationClass(String name, SemanticModel sm)
 {
  return v1factory.createAgeRelationClass(name, sm);
 }

 @Override
 public AgeAnnotationClassWritable createAgeAnnotationClass(String name, SemanticModel sm)
 {
  return v1factory.createAgeAnnotationClass(name, sm);
 }

 
 @Override
 public AgeAttributeClassWritable createAgeAttributeClass(String name, DataType type, SemanticModel sm)
 {
  return v1factory.createAgeAttributeClass(name, type, sm);
 }


 @Override
 public AgeExternalRelationWritable createExternalRelation(RelationClassRef rClsR, AgeObjectWritable sourceObj, String id, ResolveScope glb )
 {
  return v1factory.createExternalRelation(rClsR, sourceObj, id, glb);
 }
 

 @Override
 public AgeAttributeWritable createExternalObjectAttribute(AttributeClassRef atCls, AttributedWritable host, String id, ResolveScope glb )
 {
  return v1factory.createExternalObjectAttribute(atCls, host, id, glb);
 }

 @Override
 public AgeRelationWritable createRelation(RelationClassRef rClsR, AgeObjectWritable sourceObj, AgeObjectWritable targetObj)
 {
  return v1factory.createRelation(rClsR, sourceObj, targetObj);
 }


 @Override
 public AgeAttributeClassWritable createCustomAgeAttributeClass(String name, DataType type, ContextSemanticModel sm, AgeClass owner)
 {
  return v1factory.createCustomAgeAttributeClass(name, type, sm, owner);
 }

 @Override
 public AgeClassWritable createCustomAgeClass(String name, String pfx, ContextSemanticModel sm)
 {
  return v1factory.createCustomAgeClass(name, pfx, sm);
 }

 @Override
 public AgeRelationClassWritable createCustomAgeRelationClass(String name, ContextSemanticModel sm, AgeClass range, AgeClass owner)
 {
  return v1factory.createCustomAgeRelationClass(name, sm, range, owner);
 }

 @Override
 public AgeAttributeClassPlug createAgeAttributeClassPlug(AgeAttributeClass attrClass, ContextSemanticModel sm)
 {
  return v1factory.createAgeAttributeClassPlug(attrClass, sm);
 }

 @Override
 public AgeClassPlug createAgeClassPlug(AgeClass cls, ContextSemanticModel sm)
 {
  return v1factory.createAgeClassPlug(cls, sm);
 }

 @Override
 public AgeRelationClassPlug createAgeRelationClassPlug(AgeRelationClass relClass, ContextSemanticModel sm)
 {
  return v1factory.createAgeRelationClassPlug(relClass, sm);
 }

 @Override
 public AgeRelationClassPlug createAgeRelationInverseClassPlug(AgeRelationClass relClass, ContextSemanticModel sm)
 {
  return v1factory.createAgeRelationInverseClassPlug(relClass, sm);
 }

 @Override
 public AgeAnnotationWritable createAgeAnnotation(AgeAnnotationClass cls, SemanticModel sm)
 {
  return v1factory.createAgeAnnotation(cls, sm);
 }

 @Override
 public AttributeAttachmentRuleWritable createAgeAttributeAttachmentRule(RestrictionType type, SemanticModel sm)
 {
  return v1factory.createAgeAttributeAttachmentRule(type,sm);
 }

 @Override
 public RelationRuleWritable createAgeRelationRule(RestrictionType type, SemanticModel sm)
 {
  return v1factory.createAgeRelationRule(type, sm);
 }

 @Override
 public QualifierRuleWritable createAgeQualifierRule(SemanticModel sm)
 {
  return v1factory.createAgeQualifierRule( sm );
 }

 @Override
 public AttributeClassRef createAttributeClassRef(AgeAttributeClassPlug plug, int order, String heading)
 {
  return v1factory.createAttributeClassRef(plug, order, heading);
 }

 @Override
 public RelationClassRef createRelationClassRef(AgeRelationClassPlug plug, int order, String heading)
 {
  return v1factory.createRelationClassRef(plug, order, heading);
 }

 @Override
 public ClassRef createClassRef(AgeClassPlug plug, int order, String heading, boolean hrz, ContextSemanticModel modl )
 {
  return v1factory.createClassRef(plug, order, heading, hrz, modl );
 }

 @Override
 public ContextSemanticModel createContextSemanticModel(SemanticModel mm)
 {
  return v1factory.createContextSemanticModel(mm);
 }

 @Override
 public AgeAttributeWritable createAgeStringAttribute(AttributeClassRef attrClass, AttributedWritable host)
 {
  return v1factory.createAgeStringAttribute(attrClass, host);
 }

 @Override
 public AgeAttributeWritable createAgeIntegerAttribute(AttributeClassRef attrClass, AttributedWritable host)
 {
  return v1factory.createAgeIntegerAttribute(attrClass, host);
 }

 @Override
 public AgeAttributeWritable createAgeRealAttribute(AttributeClassRef attrClass, AttributedWritable host)
 {
  return v1factory.createAgeRealAttribute(attrClass, host);
 }

 @Override
 public AgeAttributeWritable createAgeBooleanAttribute(AttributeClassRef attrClass, AttributedWritable host)
 {
  return v1factory.createAgeBooleanAttribute(attrClass, host);
 }

 @Override
 public AgeAttributeWritable createAgeFileAttribute(AttributeClassRef attrClass, AttributedWritable host, ResolveScope scope)
 {
  return v1factory.createAgeFileAttribute(attrClass, host, scope);
 }

 @Override
 public AgeAttributeWritable createAgeObjectAttribute(AttributeClassRef attrClass, AttributedWritable host)
 {
  return v1factory.createAgeObjectAttribute(attrClass, host);
 }

 @Override
 public AgeRelationWritable createInferredInverseRelation(AgeRelationWritable dirRel)
 {
  return v1factory.createInferredInverseRelation(dirRel);
 }

}
