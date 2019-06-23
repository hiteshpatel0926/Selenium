package Collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SortHset {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		// create HashSet of String type
        Set<String> coaches = new HashSet<String>();
 
        // add string-values to HashSet
        coaches.add("Kirsten");
        coaches.add("Anil");
        coaches.add("John");
        coaches.add("Fletcher");
        coaches.add("Madan");
        coaches.add("Bishen");
        coaches.add("Chappell");
 
        // Before Sorting
        System.out.println("Before Sorting :"
                + " HashSet contents in random order\n");
        for(String coach : coaches) {
            System.out.println(coach);
        }
 
        // create ArrayList and store HashSet contents 
        List<String> alCoaches = new ArrayList<String>(coaches);
 
        // sort using Collections.sort(); method
        Collections.sort(alCoaches);
      
 
        // After Sorting
        System.out.println("\n\nAfter Sorting Ascending: Sorted order\n");
        for(String coach : alCoaches) {
            System.out.println(coach);
        }

        Collections.reverse(alCoaches);
        System.out.println("\n\nAfter Sorting descending: Desc order\n");
        for(String coach : alCoaches) {
            System.out.println(coach);
        }
	}

}
