package Calculator;

import java.util.ArrayList;
import java.util.Scanner;

public class ArmstrongNum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter Number: ");
        int i = sc.nextInt();
		sc.close();
        
		int originalnum=i;
		int count = 0, num = i;

        while(num != 0)
        {
            // num = num/10
            num /= 10;
            ++count;
        }

        System.out.println("Number of digits: " + count);
         
        int result=0;
        
        while(i!=0)
        {
        	int reminder = i%10;
        	result+=Math.pow(reminder,3) ;
        	i/=10;
        }
        
        System.out.println(result);
        
        if(result == originalnum)
            System.out.println(originalnum + " is an Armstrong number.");
        else
            System.out.println(originalnum + " is not an Armstrong number.");
	}

}
