package main.java;

import java.util.ArrayList;
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
        return 0; //implement me, will be important for assignment 
        //4 (nothing to do here for assignment 3)
    }

    // calculates how much was saved in the current 
    //shopping cart based on the deals, returns the saved amount
    // throws exception if alcohol is bought from underage person
    // TODO: Create node graph for this method in assign 4: 
    //create white box tests and fix the method, reach at least 98% coverage
    public int Amount_saved() throws UnderAgeException {
        int subTotal = 0;
        int costAfterSavings = 0;

        double produce_counter = 0;
        int alcoholCounter = 0;
        int frozenFoodCounter = 0;
        int dairyCounter = 0;

        for(int i = 0; i < cart.size(); i++) {
            subTotal += cart.get(i).getCost();
            costAfterSavings = costAfterSavings + cart.get(i).getCost();
            //SER316 TASK 2 SPOTBUGS FIX
            if (cart.get(i).getClass().toString().equals( Produce.class.toString())) {
                produce_counter++;

                if (produce_counter >= 3) {
                    costAfterSavings -= 1;
                    produce_counter = 0;
                }
            }
          //SER316 TASK 2 SPOTBUGS FIX
            else if (cart.get(i).getClass().toString().equals(Alcohol.class.toString())) {
                alcoholCounter++;
                if (userAge < 21) {
                    throw new UnderAgeException("The User is not of age to purchase alcohol!");
                }
            }
          //SER316 TASK 2 SPOTBUGS FIX
            else if (cart.get(i).getClass().toString().equals(FrozenFood.class.toString())) {
                frozenFoodCounter++;
            }
          //SER316 TASK 2 SPOTBUGS FIX
            else if (cart.get(i).getClass().toString().equals(FrozenFood.class.toString())) {
                dairyCounter++;
            }

            if (alcoholCounter >= 1 && frozenFoodCounter >= 1) {
                costAfterSavings = costAfterSavings + 3;
                alcoholCounter--;
                frozenFoodCounter--;
            }
        }

        return subTotal - costAfterSavings;
    }

    // Gets the tax based on state and the total
    public double getTax(double totalBt, String twoLetterUsStateAbbreviation) {
        double newTotal = 0;
        switch (twoLetterUsStateAbbreviation) {
            case "AZ":
                newTotal = totalBt * .08;
                break;
            case "CA":
                newTotal = totalBt * .09;
                break;
            case "NY":
                newTotal = totalBt * .1;
              //SER316 TASK 2 SPOTBUGS FIX
                break;
           case "CO":
                newTotal = totalBt * .07;
                break;
            default:
                return totalBt;
        }
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
