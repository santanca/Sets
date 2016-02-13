/* Student	Information
* -------------------
*	 Student	Name:	Santana Penner,	Cesar Antonio
*	 Student	Number:	1411598
*	 Course	Code:	CS/SE	2XB3
*	 Lab	Section:	04
*	
*	 I	attest	that	the	following	code	being	submitted	is	my	own	individual	
	work.
*/
package cas.xb3.a1.wt;
import java.util.*;

public interface SetInterface {
	Set Union(Set S);			//return the union of two sets
	Set Intersection(Set S);	//return the intersection of two sets
	Set Difference(Set S);		//return the difference of two sets
	Set Product(Set S);			//return the product of two sets
	Boolean isEqual(Set S);					//checks if two sets are equal
	Boolean isSubset(Set S);					//checks if set R is a subset of set S
	int getCount();							//returns the number of elements in this set
	int getCardinality();					//return the number of unique elements 
	String toString();						//returns the elements of the set in the form: {e1, e2, ..., en}
	
	
}
