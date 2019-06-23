package OOP1;

import java.util.Scanner;

public class SwapNumbers {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int i,j;
		Scanner sc=new Scanner(System.in);
		
		System.out.println("Enter First Number: ");
        i = sc.nextInt();
        System.out.println("Enter Second Number: ");
        j = sc.nextInt();
        	
        sc.close();
        
		i= i+j;
		j= i-j;
		i=i-j;

		System.out.println(i);
		System.out.println(j);
	}

}
