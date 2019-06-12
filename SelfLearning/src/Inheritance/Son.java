package Inheritance;

public class Son extends Parent{
	
	
	public void S1() {

		String s1 = "Method S1";
		System.out.println("Method P1 Invoked");
	}

	public void S2() {

		String s2 = "Method S2";
		System.out.println("Method P2 Invoked");
	}
	
	public Son()
	{
		super.P1();
		super.P2();
		System.out.println(super.P3);
		System.out.println("Son constructor Invoked");
	}

	public static void main(String[] args) {
	
	Son s=new Son();
	
	}
}
