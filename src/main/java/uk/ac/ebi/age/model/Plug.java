package uk.ac.ebi.age.model;

import uk.ac.ebi.age.AgeResolver;

public interface Plug
{
 void unplug();
 boolean plug( AgeResolver rslv );
 
 boolean isPlugged();
}
