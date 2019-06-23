package Collection;

import java.util.ArrayList;

public class AddAll {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ArrayList<Integer> arrlist1 = new ArrayList<Integer>(); 
		// use add() method to add elements in the list 
        arrlist1.add(12); 
        arrlist1.add(20); 
        arrlist1.add(45); 
  
        // prints all the elements available in list1 
        System.out.println("Printing list1:"); 
        for (Integer number : arrlist1)  
            System.out.println("Number = " + number);         
  
        // create an empty array list2 with an initial 
        // capacity 
        ArrayList<Integer> arrlist2 =  
                             new ArrayList<Integer>(5); 
  
        // use add() method to add elements in list2 
        arrlist2.add(25); 
        arrlist2.add(30); 
        arrlist2.add(31); 
        arrlist2.add(35); 
  
        // let us print all the elements available in  
        // list2 
        System.out.println("Printing list2:"); 
        for (Integer number : arrlist2)  
            System.out.println("Number = " + number);         
  
        // inserting all elements, list2 will get printed 
        // after list1 
        arrlist1.addAll(arrlist2); 
  
        System.out.println("Printing all the elements"); 
        // let us print all the elements available in  
        // list1 
        for (Integer number : arrlist1)  
            System.out.println("Number = " + number);         
    
        
        ArrayList<String> sarrlist1 = new ArrayList<String>();
        
        sarrlist1.add("1.Hitesh");
        sarrlist1.add("1.Ravi");
        sarrlist1.add("1.Jatan");
        
        System.out.println("Printing List 1:"); 
        System.out.println(sarrlist1);
        ArrayList<String> sarrlist2 = new ArrayList<String>();
     
        sarrlist2.add("2.Hitesh");
        sarrlist2.add("2.Ravi");
        sarrlist2.add("2.Jatan");
        System.out.println("Printing List 2:"); 
        System.out.println(sarrlist2);
        
        sarrlist2.addAll(sarrlist1);
        System.out.println("Printing List1 added at the End of List2:"); 
        System.out.println(sarrlist2);
	}

}
