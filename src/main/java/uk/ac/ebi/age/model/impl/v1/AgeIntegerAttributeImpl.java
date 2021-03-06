package uk.ac.ebi.age.model.impl.v1;

import uk.ac.ebi.age.model.AgeAttribute;
import uk.ac.ebi.age.model.AttributeClassRef;
import uk.ac.ebi.age.model.FormatException;
import uk.ac.ebi.age.model.writable.AgeAttributeWritable;
import uk.ac.ebi.age.model.writable.AttributedWritable;


public class AgeIntegerAttributeImpl extends AgeAttributeImpl
{
 private static final long serialVersionUID = 3L;

 private int value; 

 protected AgeIntegerAttributeImpl(AttributeClassRef attrClass, AttributedWritable host)
 {
  super(attrClass, host);
 }

 @Override
 public Object getValue()
 {
  return value;
 }

 @Override
 public void updateValue(String val) throws FormatException
 {
  val=val.trim();
  
  if( val.length() == 0 )
   return;
  
  try
  { 
   value=Integer.parseInt(val);
  }
  catch (NumberFormatException e) 
  {
   throw new FormatException("Invalid integer format",e);
  }
 }


 @Override
 public boolean getValueAsBoolean()
 {
  return value!=0;
 }

 @Override
 public double getValueAsDouble()
 {
  return value;
 }

 @Override
 public int getValueAsInteger()
 {
  return value;
 }

 @Override
 public void setBooleanValue(boolean boolValue)
 {
  value=boolValue?1:0;
 }

 @Override
 public void setDoubleValue(double doubleValue)
 {
  value=(int)Math.round(doubleValue);
 }

 @Override
 public void setIntValue(int intValue)
 {
  value=intValue;
 }

 @Override
 public void setValue(Object val)
 {
  if( val instanceof Number )
   value=((Number)val).intValue();
  else try
  {
   value = Integer.parseInt(val.toString());
  }
  catch (Exception e)
  {
  }
 }
 
 @Override
 public AgeAttributeWritable createClone( AttributedWritable host)
 {
  AgeIntegerAttributeImpl clone  = new AgeIntegerAttributeImpl(getClassReference(), host);
  clone.value=this.value;
  
  cloneAttributes( clone );

  return clone;
 }
 
 @Override
 public boolean equals( Object ob )
 {
  if( ! (ob instanceof AgeAttribute) )
   return false;
  
   return value == ((AgeAttribute)ob).getValueAsInteger();
 }
 
 @Override
 public int compareTo(AgeAttribute o)
 {
  return value-o.getValueAsInteger();
 }
}
