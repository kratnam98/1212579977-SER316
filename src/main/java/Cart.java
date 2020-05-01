package main.java;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class Cart {
  //SER316 TASK 2 SPOTBUGS FIX
    public int userAge;
    public List<Product> cart;
  //SER316 TASK 2 SPOTBUGS FIX
    //int cartStorage;

    /**
     * Calculates the final cost after all savings and tax has 
     * been applied. Also checks
     * that the user is of age to purchase alcohol if it is in their 
     * cart at checkout. Sales tax is always AZ tax.
     * Calculation is based off of the following prices and deals:
     * Dairy -> $3
     * Meat -> $10
     * Produce -> $2 or 3 for $5
     * Alcohol -> $8
     * Frozen Food -> $5
     * Alcohol + Frozen Food -> $10
     * If there is an alcohol product in the cart and the user is under 21, then an
     * UnderAgeException should be thrown.
     * @return double totalCost
     * @throws UnderAgeException
     */
    
    public double calcCost() throws UnderAgeException {
        //return 0; //implement me, will be important for assignment 
        //4 (nothing to do here for assignment 3)
        double fin = 0;
        for(int i = 0; i < cart.size(); i++) {
            fin = fin + cart.get(i).getCost();
        }
        int save = Amount_saved();
        fin = fin - save;
        fin = fin + getTax(fin, "AZ");
        return fin;
        
    }

    // calculates how much was saved in the current 
    //shopping cart based on the deals, returns the saved amount
    // throws exception if alcohol is bought from underage person
    // TODO: Create node graph for this method in assign 4: 
    //create white box tests and fix the method, reach at least 98% coverage
    
    
    
    
    public int Amount_saved() throws UnderAgeException {
        int subTotal = 0;
        int costAfterSavings = 0;
        int produce_counter = 0;
        boolean alcc = false;
        for(int i = 0; i < cart.size(); i++) {
            subTotal += cart.get(i).getCost();
            costAfterSavings = costAfterSavings + cart.get(i).getCost();
            if (cart.get(i).getClass().toString().equals( Produce.class.toString())) {
                produce_counter++;
            }
            else if (cart.get(i).getClass().toString().equals(Alcohol.class.toString())) {
                alcc = true;
                if (userAge < 21) {
                    throw new UnderAgeException("The User is not of age to purchase alcohol!");
                }
            }
            else if (cart.get(i).getClass().toString().equals(FrozenFood.class.toString())) {
                if(alcc) {
                    costAfterSavings = costAfterSavings - 3;
                    alcc = false;
                }
            }
        }
        int x = produce_counter % 3;
        costAfterSavings = costAfterSavings-x;
        return subTotal - costAfterSavings;
    }

    // Gets the tax based on state and the total
    public double getTax(double totalBt, String let) {
        double newTotal = totalBt;
        Hashtable<String, Double> hash = new Hashtable<String, Double>();
        hash.put("AZ", .08);
        hash.put("CA", .09);
        hash.put("NY", .10);
        hash.put("CO", .07);
        newTotal = totalBt * hash.get(let);
        return newTotal;
    }

    public void addItem(Product np) {
      cart.add(np);
    }

    public boolean removeItem(Product productToRemove) {
        boolean test = false;
        for (int i = 0; i < cart.size(); i++) {
            if (cart.get(i) == productToRemove) {
                cart.remove(i);
                test = true;
                return test;
            }
        }
        return false;
    }
    public Cart(int age) {
        userAge = age;
        cart = new ArrayList<Product>();
    }
}
