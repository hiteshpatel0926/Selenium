package Learnings;

import java.util.Scanner;

public class ReverseString {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		 Scanner sc = new Scanner(System.in);
	       
	        System.out.println("Enter String: ");
	        String S = sc.next();
	      	
		
		int len=S.length();
	
		String R=makereverse(S,len);
		
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
