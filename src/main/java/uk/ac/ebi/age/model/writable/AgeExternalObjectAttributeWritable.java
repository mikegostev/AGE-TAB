package uk.ac.ebi.age.model.writable;

import uk.ac.ebi.age.model.AgeExternalObjectAttribute;
import uk.ac.ebi.age.model.ResolveScope;

public interface AgeExternalObjectAttributeWritable extends AgeExternalObjectAttribute, AgeAttributeWritable
{
 void setTargetObject(AgeObjectWritable obj);
 void setTargetResolveScope( ResolveScope scp );
}
