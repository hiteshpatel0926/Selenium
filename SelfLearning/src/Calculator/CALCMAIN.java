package Calculator;

import java.util.Scanner;

public class CALCMAIN {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		float num1, num2, sum, sub, mult, div;
        Scanner sc = new Scanner(System.in);
       
        System.out.println("Enter First Number: ");
        num1 = sc.nextInt();
        System.out.println("Enter Second Number: ");
        num2 = sc.nextInt();
        
        sc.close();
        
        MULT m=new MULT();
        mult=m.multiplication(num1, num2);
       
        SUM s=new SUM();
        sum=s.summation(num1, num2);
        
        SUBTRACT sb=new SUBTRACT();
        sub=sb.subtract(num1, num2);
        
        DIVISION d=new DIVISION();
        div=d.division(num1, num2);
        
        
        System.out.println("Summation of these numbers: "+sum);
        System.out.println("Substraction of these numbers: "+sub);
        System.out.println("Multiplication of these numbers: "+mult);
        System.out.println("Division of these numbers: "+div);
	}

}
