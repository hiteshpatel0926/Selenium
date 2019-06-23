package Collection;

import java.util.ArrayList;

public class ArrayLst {

	public static void main(String[] args) {
				
		ArrayList<Integer> iarray = new ArrayList<Integer>(); 
		
		
		for (int i=1; i<=5; i++) 
			iarray.add(i); 
  
        // Printing elements 
        System.out.println(iarray); 
  
        // Remove element at index 3 
        iarray.remove(3); 
  
        // Displaying ArrayList after deletion 
        System.out.println(iarray); 
  
        // Printing elements one by one 
        for (int i=0; i<iarray.size(); i++) 
            System.out.print(iarray.get(i)+" "); 
        
       
        ArrayList<String> sarray = new ArrayList<String>();
        sarray.add("Hitesh");
        sarray.add("Ravi");
        sarray.add("Jatan");
        sarray.add(2, "New Added");
        System.out.println(sarray);
	
	}

}
