package Oops;

public class Poly {

	public int poly(int x, int y) 
    { 
        return (x + y); 
    } 
  
    // Overloaded sum(). 
    // This sum takes three int parameters 
    public int poly(int x, int y, int z) 
    { 
        return (x + y + z); 
    } 
  
    // Overloaded sum(). 
    // This sum takes two double parameters 
    public double poly(double x, double y) 
    { 
        return (x + y); 
    } 
  
    // Driver code 
    public static void main(String args[]) 
    { 
        Poly s = new Poly(); 
        System.out.println(s.poly(10, 20)); 
        System.out.println(s.poly(10, 20, 30)); 
        System.out.println(s.poly(10.5, 20.5)); 
    } 
}
