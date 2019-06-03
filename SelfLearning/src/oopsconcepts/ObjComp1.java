package oopsconcepts;

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
        
        
        
		
	}

}
