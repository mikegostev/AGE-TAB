package uk.ac.ebi.age;

import java.io.File;

import uk.ac.ebi.age.model.AgeAttributeClass;
import uk.ac.ebi.age.model.AgeClass;
import uk.ac.ebi.age.model.AgeRelationClass;
import uk.ac.ebi.age.model.ModuleKey;

public interface AgeResolver
{

 AgeClass getDefinedAgeClass(String className);
 AgeAttributeClass getDefinedAgeAttributeClass(String className);
 AgeRelationClass getDefinedAgeRelationClass(String className);
 AgeRelationClass getCustomAgeRelationClass(String className, ModuleKey modKey);

 File getAttachment(String value);
 File getAttachment(String value, String clusterId);

}
