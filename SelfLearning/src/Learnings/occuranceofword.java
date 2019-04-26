package Learnings;
 
public class occuranceofword {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// Java program to count the number 
		// of occurrence of a word in 
		// the given string given string 
	
		String str = "GeeksforGeeks A computer science portal for geeks geeks geeks portal Portal "; 
		String word = "geeks"; 
		System.out.println(countOccurences(str, word)); 
		
	
}

		static int countOccurences(String str, String word) 
		{ 
			// split the string by spaces in a 
			String a[] = str.split(" "); 

			// search for pattern in a 
			int count = 0; 
			for (int i = 0; i < a.length; i++) 
			{ 
			// if match found increase count 
			if (word.equals(a[i])) 
				count++; 
			} 

			return count; 
		} 

		
}		
	


