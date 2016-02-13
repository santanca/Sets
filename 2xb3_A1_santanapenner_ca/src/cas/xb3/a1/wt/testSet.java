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
//import packages
package cas.xb3.a1.wt;
import java.io.File;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.*;

//this class tests the implementation of the Set class
public class testSet {
	//main method
	public static void main(String[] args){
		Scanner input;
		//try/catch for file reading and writing
		try {
			
			//declare scanner and array lists to input each line of the input.txt
			Scanner sc = new Scanner(new File("input.txt"));
			ArrayList<String> lines = new ArrayList<String>();
			ArrayList<Set> inputSets = new ArrayList<Set>();
			//read each line of the input file and put it into an arraylist of Strings
			while(sc.hasNextLine()){
				lines.add(sc.nextLine());
			}
			
			//convert the arrayList of string to an arrayList of sets
			for(int i = 0; i < lines.size();i++){
				//System.out.println(lines.get(i));
				String current = new String();
				current = lines.get(i);
				current = current.replace("{", "");
				current = current.replace("}", "");
				String elements[] = current.split(",");
				Set A = new Set(elements);
				inputSets.add(A);
			}
			sc.close();
			
			//run test cases
			PrintStream ps = new PrintStream("output.txt");		//declare output 
		    System.setOut(ps);
			testUnion(inputSets);
			testIntersection(inputSets);
			testDifference(inputSets);
			testProduct(inputSets);
			testIsEqual(inputSets);
			testIsSubset(inputSets);
			testGetCount(inputSets);
			testGetCardinality(inputSets);
			testToString(inputSets);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	//method that checks if two sets (inputed as strings) are equivalent 
	public static Boolean equal(Set mySet,String answer){
		//convert the strings to arraylists
		ArrayList<String>  other =new ArrayList<String>();
		ArrayList<String> ans = new ArrayList<String>();
		String current = new String();
		current = mySet.toString();
		current = current.replace("{", "");
		current = current.replace("}", "");
		other = split(current,",");
		answer= answer.replace("{", "");
		answer  = answer.replace("}", "");
		ans = split(answer,",");
		
		//compare if the two array lists are equal
		if(other.containsAll(ans) && ans.containsAll(other)){
			return true;
		}else{
			return false;
		}
	}
	
	//converts a string to an array list by splitting at each occurrence of mysplit, e.g. ","
	public static ArrayList<String> split(String myString, String mysplit){
		//declare and array and array list (since String.split() is only for array's, not array lists)
		String[] arr = new String[20];
		ArrayList<String> arr1 = new ArrayList<String>();
		
		arr = myString.split(mysplit);
		//input each element into the array list
		for (int i =0; i<arr.length;i++){
			arr1.add(arr[i]);
		}
		
		return arr1;	
	}
	

	//method that prints passed or failed based on the boolean value at each index of an array list
	public static void printResults(ArrayList<Boolean> results){
		//print the results for each test case
		for(int i = 0; i < results.size();i++){
			System.out.print("Test Case " + (i+1) + ": ");
			//write if the test case produced the correct result for each test case 
			if(results.get(i)==true){
				System.out.println("Passed");
			}else{
				System.out.println("Failed");
			}
		}
		System.out.println("");
	}
	
	
	//method that compares two array lists and see's if they have the same boolean values
	public static void printResults(ArrayList<Boolean> results, ArrayList<Boolean> answers){
		for(int i=0;i<results.size();i++){
			System.out.print("Test Case " + (i+1) + ": ");
			if((results.get(i) == true && answers.get(i)==true)||(results.get(i) == false && answers.get(i)==false)){
				System.out.println("Passed");
			}else{
				System.out.println("Failed");
			}
		}
		System.out.println("");
	}
	
	//method that compares two array lists and see's if they have the same boolean values
	public static void printIntResults(ArrayList<Integer> results, ArrayList<Integer> answers){
		for(int i=0;i<results.size();i++){
			System.out.print("Test Case " + (i+1) + ": ");
			if((results.get(i) == answers.get(i))){
				System.out.println("Passed");
			}else{
				System.out.println("Failed");
			}
		}
		System.out.println("");
	}
	
		//test the implementation of Union of two sets
	public static void testUnion(ArrayList<Set> inputSets){
		System.out.println("Testing Union");
		Set other = new Set();
		ArrayList<Boolean> results = new ArrayList<Boolean>();
		
		//test case 1
		other = inputSets.get(0).Union(inputSets.get(1));
		results.add(equal(other,"{1,2,3,4,5,6,7,8}"));
		//test case 2 
		other = inputSets.get(3).Union(inputSets.get(4));
		results.add(equal(other,"{a,b,c}"));
		//test case 3
		other = inputSets.get(7).Union(inputSets.get(8));
		results.add(equal(other,"{-11,5,hello,cesar,is,cool}"));
		//test case 4
		other = inputSets.get(6).Union(inputSets.get(9));
		results.add(equal(other,"{hello,from,the,other,side}"));
		
		//test case 5
		other = inputSets.get(2).Union(inputSets.get(3));
		results.add(equal(other,"{a,b,c,d}"));
		
		//print results
		printResults(results);
	}
	
	//test the implementation of Intersection bewteen two sets
	public static void testIntersection(ArrayList<Set> inputSets){
		System.out.println("Testing Intersection");
		Set other = new Set();
		ArrayList<Boolean> results = new ArrayList<Boolean>();
		
		//test case 1
		other = inputSets.get(0).Intersection((inputSets.get(1)));
		results.add(equal(other,"{5}"));
		
		//test case 2
		other = inputSets.get(0).Intersection((inputSets.get(9)));
		results.add(equal(other,"{}"));
		
		//test case 3
		other = inputSets.get(5).Intersection((inputSets.get(6)));
		results.add(equal(other,"{hello}"));
		
		//test case 4
		other = inputSets.get(3).Intersection((inputSets.get(2)));
		results.add(equal(other,"{a,b}"));
		
		//test case 5
		other = inputSets.get(7).Intersection((inputSets.get(8)));
		results.add(equal(other,"{-11,5,hello,cesar}"));
		
		//print results
		printResults(results);
	}
	
	//test the implementation of the difference of two sets
	public static void testDifference(ArrayList<Set> inputSets){
		System.out.println("Testing Difference");
		Set other = new Set();
		ArrayList<Boolean> results = new ArrayList<Boolean>();
		
		//test case 1
		other = inputSets.get(0).Difference((inputSets.get(1)));
		results.add(equal(other,"{6,7,8}"));
		
		//test case 2
		other = inputSets.get(1).Difference((inputSets.get(0)));
		results.add(equal(other,"{1,2,3,4}"));
		
		//test case 3
		other = inputSets.get(6).Difference((inputSets.get(9)));
		results.add(equal(other,"{hello,from,the,other,side}"));
		
		//test case 4
		other = inputSets.get(9).Difference((inputSets.get(6)));
		results.add(equal(other,"{}"));
		
		//test case 5
		other = inputSets.get(8).Difference((inputSets.get(7)));
		results.add(equal(other,"{is,cool}"));
		
		//print results
		printResults(results);
	}
	
	//test the Cartesian product of two sets
	public static void testProduct(ArrayList<Set> inputSets){
		System.out.println("Testing Cartesian Product");
		Set other = new Set();
		ArrayList<Boolean> results = new ArrayList<Boolean>();
		
		//test case 1
		other = inputSets.get(4).Product((inputSets.get(5)));
		results.add(equal(other,"{(a,hello),(a,my),(a,name),(a,is),(b,hello),(b,my),(b,name),(b,is),(c,hello),(c,my),(c,name),(c,is)}"));
		
		//test case 2
		other = inputSets.get(1).Product((inputSets.get(9)));
		results.add(equal(other,"{}"));
		
		//test case 3
		other = inputSets.get(3).Product((inputSets.get(4)));
		results.add(equal(other,"{(a,a),(a,b),(a,c),(b,a),(b,b),(b,c)}"));
		
		//test case 4
		other = inputSets.get(9).Product((inputSets.get(6)));
		results.add(equal(other,"{}"));
		
		//test case 5
		other = inputSets.get(0).Product((inputSets.get(4)));
		results.add(equal(other,"{(5,a),(5,b),(5,c),(6,a),(6,b),(6,c),(7,a),(7,b),(7,c),(8,a),(8,b),(8,c)}"));
		
		//print results
		printResults(results);
		
	}
	
	//test if two sets are equal
	public static void testIsEqual(ArrayList<Set> inputSets){
		System.out.println("Testing Equality");
		Set other = new Set();
		//one array list to keep track of the results, and one for the answers
		ArrayList<Boolean> results = new ArrayList<Boolean>();
		ArrayList<Boolean> answers = new ArrayList<Boolean>(){{
			add(false);
			add(true);
			add(false);
			add(false);
			add(false);
		}};
		
		//test case 1
		results.add(inputSets.get(0).isEqual(inputSets.get(1)));
		//test case 2
		results.add(inputSets.get(3).isEqual(inputSets.get(10)));
		//test case 3
		results.add(inputSets.get(0).isEqual(inputSets.get(1)));
		//test case 4
		results.add(inputSets.get(0).isEqual(inputSets.get(1)));
		//test case 5
		results.add(inputSets.get(0).isEqual(inputSets.get(1)));
	
		//print results
		printResults(results,answers);
	}
	
	//testing if the implementation of isSubset is correct (bewtee two sets)
	public static void testIsSubset(ArrayList<Set> inputSets){
		System.out.println("Testing is Subset");
		Set other = new Set();
		//one array list to keep track of the results, and one for the answers
		ArrayList<Boolean> results = new ArrayList<Boolean>();
		ArrayList<Boolean> answers = new ArrayList<Boolean>(){{
			add(false);
			add(true);
			add(true);
			add(true);
			add(false);
		}};
		
		//test case 1
		results.add(inputSets.get(0).isSubset(inputSets.get(1)));
		//test case 2
		results.add(inputSets.get(3).isSubset(inputSets.get(10)));
		//test case 3
		results.add(inputSets.get(7).isSubset(inputSets.get(8)));
		//test case 4
		results.add(inputSets.get(4).isSubset(inputSets.get(2)));
		//test case 5
		results.add(inputSets.get(5).isSubset(inputSets.get(6)));
	
		//print results
		printResults(results,answers);
	}
	
	//testing the get count (size of a set)
	public static void testGetCount(ArrayList<Set> inputSets){
		System.out.println("Testing Get Count");
		Set other = new Set();
		//one array list to keep track of the results, and one for the answers
		ArrayList<Integer> results = new ArrayList<Integer>();
		ArrayList<Integer> answers = new ArrayList<Integer>(){{
			add(5);
			add(4);
			add(0);
			add(4);
			add(4);
		}};
		
		//test case 1
		results.add(inputSets.get(0).getCount());
		//test case 2
		results.add(inputSets.get(3).getCount());
		//test case 3
		results.add(inputSets.get(9).getCount());
		//test case 4
		results.add(inputSets.get(5).getCount());
		//test case 5
		results.add(inputSets.get(7).getCount());
		
		//print results
		printIntResults(results,answers);
	}
	
	
	//method that tests the implementation of cardinality
	public static void testGetCardinality(ArrayList<Set> inputSets){
		System.out.println("Testing Cardinality");
		Set other = new Set();
		//one array list to keep track of the results, and one for the answers
		ArrayList<Integer> results = new ArrayList<Integer>();
		ArrayList<Integer> answers = new ArrayList<Integer>(){{
			add(4);
			add(2);
			add(6);
			add(4);
			add(4);
		}};
		
		//test case 1
		results.add(inputSets.get(0).getCardinality());
		//test case 2
		results.add(inputSets.get(3).getCardinality());
		//test case 3
		results.add(inputSets.get(8).getCardinality());
		//test case 4
		results.add(inputSets.get(5).getCardinality());
		//test case 5
		results.add(inputSets.get(7).getCardinality());
	
		//print results
		printIntResults(results,answers);
	}
	
	//test the implementation of toString()
	public static void testToString(ArrayList<Set> inputSets){
		System.out.println("Testing toString");
		Set other = new Set();
		ArrayList<String> results = new ArrayList<String>();
		ArrayList<String> answers = new ArrayList<String>(){{
			add("{5,6,7,8,7}");
			add("{a,a,a,b}");
			add("{-11,5,hello,cesar}");
			add("{a,b,c}");
			add("{hello,my,name,is}");
		}};
		
		//test case 1
		results.add(inputSets.get(0).toString());
		//test case 2
		results.add(inputSets.get(3).toString());
		//test case 3
		results.add(inputSets.get(7).toString());
		//test case 4
		results.add(inputSets.get(4).toString());
		//test case 5
		results.add(inputSets.get(5).toString());
	
		//print results
		for(int i=0;i<results.size();i++){
			System.out.print("Test Case " + (i+1) + ": ");
			if((results.get(i).equals(answers.get(i)))){
				System.out.println("Passed");
			}else{
				System.out.println("Failed");
			}
		}
		System.out.println("");
	}

}
