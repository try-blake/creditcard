package com.braintree.interview;

/**
 * Stores relevant information of a credit card and contains methods
 *  to do credits and charges.
 */
public class CreditCard
{
    private static final int MAX_CARD_NUMBER_LENGTH = 19;

    private String cardNumber;
    private String name;
    private int limit;
    private int balance;
    private boolean isCardValid;

    /**
     * @param name - card holder name
     * @param number - card number
     * @param limit - max limit of card
     */
    public CreditCard(String name, String number, int limit)
    {
        this.name = name;
        this.limit = limit;
        this.cardNumber = number.trim();

        if (this.cardNumber.length() <= MAX_CARD_NUMBER_LENGTH &&
                isCardNumberValid(this.cardNumber))
            this.isCardValid = true;
        else
            this.isCardValid = false;

    }

    /**
     * If the card is balid, adds an amount to the balance of the credit card.
     *  If the amount exceeds the limit, the charge will be ignored.
     *
     * @param amount - value to add to balance
     */
    public void charge(int amount)
    {
        if (this.isCardValid && this.balance + amount <= limit)
            this.balance += amount;
    }

    /**
     * If the card is balid, this subtracts the amount from the balance of the credit card.
     *  Credits that decrease the balance below $0 will create a negative balance;
     *
     * @param amount
     */
    public void credit(int amount)
    {
        if (this.isCardValid)
            this.balance -= amount;
    }

    /**
     * Determines the validity of a card number using the Luhn algorithm.
     *
     * @param cardNumber
     * @return true if cardNumber is valid using Luhn 10 validation;
     *         false otherwise
     * Note: method below referenced from Wikipedia article on Luhn algorithm
     */
    private boolean isCardNumberValid(String cardNumber)
    {
        int sum = 0, checkDigit = 0;
        int cardNumberCheckDigit = Integer.parseInt(cardNumber.substring(cardNumber.length()-1));
        cardNumber = cardNumber.substring(0, cardNumber.length()-1);

        boolean isDouble = true;
        for (int i = cardNumber.length() - 1; i >= 0; i--) {
            int k = Integer.parseInt(String.valueOf(cardNumber.charAt(i)));
            sum += sumToSingleDigit((k * (isDouble ? 2 : 1)));
            isDouble = !isDouble;
        }

        if ((sum % 10) > 0)
            checkDigit = (10 - (sum % 10));

        return checkDigit == cardNumberCheckDigit;
    }

    private static int sumToSingleDigit(int k) {
        if (k < 10)
            return k;
        return sumToSingleDigit(k / 10) + (k % 10);
    }

    /*
     * getters for CreditCard info
     */
    public String getCardNumber()
    {
        return cardNumber;
    }

    public String getName()
    {
        return name;
    }

    public int getLimit()
    {
        return limit;
    }

    public int getBalance()
    {
        return balance;
    }

    public boolean isCardValid()
    {
        return isCardValid;
    }

    public void increaseLimit(int amount)
    {
        this.limit = amount;
    }
}
