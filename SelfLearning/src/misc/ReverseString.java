package misc;

import java.util.Scanner;

public class ReverseString {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		 Scanner sc = new Scanner(System.in);
	       
	        System.out.println("Enter String: ");
	        String S = sc.next();
	      	
		
		int len=S.length();
	
		String R=makereverse(S,len);
		
		
		String input = "Hello World...You are the BEST"; 
		  
        StringBuilder input1 = new StringBuilder(); 
  
        // append a string into StringBuilder input1 
        input1.append(input); 
  
        // reverse StringBuilder input1 
        input1 = input1.reverse(); 
  
        // print reversed String 
        System.out.println(input1); 
		
		
	}
	
	
	private static String makereverse(String s, int len) {
		
		String T = "";
		
		for(int i=(len-1);i>=0;i--)
		{
			T+=s.charAt(i);
			
		}
		
		System.out.println(T);
		
		if(T.equalsIgnoreCase(s))
		{
			System.out.println("Your Entered String is pelindrom");
		}
		else 
		{
			System.out.println("Your Entered String is not pelindrom");
		}
		return null;
	}


}
