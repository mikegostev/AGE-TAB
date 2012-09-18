package uk.ac.ebi.age.model.impl.v1;

import uk.ac.ebi.age.model.AttributeClassRef;
import uk.ac.ebi.age.model.CascadeExternalObjectAttribute;
import uk.ac.ebi.age.model.IdScope;
import uk.ac.ebi.age.model.ResolveScope;
import uk.ac.ebi.age.model.writable.AttributedWritable;


public class AgeCascadeExternalObjectAttributeImpl extends AgeExternalObjectAttributeImpl implements CascadeExternalObjectAttribute
{

 private static final long serialVersionUID = 1L;

 private IdScope resolvedScope;

 protected AgeCascadeExternalObjectAttributeImpl(AttributeClassRef relClass, String id, AttributedWritable host, ResolveScope scp)
 {
  super(relClass, id, host, scp);
 }

 @Override
 public IdScope getResolvedScope()
 {
  return resolvedScope;
 }

 @Override
 public void setResolvedScope(IdScope resolvedScope)
 {
  this.resolvedScope = resolvedScope;
 }



}
