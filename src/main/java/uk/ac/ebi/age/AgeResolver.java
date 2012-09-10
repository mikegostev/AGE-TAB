package uk.ac.ebi.age;

import java.io.File;

public interface AgeResolver
{

 File getAttachment(String value);
 File getAttachment(String value, String clusterId);

}
