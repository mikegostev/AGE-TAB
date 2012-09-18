package uk.ac.ebi.age.model;

import uk.ac.ebi.age.model.writable.AgeExternalObjectAttributeWritable;

public interface CascadeExternalObjectAttribute extends AgeExternalObjectAttributeWritable
{
 void setResolvedScope( IdScope scp );
}
