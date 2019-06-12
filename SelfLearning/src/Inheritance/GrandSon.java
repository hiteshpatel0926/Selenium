package Inheritance;

public class GrandSon extends Son{
	
	
	public GrandSon()
	{
		super.S1();
		super.S2();
		super.P1();
		super.P2();
		System.out.println("GrandSon Constructor Invoked");
	}
	
	public static void main(String[] args) {
	
	GrandSon gs=new GrandSon();
	

}
}