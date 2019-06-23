package OOP1;

public class ObjComp1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String s1 = new String("HELLO");
		String s2 = new String("HELLO");

		System.out.println(s1 == s2);
		System.out.println(s1.equals(s2));

		// integer-type
		System.out.println(10 == 20);

		// char-type
		System.out.println('a' == 'b');

		// char and double type
		System.out.println('a' == 97.0);

		// boolean type
		System.out.println(true == true);

		Thread t = new Thread();
		Object o = new Object();
		String s = new String("GEEKS");

		System.out.println(t == o);
		System.out.println(o == s);

		// Uncomment to see error
		// System.out.println(t==s);

		Thread t1 = new Thread();
		Thread t2 = new Thread();
		Thread t3 = t1;

		String ss1 = new String("GEEKS");
		String ss2 = new String("GEEKS");

		System.out.println(t1 == t3);
		System.out.println(t1 == t2);

		System.out.println(t1.equals(t2));
		System.out.println(ss1.equals(ss2));

		int a = 1;
		int b = 2;
		int c = 3;
		a += 5;
		b *= 4;
		c += a * b;
		c %= 6;
		System.out.println("a = " + a);
		System.out.println("b = " + b);
		System.out.println("c = " + c);

		// Java Unary Operator Example: ++ and --

		int x = 10;
		System.out.println(x++);// 10 (11)
		System.out.println(++x);// 12
		System.out.println(x--);// 12 (11)
		System.out.println(--x);// 10

		int a1 = 10;
		int b1 = 10;
		System.out.println(a1++ + ++a1);// 10+12=22
		System.out.println(b1++ + b1++);// 10+11=21

		// Java Unary Operator Example: ~ and !

		int a2 = 10;
		int b2 = -10;
		boolean c2 = true;
		boolean d2 = false;
		System.out.println(~a2);// -11 (minus of total positive value which starts from 0)
		System.out.println(~b2);// 9 (positive of total minus, positive starts from 0)
		System.out.println(!c2);// false (opposite of boolean value)
		System.out.println(!d2);// true

		// Java Arithmetic Operator Example

		int a3 = 10;
		int b3 = 5;
		System.out.println(a3 + b3);// 15
		System.out.println(a3 - b3);// 5
		System.out.println(a3 * b3);// 50
		System.out.println(a3 / b3);// 2
		System.out.println(a3 % b3);// 0

		System.out.println(10 * 10 / 5 + 3 - 1 * 4 / 2);

		// Java Left Shift Operator Example

		System.out.println(10 << 2);// 10*2^2=10*4=40
		System.out.println(10 << 3);// 10*2^3=10*8=80
		System.out.println(20 << 2);// 20*2^2=20*4=80
		System.out.println(15 << 4);// 15*2^4=15*16=240
		
		//Java Right Shift Operator Example
		
		System.out.println(10>>2);//10/2^2=10/4=2  
		System.out.println(20>>2);//20/2^2=20/4=5  
		System.out.println(20>>3);//20/2^3=20/8=2
		
		int[] arrayint= {1,2,3,4,5};
		System.out.println(arrayint.length);
		System.out.println(arrayint.equals(arrayint));
		
		
	}

}
