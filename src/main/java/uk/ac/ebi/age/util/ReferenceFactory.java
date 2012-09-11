package uk.ac.ebi.age.util;

import java.lang.ref.Reference;
import java.lang.ref.SoftReference;

public class ReferenceFactory
{
 public static <T> Reference<T> getReference( T obj )
 {
  return new SoftReference<T>(obj);
 }
}
