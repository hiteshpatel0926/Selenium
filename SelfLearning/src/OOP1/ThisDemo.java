package OOP1;

public class ThisDemo {

	int i=5;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ThisDemo d=new ThisDemo();
		d.demo();
		
	}
	
	public void demo() {
		
		int i=7;
		this.i=6;
		System.out.println(i);
		System.out.println(this.i);
	}
	
}
