package uk.ac.ebi.age.parser;

import uk.ac.ebi.age.model.IdScope;
import uk.ac.ebi.age.model.ResolveScope;

//This in immutable SyntaxProfileDefinition with default AGE profile
public class AgeDefaultSyntaxProfileDefinition implements SyntaxProfileDefinition
{
 public static final String escapeSequence="\\";

 public static final String customTokenBrackets="{}";
 public static final String flagsTokenBrackets="<>";
 public static final String qualifierTokenBrackets="[]";
 public static final String flagsSeparatorSign=";";
 public static final String flagsEqualSign="=";
 public static final String prototypeObjectId="*";
 public static final String anonymousObjectId="?";
 
 public static final String globalIdPrefix="G::";
 public static final String clusterIdPrefix="C::";
 public static final String moduleIdPrefix="M::";
 public static final String defaultScopeIdPrefix="D::";

 public static final String globalResolveScopePrefix="G::";
 public static final String clusterResolveScopePrefix="C::";
 public static final String moduleResolveScopePrefix="M::";
 public static final String moduleCascadeResolveScopePrefix="MC::";
 public static final String clusterCascadeResolveScopePrefix="CC::";
 public static final String globalFallbackResolveScopePrefix="GF::";
 public static final String defaultResolveScopePrefix="D::";

 public static final String defaultEmbeddedObjectAttributeSeparator=": ";

 public static final String horizontalBlockPrefix="-";
 public static final String verticalBlockPrefix="|";
 
 public static final IdScope defaultIdScope=IdScope.CLUSTER;
 public static final boolean horizontalBlockDefault = true;
 public static final boolean resetPrototype = true;
 public static final boolean allowImplicitCustomClasses = false;
 
 public static final ResolveScope defaultObjectAttributeResolveScope = ResolveScope.MODULE_CASCADE;
 public static final ResolveScope defaultFileAttributeResolveScope = ResolveScope.CLUSTER_CASCADE;
 public static final ResolveScope defaultRelationResolveScope = ResolveScope.MODULE_CASCADE;
 
 private static AgeDefaultSyntaxProfileDefinition instance  = new AgeDefaultSyntaxProfileDefinition();
 
 public static AgeDefaultSyntaxProfileDefinition getInstance()
 {
  return instance;
 }
 
 private AgeDefaultSyntaxProfileDefinition()
 {}
 
 @Override
 public String getCustomTokenBrackets()
 {
  return customTokenBrackets;
 }
 
 @Override
 public String getFlagsTokenBrackets()
 {
  return flagsTokenBrackets;
 }

 @Override
 public String getQualifierTokenBrackets()
 {
  return qualifierTokenBrackets;
 }

 @Override
 public String getFlagsSeparatorSign()
 {
  return flagsSeparatorSign;
 }
 
 @Override
 public String getFlagsEqualSign()
 {
  return flagsEqualSign;
 }
 
 @Override
 public String getPrototypeObjectId()
 {
  return prototypeObjectId;
 }
 @Override
 public String getAnonymousObjectId()
 {
  return anonymousObjectId;
 }
 @Override
 public String getGlobalIdPrefix()
 {
  return globalIdPrefix;
 }
 @Override
 public String getClusterIdPrefix()
 {
  return clusterIdPrefix;
 }
 @Override
 public String getModuleIdPrefix()
 {
  return moduleIdPrefix;
 }
 @Override
 public IdScope getDefaultIdScope()
 {
  return defaultIdScope;
 }

 @Override
 public String getHorizontalBlockPrefix()
 {
  return horizontalBlockPrefix;
 }

 @Override
 public String getVerticalBlockPrefix()
 {
  return verticalBlockPrefix;
 }

 @Override
 public boolean isHorizontalBlockDefault()
 {
  return horizontalBlockDefault;
 }

 @Override
 public boolean isResetPrototype()
 {
  return resetPrototype;
 }

 @Override
 public ResolveScope getDefaultObjectAttributeResolveScope()
 {
  return defaultObjectAttributeResolveScope;
 }

 @Override
 public ResolveScope getDefaultRelationResolveScope()
 {
  return defaultRelationResolveScope;
 }

 @Override
 public String getEscapeSequence()
 {
  return escapeSequence;
 }

 @Override
 public String getDefaultScopeIdPrefix()
 {
  return defaultScopeIdPrefix;
 }

 @Override
 public String getGlobalResolveScopePrefix()
 {
  return globalResolveScopePrefix;
 }

 @Override
 public String getClusterResolveScopePrefix()
 {
  return clusterResolveScopePrefix;
 }

 @Override
 public String getModuleResolveScopePrefix()
 {
  return moduleResolveScopePrefix;
 }

 @Override
 public String getModuleCascadeResolveScopePrefix()
 {
  return moduleCascadeResolveScopePrefix;
 }

 @Override
 public String getClusterCascadeResolveScopePrefix()
 {
  return clusterCascadeResolveScopePrefix;
 }

 @Override
 public String getDefaultResolveScopePrefix()
 {
  return defaultResolveScopePrefix;
 }

 @Override
 public ResolveScope getDefaultFileAttributeResolveScope()
 {
  return defaultFileAttributeResolveScope;
 }

 @Override
 public String getDefaultEmbeddedObjectAttributeSeparator()
 {
  return defaultEmbeddedObjectAttributeSeparator;
 }

 @Override
 public boolean allowImplicitCustomClasses()
 {
  return allowImplicitCustomClasses;
 }

 @Override
 public String getGlobalFallbackResolveScopePrefix()
 {
  return globalFallbackResolveScopePrefix;
 }

}
