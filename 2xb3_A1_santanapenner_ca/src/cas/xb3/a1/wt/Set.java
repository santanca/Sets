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

public class Set implements SetInterface{
	private ArrayList<String> Elements;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}
	
	//Constructor 
	public Set(){
		//read from input file***********************
		this.Elements = new ArrayList<String>();
	}
	
	//Constructor with string array
	public Set(String[] array){
		this.Elements = new ArrayList<String>();
		for(String myString: array){
			this.Elements.add(myString);
		}
	}
	
	//Add an element to a set
	public void add(String x){
		this.Elements.add(x);
	}
	
	//delete an element in a set
	public void del(String x){
		this.Elements.remove(x);
	}
	
	//Copy one Set to another
	public void copy(Set S){
		for(String myString: S.Elements){
			this.add(myString);
		}
	}
	
	//reduce a set such that each element is unique
	public Set reduce(){
		Set newSet = new Set();
		newSet.copy(this); 		//copy the set so that the original remains the same
		
		//delete the first occurrence of elements that have duplicates
		int counter = 0;
		for(int i = this.Elements.size()-1; i > 0;i--){
			for(int j = i-1; j >= 0; j--){
				if (this.Elements.get(i).equals(this.Elements.get(j))){
					newSet.del(this.Elements.get(j));
					i--;
				}
			}
		}
		return newSet;
	}
	
	//checks if a string is in a Set
	public static Boolean contains(String myString, Set other){
		Boolean Contains = false;
		
		//check if the string can be found in the set
		for(int i = 0; i < other.Elements.size(); i++){
			if(myString.equals(other.Elements.get(i))){
				Contains =true;
			}
		}
		return Contains;
	}
	
	//checks if a set is empty
	public Boolean isEmpty(){
		if(this.getCount()==1 && this.Elements.get(0).equals("")) return true;
		return false;
	}
	

	//union of two sets
	@Override
	public Set Union(Set S) {
		Set newSet = new Set();
		//add all the elements into a new set
		for(String myString : this.Elements){
			newSet.add(myString);
		}
		for(String myString : S.Elements){
			newSet.add(myString);
		}
		//reduce the set so that there are no duplicates
		newSet = newSet.reduce();
		return newSet;
	}

	//intersection of two sets
	@Override
	public Set Intersection(Set other){
		Set newSet = new Set();
		Set R = new Set();
		Set S = new Set();
		
		//copy the other set to new set (to maintain the values of the old set)
		R.copy(other);
		R = R.reduce();
		S.copy(this);
		S = S.reduce();
		
		//Go through both sets and and see if there are elements that are in both sets
		for (String myString : S.Elements){
			for (int i = 0; i < R.Elements.size();i++){
				if (R.Elements.get(i).equals(myString)){
					newSet.add(myString);
				}
				
			}
		}
		return newSet.reduce();
	}

	//the difference of two sets
	public Set Difference(Set other){
		Set newSet = new Set();
		newSet.copy(this);
		newSet = newSet.reduce();
		Set S = other.reduce();
		
		//go through both sets, then remove all the elements that the other set has
		for(String myString : S.Elements){
			for(int i = 0 ; i < newSet.Elements.size(); i++){
				if(myString.equals(newSet.Elements.get(i))){
					newSet.del(myString);
				}
			}
			
		}
		return newSet;
	}

	//product of two sets
	@Override
	public Set Product(Set other) {
		//Declaring new sets, and copying them so that the original set maintain there old value
		Set R = new Set();
		Set S = new Set();
		Set newSet = new Set();
		R = this.reduce();
		S = other.reduce();
		
		//if one of the sets is empty, the product will be the empty set
		if(other.isEmpty() || this.isEmpty()){
			return newSet;
		}else{
			//generate the product of the two sets
			for(int i =0; i < R.getCount(); i++){
				for(int j =0; j < S.getCount();j++){
					newSet.add("(" + R.Elements.get(i) + "," + S.Elements.get(j)+ ")");
				}
			}
			return newSet;
		}
	}

	//check if two sets are equal
	@Override
	public Boolean isEqual(Set other) {
		//Declare new sets, and variables 
		Set R = new Set();
		Set S = new Set();
		int counter = 0;
		Boolean equal = true;
		
		//reduce sets to eliminate duplicates
		R = this.reduce();
		S = other.reduce();
		
		//if the sizes are not the same, the sets cannot be equal
		if (R.getCount() != S.getCount()){
			return false;
		}else{
			//two sets are equal iff R is a subset of S and S is a subset of B
			if(R.isSubset(S) == true && S.isSubset(R)){
				return true;
			}else{
				return false;
			}
		}
	}

	//check if a set is a subset of another set
	@Override
	public Boolean isSubset(Set other) {
		Boolean subset = false;
		
		//go through both sets
		for(String myString: this.Elements){
			for(int i = 0; i < other.getCount(); i++){
				//if both sets dont have the same character, it can't be a subset
				if (contains(myString,other)){
					subset = true;
				}else{
					return false;
				}
			}
			
		}
		
		return subset;
	}

	//return the number of elements in a set
	@Override
	public int getCount() {
		int counter = 0;
		//count the number of elements in a set
		for(int  i = 0; i< this.Elements.size();i++){
			if(this.Elements.get(i).equals("")){
				return 0;
			}
			counter +=1;
		}
		return counter;
	}

	//return the number of unique elements in this set
	@Override
	public int getCardinality(){
		Set R = new Set();
		R = this.reduce();
		
		return R.getCount();
	}

	//return the elements of the set in the format: {e1,e2,e3...}
	@Override
	public String toString() {
		//print the set in the specified format
		String output= "{";
		boolean firstElement = true;
		for(String myString : this.Elements){
			if (firstElement == true){
				output += myString;
				firstElement = false;
			}else{
				if(myString!="") output += "," + myString;
			}
		}
		output += "}";
		return output;
	}

}
