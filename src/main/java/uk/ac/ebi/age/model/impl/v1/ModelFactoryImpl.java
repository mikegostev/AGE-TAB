package uk.ac.ebi.age.model.impl.v1;

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

public class ModelFactoryImpl implements Serializable, ModelFactory 
{
 private static final long serialVersionUID = 4L;

 private static ModelFactoryImpl instance;

 private ModelFactoryImpl()
 {}
 
 public static ModelFactory getInstance()
 {
  if( instance == null )
   instance = new ModelFactoryImpl();
  
  return instance;
 }



 @Override
 public AgeObjectWritable createAgeObject(ClassRef ageClassRef, String id )
 {
  return new AgeObjectImpl(ageClassRef, id);
 }



 @Override
 public AgeAttributeClassWritable createCustomAgeAttributeClass(String name, DataType type, ContextSemanticModel sm, AgeClass owner)
 {
  if( type == DataType.OBJECT )
   return new CustomObjectAgeAttributeClassImpl(name, sm, owner);
  
  return new CustomAgeAttributeClassImpl(name, type, sm, owner);
 }


 @Override
 public AgeClassWritable createCustomAgeClass(String name, String pfx, ContextSemanticModel sm)
 {
  return new CustomAgeClassImpl(name, pfx, sm);
 }

 @Override
 public AgeRelationClassWritable createCustomAgeRelationClass(String name, ContextSemanticModel sm, AgeClass range, AgeClass owner)
 {
  return new CustomAgeRelationClassImpl(name, sm, range, owner);
 }


 @Override
 public AttributeClassRef createAttributeClassRef(AgeAttributeClassPlug plug, int order, String heading)
 {
  return new AttrClassRef(plug, order, heading);
 }

 @Override
 public ClassRef createClassRef(AgeClassPlug plug, int order, String heading, boolean hrz, ContextSemanticModel modl )
 {
  return new ClassRefImpl(plug, order, heading, hrz, modl );
 }

 @Override
 public RelationClassRef createRelationClassRef(AgeRelationClassPlug plug, int order, String heading)
 {
  return new RelClassRef(plug, order, heading);
 }


 @Override
 public AgeAttributeWritable createAgeIntegerAttribute(AttributeClassRef attrClass, AttributedWritable host)
 {
  return new AgeIntegerAttributeImpl(attrClass, host);
 }

 @Override
 public AgeAttributeWritable createAgeRealAttribute(AttributeClassRef attrClass, AttributedWritable host)
 {
  return new AgeRealAttributeImpl(attrClass, host);
 }

 @Override
 public AgeAttributeWritable createAgeBooleanAttribute(AttributeClassRef attrClass, AttributedWritable host)
 {
  return new AgeBooleanAttributeImpl(attrClass, host);
 }


 @Override
 public AgeAttributeWritable createAgeObjectAttribute(AttributeClassRef attrClass, AttributedWritable host)
 {
  return new AgeObjectAttributeImpl(attrClass, host);
 }

 @Override
 public AgeRelationWritable createInferredInverseRelation(AgeRelationWritable dirRel)
 {
  return new InferredInverseRelation(dirRel);
 }

 @Override
 public DataModuleWritable createDataModule( ContextSemanticModel sm )
 {
  return new DataModuleImpl( sm );
 }

 @Override
 public AgeExternalRelationWritable createExternalRelation(RelationClassRef ref, AgeObjectWritable sourceObj, String id, ResolveScope global )
 {
  return new AgeExternalRelationImpl(ref, sourceObj, id, global);
 }
 

 @Override
 public AgeAttributeWritable createExternalObjectAttribute(AttributeClassRef atCls, AttributedWritable host , String id, ResolveScope scope )
 {
  return new AgeExternalObjectAttributeImpl(atCls, id, host, scope);
 }

 
 @Override
 public AgeRelationWritable createRelation(RelationClassRef rClsR, AgeObjectWritable sourceObj, AgeObjectWritable targetObj)
 {
  return new AgeRelationImpl(rClsR, sourceObj, targetObj);
 }

 @Override
 public AgeAttributeWritable createAgeStringAttribute(AttributeClassRef attrClass, AttributedWritable host)
 {
  return new AgeStringAttributeImpl(attrClass, host);
 }


 @Override
 public AgeAttributeWritable createAgeFileAttribute(AttributeClassRef attrClass, AttributedWritable host,
   ResolveScope scope)
 {
  return new AgeFileAttributeImpl(attrClass, host, scope);
 }




 @Override
 public AgeClassWritable createAgeClass(String name, String pfx, SemanticModel sm)
 {
  return new AgeClassImpl(name, pfx, sm);
 }

 @Override
 public AgeRelationClassWritable createAgeRelationClass(String name, SemanticModel sm)
 {
  return new AgeRelationClassImpl(name, sm);
 }

 @Override
 public AgeAnnotationClassWritable createAgeAnnotationClass(String name, SemanticModel sm)
 {
  return new AgeAnnotationClassImpl(name, sm);
 }

 
 @Override
 public AgeAttributeClassWritable createAgeAttributeClass(String name, DataType type, SemanticModel sm)
 {
  return new AgeAttributeClassImpl(name, type, sm);
 }



 @Override
 public AgeAttributeClassPlug createAgeAttributeClassPlug(AgeAttributeClass attrClass)
 {
  return new AgeAttributeClassPlugPluggable(attrClass);
 }

 @Override
 public AgeClassPlug createAgeClassPlug(AgeClass cls)
 {
  return new AgeClassPlugPluggable(cls);
 }

 @Override
 public AgeRelationClassPlug createAgeRelationClassPlug(AgeRelationClass relClass)
 {
  return new AgeRelationClassPlugPluggable(relClass);
 }

 @Override
 public AgeRelationClassPlug createAgeRelationInverseClassPlug(AgeRelationClass relClass)
 {
  return new AgeRelationInverseClassPlug(relClass);
 }

 @Override
 public AgeAnnotationWritable createAgeAnnotation(AgeAnnotationClass cls, SemanticModel sm)
 {
  return new AgeAnnotationImpl(cls, sm);
 }

 @Override
 public AttributeAttachmentRuleWritable createAgeAttributeAttachmentRule(RestrictionType type, SemanticModel sm)
 {
  return new AttributeAttachmentRuleImpl(type,sm);
 }

 @Override
 public RelationRuleWritable createAgeRelationRule(RestrictionType type, SemanticModel sm)
 {
  return new RelationRuleImpl(type, sm);
 }

 @Override
 public QualifierRuleWritable createAgeQualifierRule(SemanticModel sm)
 {
  return new QualifierRuleImpl( sm );
 }


 @Override
 public ContextSemanticModel createContextSemanticModel( SemanticModel mm )
 {
  return new ContextSemanticModelImpl(mm);
 }

 @Override
 public SemanticModel createModelInstance()
 {
  return new SemanticModelImpl(this);
 }





}
