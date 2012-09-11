package uk.ac.ebi.age.model.impl.v1;

import java.io.ObjectStreamException;

import uk.ac.ebi.age.model.AgeAttribute;
import uk.ac.ebi.age.model.AttributeClassRef;
import uk.ac.ebi.age.model.FormatException;
import uk.ac.ebi.age.model.writable.AgeAttributeWritable;
import uk.ac.ebi.age.model.writable.AttributedWritable;
import uk.ac.ebi.mg.packedstring.PackedString;

public class AgeStringAttributeImpl extends AgeAttributeImpl
{
 private static final long serialVersionUID = 4L;
 
 private Object value; 

 protected AgeStringAttributeImpl(AttributeClassRef attrClass, AttributedWritable host)
 {
  super(attrClass, host);
 }

 // Will be called by deserializer
 private Object readResolve() throws ObjectStreamException
 {
  if( value instanceof String )
   value = ((String)value).intern();
  
  return this;
 }

 
 @Override
 public Object getValue()
 {
  return value;
 }

 @Override
 public void updateValue(String val) throws FormatException
 {
  if( value == null )
   value=val;
  else
  {
   if( val.length() == 0 )
    value = value+"\n";
   else
    value = value+"\n"+val;
  }
 }

 @Override
 public void finalizeValue()
 {
//  if( value != null )
//   value = value.toString().trim();
  
  if( value instanceof String )
   value = PackedString.pack(value.toString());
  
  if( value instanceof String )
   value = ((String)value).intern();
 }
 
 @Override
 public boolean getValueAsBoolean()
 {
  return false;
 }

 @Override
 public double getValueAsDouble()
 {
  return 0;
 }

 @Override
 public int getValueAsInteger()
 {
  return 0;
 }

 @Override
 public void setBooleanValue(boolean boolValue)
 {
  value = String.valueOf(boolValue);
 }

 @Override
 public void setDoubleValue(double doubleValue)
 {
  value = String.valueOf(doubleValue);
 }

 @Override
 public void setIntValue(int intValue)
 {
  value = String.valueOf(intValue);
 }

 @Override
 public void setValue(Object val)
 {
  value=val.toString();
 }
 
 @Override
 public AgeAttributeWritable createClone(AttributedWritable host)
 {
  AgeStringAttributeImpl clone  = new AgeStringAttributeImpl(getClassReference(), host);
  clone.value=this.value;
  
  cloneAttributes( clone );
  
  return clone;
 }
 
 @Override
 public boolean equals( Object ob )
 {
  if( ! (ob instanceof AgeAttribute) )
   return false;
  
   return value.equals( ((AgeAttribute)ob).getValue() );
 }

 @Override
 public int compareTo(AgeAttribute o)
 {
  return value.toString().compareTo(o.getValue().toString());
 }

 @Override
 public int hashCode()
 {
  return value.hashCode();
 }
 
}
