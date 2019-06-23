package Collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class SortHMapbyKey {
	
	// This map stores unsorted values 
    static Map<String, Integer> map = new HashMap<>(); 
  
    // Function to sort map by Key 
    public static void sortbykey() 
    { 
        ArrayList<String> sortedKeys = new ArrayList<String>(map.keySet()); 
          
        Collections.sort(sortedKeys);  
  
        // Display the TreeMap which is naturally sorted 
        for (String x : sortedKeys)  
            System.out.println("Key = " + x +  
                        ", Value = " + map.get(x));      
    } 
      
    // Driver Code 
    public static void main(String args[]) 
    { 
        // putting values in the Map 
        map.put("Jayant", 80); 
        map.put("Abhishek", 90); 
        map.put("ZAnushka", 80); 
        map.put("WAmit", 75); 
        map.put("Danish", 40); 
  
        // Calling the function to sortbyKey 
        sortbykey(); 
    } 

}
