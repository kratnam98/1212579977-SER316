package test.java;

import main.java.*;
import main.java.Alcohol;
import main.java.Dairy;
import main.java.FrozenFood;
import main.java.Produce;
import main.java.UnderAgeException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Collection;

import main.java.Cart;
import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class BlackBoxGiven {

    private Class<Cart> classUnderTest;

    @SuppressWarnings("unchecked")
    public BlackBoxGiven(Object classUnderTest) {
        this.classUnderTest = (Class<Cart>) classUnderTest;
    }

    // Define all classes to be tested
    /*
    @Parameterized.Parameters
    public static Collection<Object[]> cartClassUnderTest() {
        Object[][] classes = {
            {Cart0.class},
            {Cart1.class},
            {Cart2.class},
            {Cart3.class},
            {Cart4.class},
            {Cart5.class}
        };
        return Arrays.asList(classes);
    }
*/
    private Cart createCart(int age) throws Exception {
        Constructor<Cart> constructor = classUnderTest.getConstructor(Integer.TYPE);
        return constructor.newInstance(age);
    }

    // A sample Cart

    Cart cart1;
    double cart1Expected;


    @org.junit.Before
    public void setUp() throws Exception {

        // all carts should be set up like this

        // cart created with an age 40 shopper
        cart1 = createCart(40);
        for (int i = 0; i < 2; i++) {
            cart1.addItem(new Alcohol());
        }
        for(int i = 0; i < 3; i++) {
            cart1.addItem(new Dairy());
        }
        for(int i = 0; i < 4; i++) {
            cart1.addItem(new Meat());
        }

        cart1Expected = 70.2;
    }

    // sample test
    @Test
    public void calcCostCart1() throws UnderAgeException, UnderAgeException {
        double amount = cart1.calcCost();
        assertEquals(cart1Expected, amount, .01);
    }
    @Test
    public void Dairy1() throws Exception
    {
        Cart carto = createCart(22);
        carto.addItem(new Dairy());
        double fin = carto.calcCost();
        double cartoGuess = 3.24;
        assertEquals(cartoGuess, fin, .01);
    } //cart 0 no tax
    @Test
    public void Meat1() throws Exception
    {
        Cart carto = createCart(22);
        carto.addItem(new Meat());
        double fin = carto.calcCost();
        double cartoGuess = 10.8;
        assertEquals(cartoGuess, fin, .01);
    } //cart 0 and 2 errors. Cart 0 no tax. Cart 2...
    @Test
    public void Produce1() throws Exception //find the 5 clues...
    {
        Cart carto = createCart(22);
        carto.addItem(new Produce());
        double fin = carto.calcCost();
        double cartoGuess = 2.16;
        assertEquals(cartoGuess, fin, .01);
    } //all carts do 1 produce correctly
    @Test
    public void Produce3() throws Exception
    {
        Cart carto = createCart(22);
        carto.addItem(new Produce());
        carto.addItem(new Produce());
        carto.addItem(new Produce());
        double fin = carto.calcCost();
        double cartoGuess = 5.4; //no discount = 3 for 6.48
        assertEquals(cartoGuess, fin, .01);
    }//cart 1 no discount, cart 2 no discount
    @Test
    public void Alchohol1OverAge() throws Exception
    {
        Cart carto = createCart(22);
        carto.addItem(new Alcohol());
        double fin = carto.calcCost();
        double cartoGuess = 8.64;
        assertEquals(cartoGuess, fin, .01);
    }//Cart 0 no tax on 1 alchohol
    @Test
    public void Alchohol1UnderAge() throws Exception
    {
        Cart carto = createCart(10);
        carto.addItem(new Alcohol());
        double fin = carto.calcCost();
        double cartoGuess = 0;
        assertEquals(cartoGuess, fin, .01);
    } //cart 1 says its too young, but allows purchase, cart 2 allows purchase no q's asked, cart 5 allows and gives cipher clue
    @Test
    public void Frozen1() throws Exception
    {
        Cart carto = createCart(22);
        carto.addItem(new FrozenFood());
        double fin = carto.calcCost();
        double cartoGuess = 5.4;
        assertEquals(cartoGuess, fin, .01);
    }//no tax cart 0
    @Test
    public void AlcoholAndFrozen1Each() throws Exception ///////check later??????
    {
        Cart carto = createCart(22);
        carto.addItem(new Alcohol());
        carto.addItem(new FrozenFood());
        double fin = carto.calcCost();
        double cartoGuess = 10.8;
        assertEquals(cartoGuess, fin, .01);
    } //cart 0 no tax BUT adds discount for alcohol and frozen. cart 3 no discount
    @Test
    public void AlcoholAndFrozen1EachUnderAge() throws Exception
    {
        Cart carto = createCart(10);
        carto.addItem(new Alcohol());
        carto.addItem(new FrozenFood());
        double fin = carto.calcCost();
        double cartoGuess = 0;
        assertEquals(cartoGuess, fin, .01);
    } //cart 0 blocks purchase, cart 1 says no but allows purchase, cart2 allows purchase, cart 5 allows purchase
    @Test
    public void Produce3AndAlcoholAndFrozen1OverAge() throws Exception
    {
        Cart carto = createCart(22);
        carto.addItem(new Produce());
        carto.addItem(new Produce());
        carto.addItem(new Produce());
        carto.addItem(new FrozenFood());
        carto.addItem(new Alcohol());
        double fin = carto.calcCost();
        double cartoGuess = 16.2;
        assertEquals(cartoGuess, fin, .01);
    } //cart1 no produce discount, cart 2 no produce discount, cart 3 no alc/froz discount
    @Test
    public void Produce3AndAlcoholAndFrozen1UnderAge() throws Exception
    {
        Cart carto = createCart(0);
        carto.addItem(new Produce());
        carto.addItem(new Produce());
        carto.addItem(new Produce());
        carto.addItem(new FrozenFood());
        carto.addItem(new Alcohol());
        double fin = carto.calcCost();
        double cartoGuess = 16.2;
        assertEquals(cartoGuess, fin, .01);
    } //cart 1 error mssg but doesnt stop purchase, cart 5 allows purchase(sets price to 0 tho)
    @Test
    public void NothingUnderAge() throws Exception
    {
        Cart carto = createCart(10);
        double fin = carto.calcCost();
        double cartoGuess = 0;
        assertEquals(cartoGuess, fin, .01);
    }//all pass
    @Test
    public void NothingOverAge() throws Exception
    {
        Cart carto = createCart(22);
        double fin = carto.calcCost();
        double cartoGuess = 0;
        assertEquals(cartoGuess, fin, .01);
    }//all pass
    @Test
    public void ALOTofEach() throws Exception
    {
        Cart carto = createCart(10);
        carto = createCart(40);
        for (int i = 0; i < 10; i++) {
            carto.addItem(new Alcohol());
        }
        for(int i = 0; i < 10; i++) {
            carto.addItem(new Dairy());
        }
        for(int i = 0; i < 10; i++) {
            carto.addItem(new Meat());
        }
        for (int i = 0; i < 10; i++) {
            carto.addItem(new FrozenFood());
        }
        for(int i = 0; i < 10; i++) {
            carto.addItem(new Produce());
        }
        double fin = carto.calcCost();
        double cartoGuess = 0;
        assertEquals(cartoGuess, fin, .01);
    }
    @Test
    public void Produce10() throws Exception
    {
        Cart carto = createCart(10);
        carto = createCart(40);
        for(int i = 0; i < 10; i++) {
            carto.addItem(new Produce());
        }
        double fin = carto.calcCost();
        double cartoGuess = 18.36;
        assertEquals(cartoGuess, fin, .01);
    } //no produce dscount cart 1
    @Test
    public void Dairy10() throws Exception
    {
        Cart carto = createCart(10);
        carto = createCart(40);
        for(int i = 0; i < 10; i++) {
            carto.addItem(new Dairy());
        }
        double fin = carto.calcCost();
        double cartoGuess = 32.4;
        assertEquals(cartoGuess, fin, .01);
    }
    @Test
    public void Meat10() throws Exception
    {
        Cart carto = createCart(10);
        carto = createCart(40);
        for(int i = 0; i < 10; i++) {
            carto.addItem(new Meat());
        }
        double fin = carto.calcCost();
        double cartoGuess = 108;
        assertEquals(cartoGuess, fin, .01);
    } //cart2, idk what happened. Got 97.2 instead of 108. Only charging 9.00 for meat
    @Test
    public void Alcohol100OverAge() throws Exception
    {
        Cart carto = createCart(10);
        carto = createCart(40);
        for(int i = 0; i < 100; i++) {
            carto.addItem(new Alcohol());
        }
        double fin = carto.calcCost();
        double cartoGuess = 864;
        assertEquals(cartoGuess, fin, .01);
    }
    @Test
    public void Alcohol100UnderAge() throws Exception
    {
        Cart carto = createCart(10);
        carto = createCart(0);
        for(int i = 0; i < 100; i++) {
            carto.addItem(new Alcohol());
        }
        double fin = carto.calcCost();
        double cartoGuess = 864;
        assertEquals(cartoGuess, fin, .01);
    }//1 allows with mssg, 2 sets to 0 , 5 sets to 0,
    @Test
    public void Frozen100OverAge() throws Exception
    {
        Cart carto = createCart(10);
        carto = createCart(40);
        for(int i = 0; i < 100; i++) {
            carto.addItem(new FrozenFood());
        }
        double fin = carto.calcCost();
        double cartoGuess = 540;
        assertEquals(cartoGuess, fin, .01);
    }
    @Test
    public void Frozen100Alcohol100OverAge() throws Exception
    {
        Cart carto = createCart(10);
        carto = createCart(40);
        for(int i = 0; i < 100; i++) {
            carto.addItem(new FrozenFood());
        }
        for(int i = 0; i < 100; i++) {
            carto.addItem(new Alcohol());
        }
        double fin = carto.calcCost();
        double cartoGuess = 1080.0;
        assertEquals(cartoGuess, fin, .01);
    }
    @Test
    public void AlcoholAtAge21() throws Exception
    {
        Cart carto = createCart(21);
        carto.addItem(new Alcohol());
        double fin = carto.calcCost();
        double cartoGuess = 2;
        assertEquals(cartoGuess, fin, .01);
    }
    @Test
    public void CLUE4() throws Exception
    {
        Cart carto = createCart(10);
        carto = createCart(40);

        for (int i = 0; i < 10; i++) {
            carto.addItem(new Alcohol());
        }
        for(int i = 0; i < 10; i++) {
            carto.addItem(new Meat());
        }
        for (int i = 0; i < 10; i++) {
            carto.addItem(new FrozenFood());
        }
        for(int i = 0; i < 10; i++) {
            carto.addItem(new Produce());
        }
        double fin = carto.calcCost();
        double cartoGuess = 277.88;
        assertEquals(cartoGuess, fin, .01);
    }




}