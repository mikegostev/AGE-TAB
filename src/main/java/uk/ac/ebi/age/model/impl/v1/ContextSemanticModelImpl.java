package uk.ac.ebi.age.model.impl.v1;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import uk.ac.ebi.age.model.AgeAnnotation;
import uk.ac.ebi.age.model.AgeAnnotationClass;
import uk.ac.ebi.age.model.AgeAttributeClass;
import uk.ac.ebi.age.model.AgeAttributeClassPlug;
import uk.ac.ebi.age.model.AgeClass;
import uk.ac.ebi.age.model.AgeClassPlug;
import uk.ac.ebi.age.model.AgeCustomAttributeClass;
import uk.ac.ebi.age.model.AgeCustomClass;
import uk.ac.ebi.age.model.AgeCustomRelationClass;
import uk.ac.ebi.age.model.AgePropertyClass;
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

public class ContextSemanticModelImpl implements ContextSemanticModel, Serializable
{
 private static final long serialVersionUID = 1L;
 
 private transient SemanticModel masterModel;
 
 private Map<String,AgeClassPlug> classPlugs;
 private Map<String,AgeAttributeClassPlug> attrClassPlugs;
 private Map<String,AgeRelationClassPlug> relClassPlugs;

 
 private Map<String,AgeClassWritable> customClassMap;
 private Map<String,AgeRelationClassWritable> customRelationClassMap;
 
 private Map<String,Map<String,AgeAttributeClassWritable>> definedClass2CustomAttrMap;
 private Map<String,Map<String,AgeAttributeClassWritable>> customClass2CustomAttrMap;
// private Map<AgeClass,Map<String,AgeRelationClass>> class2CustomRelationMap = new TreeMap<AgeClass,Map<String,AgeRelationClass>>();

 
 public ContextSemanticModelImpl( SemanticModel mm )
 {
  masterModel=mm;
 }

 @Override
 public SemanticModel getMasterModel()
 {
  return masterModel;
 }
 
 @Override
 public AgeAttributeClassWritable getOrCreateCustomAgeAttributeClass(String name, DataType type, AgeClass cls, AgeAttributeClassWritable supCls )
 {
  AgeAttributeClassWritable acls = null;

  Map<String,AgeAttributeClassWritable> clsattr = null;
  Map<String,Map<String,AgeAttributeClassWritable>> map = null;
  
  if( cls.isCustom() )
  {
   if( customClass2CustomAttrMap == null )
    map=customClass2CustomAttrMap=new TreeMap<String, Map<String,AgeAttributeClassWritable>>();
   else
   {
    map=customClass2CustomAttrMap;
    clsattr = map.get(cls.getName());
   }
  }
  else
  {
   if( definedClass2CustomAttrMap == null )
    map=definedClass2CustomAttrMap=new TreeMap<String, Map<String,AgeAttributeClassWritable>>();
   else
   {
    map=definedClass2CustomAttrMap;
    clsattr = map.get(cls.getName());
   }
  }
  
  
  if( clsattr == null )
  {
   clsattr=new TreeMap<String,AgeAttributeClassWritable>();
   map.put(cls.getName(), clsattr);
   
//   if( cls.getAliases() != null )
//   {
//    for( String alias : cls.getAliases() )
//     map.put(alias, clsattr);
//   }
  }
  else
   acls = clsattr.get(name);

  if( acls == null )
  {
   acls = masterModel.getModelFactory().createCustomAgeAttributeClass(name, type, this, cls);
   clsattr.put(name,acls);
  }
  
  if( supCls != null )
   acls.addSuperClass(supCls);
  
  return acls;
 }
 
 @Override
 public AgeRelationClassWritable getOrCreateCustomAgeRelationClass(String name, AgeClass range, AgeClass owner, AgeRelationClass supCls)
 {
  AgeRelationClassWritable cls = null;
  
  if( customRelationClassMap == null )
   customRelationClassMap = new TreeMap<String, AgeRelationClassWritable>();
  else
   cls = customRelationClassMap.get(name);
  
  if( cls == null )
  {
   cls = masterModel.getModelFactory().createCustomAgeRelationClass(name, this, range, owner);
   customRelationClassMap.put(name, cls);
  }
  
  if( supCls != null )
   cls.addSuperClass( (AgeRelationClassWritable)supCls);
  
  
  AgeRelationClassWritable invRelCls = masterModel.getModelFactory().createAgeRelationClass( "!"+name, null );
  
  invRelCls.setImplicit(true);
  
  cls.setInverseRelationClass( invRelCls );
  invRelCls.setInverseRelationClass(cls);

  
  return cls;
 }

 @Override
 public AgeAnnotationClassWritable createAgeAnnotationClass(String name, AgeAnnotationClass parent)
 {
  return masterModel.createAgeAnnotationClass(name, parent);
 }

 @Override
 public AgeAnnotationClassWritable createAgeAnnotationClass(String name, Collection<String> aliases, AgeAnnotationClass parent)
 {
  return masterModel.createAgeAnnotationClass(name, aliases, parent);
 }

 @Override
 public AgeClassWritable createAgeClass(String name, String pfx, AgeClass parent)
 {
  return masterModel.createAgeClass(name, pfx, parent);
 }
 
 @Override
 public AgeClassWritable createAgeClass(String name, Collection<String> aliases, String pfx, AgeClass parent)
 {
  return masterModel.createAgeClass(name, aliases, pfx, parent);
 }

 
 @Override
 public AgeClassWritable getOrCreateCustomAgeClass(String name, String pfx, AgeClass parent)
 {
  AgeClassWritable cls = null;
  
  if( customClassMap == null )
   customClassMap = new TreeMap<String, AgeClassWritable>();
  else
   cls = customClassMap.get(name);
  
  if( cls == null )
  {
   cls = masterModel.getModelFactory().createCustomAgeClass(name, pfx, this);
   customClassMap.put(name, cls);
  }
  
  cls.addSuperClass((AgeClassWritable)parent);
  
  return cls;
 }


 
 @Override
 public AgeExternalRelationWritable createExternalRelation(RelationClassRef clsRef, AgeObjectWritable sourceObj, String val, ResolveScope scope )
 {
  return masterModel.getModelFactory().createExternalRelation(clsRef, sourceObj, val, scope);
 }


 @Override
 public AgeAttributeWritable createExternalObjectAttribute(AttributeClassRef atCls, AttributedWritable host, String val, ResolveScope scope )
 {
  return masterModel.getModelFactory().createExternalObjectAttribute(atCls, host, val, scope );
 }


 @Override
 public AgeClass getDefinedAgeClass(String name)
 {
  return masterModel.getDefinedAgeClass(name);
 }

 @Override
 public AgeRelationClass getDefinedAgeRelationClass(String name)
 {
  return masterModel.getDefinedAgeRelationClass(name);
 }

 
 @Override
 public AgeClass getCustomAgeClass(String name)
 {
  if( customClassMap == null )
   return null;
  
  return customClassMap.get(name);
 }

 /*
 public AgeClass getAgeClass(String name)
 {
  AgeClass cls = getCustomAgeClass(name);
  
  if( cls != null )
   return cls;
  
  return getDefinedAgeClass(name);
 }

 public AgeRelationClass getAgeRelationClass(String name)
 {
  AgeRelationClass cls = getCustomAgeRelationClass(name);
  
  if( cls != null )
   return cls;
  
  return getDefinedAgeRelationClass(name);
 }
*/
 
 @Override
 public AgeRelationClass getCustomAgeRelationClass(String name)
 {
  if( customRelationClassMap == null )
   return null;
  
  return customRelationClassMap.get(name);
 }

 @Override
 public AgeAttributeClass getCustomAgeAttributeClass(String name, AgeClass cls)
 {
  Map<String,AgeAttributeClassWritable> atclMap = null;
  
  if( cls.isCustom() )
   atclMap = customClass2CustomAttrMap != null? customClass2CustomAttrMap.get(cls.getName()) : null;
  else
   atclMap = definedClass2CustomAttrMap != null? definedClass2CustomAttrMap.get(cls.getName()) : null;
  
  if( atclMap == null )
   return null;
  
  return atclMap.get(name);
 }

 @Override
 public AgePropertyClass getDefinedAgeClassProperty( String name )
 {
  return masterModel.getDefinedAgeClassProperty(name);
 }

// public boolean isValidProperty(AgeClassProperty prop, AgeClass ageClass)
// {
//  return masterModel.isValidProperty(prop, ageClass);
// }

 @Override
 public DataModuleWritable createDataModule()
 {
  return masterModel.getModelFactory().createDataModule(this);
 }

 @Override
 public AgeAttributeClassWritable createAgeAttributeClass(String name, DataType type, AgeAttributeClass parent)
 {
  return masterModel.createAgeAttributeClass(name, type, parent);
 }

 @Override
 public AgeAttributeClassWritable createAgeAttributeClass(String name, Collection<String> aliases, DataType type, AgeAttributeClass parent)
 {
  return masterModel.createAgeAttributeClass(name, aliases, type, parent);
 }
 
 @Override
 public AgeObjectWritable createAgeObject(ClassRef cls, String id )
 {
  return masterModel.getModelFactory().createAgeObject( cls, id );
 }

 @Override
 public AgeRelationClassWritable createAgeRelationClass(String name, AgeRelationClass parent)
 {
  return masterModel.createAgeRelationClass(name, parent);
 }

 @Override
 public AgeRelationClassWritable createAgeRelationClass(String name, Collection<String> aliases, AgeRelationClass parent)
 {
  return masterModel.createAgeRelationClass(name, aliases, parent);
 }
 
 @Override
 public ModelFactory getModelFactory()
 {
  return masterModel.getModelFactory();
 }

// @Override
// public AgeAttributeWritable createAgeAttribute(AttributeClassRef attrClass, AttributedWritable host)
// {
//  AttributeClassRef ref = masterModel.getModelFactory().createAttributeClassRef( getAgeAttributeClassPlug(attrClass), 0, attrClass.getName());
//  
//  return masterModel.getModelFactory().createAgeAttribute(ref,host);
// }
 
 @Override
 public AgeAttributeWritable createAgeAttribute(AttributeClassRef attrClass, AttributedWritable host )
 {
  switch(attrClass.getAgeElClass().getDataType())
  {
   case BOOLEAN:
    return masterModel.getModelFactory().createAgeBooleanAttribute(attrClass, host);

   case INTEGER:
    return masterModel.getModelFactory().createAgeIntegerAttribute(attrClass, host);

   case REAL:
    return masterModel.getModelFactory().createAgeRealAttribute(attrClass, host);

   case STRING:
   case TEXT:
   case URI:
   case GUESS:
    return masterModel.getModelFactory().createAgeStringAttribute(attrClass, host);

   case FILE:
    return masterModel.getModelFactory().createAgeFileAttribute(attrClass, host, ResolveScope.CLUSTER_CASCADE);
    
   case OBJECT:
    return masterModel.getModelFactory().createAgeObjectAttribute(attrClass, host);
  }
  
  return null;
 }

 @Override
 public AgeAttributeWritable createAgeFileAttribute(AttributeClassRef attrClass, AttributedWritable host, ResolveScope scope )
 {
  return masterModel.getModelFactory().createAgeFileAttribute(attrClass, host, scope);
 }

 
 @Override
 public AgeRelationWritable createAgeRelation(RelationClassRef rClsR, AgeObjectWritable sourceObj, AgeObjectWritable targetObj)
 {
  return masterModel.getModelFactory().createRelation(rClsR, sourceObj, targetObj);
 }

 @Override
 public AgeRelationWritable createInferredInverseRelation(AgeRelationWritable rl)
 {
  return masterModel.getModelFactory().createInferredInverseRelation(rl);
 }

 @Override
 public AgeAttributeClass getDefinedAgeAttributeClass(String attrClass)
 {
  return masterModel.getDefinedAgeAttributeClass( attrClass );
 }

 @Override
 public void setMasterModel(SemanticModel newModel)
 {
  masterModel = newModel;

  if(classPlugs != null)
  {
   for(AgeClassPlug plg : classPlugs.values())
    plg.unplug();
  }

  if(attrClassPlugs != null)
  {
   for(AgeAttributeClassPlug plg : attrClassPlugs.values())
    plg.unplug();
  }

  if(relClassPlugs != null)
  {
   for(AgeRelationClassPlug plg : relClassPlugs.values())
    plg.unplug();
  }

 }

 @Override
 public AgeClassPlug getAgeClassPlug(AgeClass cls)
 {
  AgeClassPlug plug = null;
  
  String classKey = (cls.isCustom()?"C":"D")+cls.getName();
  
  if( classPlugs == null )
   classPlugs = new TreeMap<String, AgeClassPlug>();
  else
  {
   plug = classPlugs.get(classKey);
   
   if( plug != null )
    return plug;
  }
  
  if( cls.isCustom() )
   plug = (AgeCustomClass)cls;
  else
   plug = masterModel.getModelFactory().createAgeClassPlug(cls);
  
  classPlugs.put(classKey, plug);
  
  return plug;
 }



 @Override
 public AgeRelationClassPlug getAgeRelationClassPlug(AgeRelationClass cls)
 {
  String classKey = cls.isCustom()?"C":"D";
 
  classKey += cls.isImplicit()?("I"+cls.getInverseRelationClass().getName()):("E"+cls.getName());
  
  
  AgeRelationClassPlug plug = null;

  if(relClassPlugs == null)
   relClassPlugs = new TreeMap<String, AgeRelationClassPlug>();
  else
  {
   plug = relClassPlugs.get(classKey);

   if(plug != null)
    return plug;
  }

  if(cls.isCustom())
   plug = (AgeCustomRelationClass)cls;
  else
   plug = masterModel.getModelFactory().createAgeRelationClassPlug(cls);

  relClassPlugs.put(classKey, plug);

  return plug;
 }

 
 @Override
 public AgeAttributeClassPlug getAgeAttributeClassPlug(AgeAttributeClass cls)
 {
  if( cls.isCustom() )
   return (AgeCustomAttributeClass)cls;
  
  String classKey = (cls.isCustom()?"C":"D")+cls.getName();

  AgeAttributeClassPlug plug = null;
  
  if( attrClassPlugs == null )
   attrClassPlugs = new TreeMap<String, AgeAttributeClassPlug>();
  else
  {
   plug = attrClassPlugs.get(classKey);
  
   if( plug != null )
    return plug;
  }
  
  plug = masterModel.getModelFactory().createAgeAttributeClassPlug(cls);
  
  attrClassPlugs.put(classKey, plug);
  
  return plug;
 }


 @Override
 public Collection<? extends AgeClass> getAgeClasses()
 {
  if( customClassMap == null )
   return null;
  
  return customClassMap.values();
 }

 @Override
 public AgeClass getRootAgeClass()
 {
  return null;
 }

 @Override
 public AgeAttributeClass getRootAgeAttributeClass()
 {
  return null;
 }
 
 @Override
 public AgeRelationClass getRootAgeRelationClass()
 {
  return null;
 }

 @Override
 public AgeAnnotationClass getRootAgeAnnotationClass()
 {
  return null;
 }

 @Override
 public Collection<AgeAnnotation> getAnnotations()
 {
  return null;
 }

 @Override
 public AgeAnnotationWritable createAgeAnnotation(AgeAnnotationClass cls)
 {
  return masterModel.createAgeAnnotation(cls);
 }

 @Override
 public void addAnnotation(AgeAnnotation ant)
 {
 }

 @Override
 public AttributeAttachmentRuleWritable createAttributeAttachmentRule(RestrictionType type)
 {
  return masterModel.createAttributeAttachmentRule(type);
 }

 @Override
 public RelationRuleWritable createRelationRule(RestrictionType type)
 {
  return masterModel.createRelationRule(type);
 }

 @Override
 public QualifierRuleWritable createQualifierRule()
 {
  return masterModel.createQualifierRule();
 }

 @Override
 public int getIdGen()
 {
  return masterModel.getIdGen();
 }

 @Override
 public void setIdGen(int id)
 {
  masterModel.setIdGen( id );
 }

 @Override
 public void setModelFactory(ModelFactory mf)
 {
  getMasterModel().setModelFactory(mf);
 }

 @Override
 public ContextSemanticModel createContextSemanticModel()
 {
  return masterModel.createContextSemanticModel();
 }



// @Override
// public void setRootAgeClass(AgeClass cls)
// {
//  masterModel.setRootAgeClass(cls);
// }
//
// @Override
// public void setRootAgeAttributeClass(AgeAttributeClass cls)
// {
//  masterModel.setRootAgeAttributeClass(cls);
// }
//
// @Override
// public void setRootAgeRelationClass(AgeRelationClass cls)
// {
//  masterModel.setRootAgeRelationClass(cls);
// }
//
// @Override
// public void setRootAgeAnnotationClass(AgeAnnotationClass cls)
// {
//  masterModel.setRootAgeAnnotationClass(cls);
// }

}
