package com.braintree.interview;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Contains test cases for the class CreditCard
 */
public class CreditCardTest
{

    /**
     * tests valid and invalid card numbers
     */
    @Test
    public void testCardNumberValidation()
    {
        // valid numbers
        assertTrue(new CreditCard("Tom", "4111111111111111", 1000).isCardValid());
        assertTrue(new CreditCard("Lisa", "5454545454545454", 3000).isCardValid());

        // sample valid number - referenced from https://en.wikipedia.org/wiki/Luhn_algorithm
        assertTrue(new CreditCard("Joe", "79927398713", 2000).isCardValid());

        // invalid numbers
        assertFalse(new CreditCard("Quincy", "1234567890123456", 2000).isCardValid());

        // sample invalid numbers - referenced from https://en.wikipedia.org/wiki/Luhn_algorithm
        assertFalse(new CreditCard("Joe", "79927398710", 2000).isCardValid());
        assertFalse(new CreditCard("Sam", "79927398711", 2000).isCardValid());
        assertFalse(new CreditCard("Betty", "79927398712", 2000).isCardValid());

        // longer than max allowed
        assertFalse(new CreditCard("TooMuch", "123456789012345678901", 2000).isCardValid());

    }

    /**
     * test credit and charge logic with valid cards
     */
    @Test
    public void testCreditsAndChargesWithValidCards()
    {
        CreditCard card = new CreditCard("Lisa", "5454545454545454", 3000);

        // test that credits dropping balance below 0 creates a negative balance
        card.credit(7);
        assertEquals(-7, card.getBalance());

        // test that charges are calculated correctly
        card.charge(100);
        assertEquals(93, card.getBalance());

        // test that charges going over limit will not get processed
        CreditCard card2 = new CreditCard("Tom", "4111111111111111", 1000);
        card2.charge(500);
        card2.charge(800);
        assertEquals(500, card2.getBalance());

    }

    /**
     * test that invalid cards don't get processed
     */
    @Test
    public void testCreditsAndChargesWithInvalidCards()
    {
        CreditCard card = new CreditCard("Quincy", "1234567890123456", 2000);

        assertFalse(card.isCardValid());

        card.charge(200);
        assertEquals(0, card.getBalance());

        card.credit(100);
        assertEquals(0, card.getBalance());

    }
}
