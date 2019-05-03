package com.braintree.interview;

import java.util.Map;

/**
 * All Commands implement this method.
 */
@FunctionalInterface
public interface Command
{
    /**
     * All Commands define their functionality with this method
     *  using these parameters.
     *
     * @param cards - map of CreditCard and associated name
     * @param commandString - string of command and its parameters
     */
    void execute(Map<String, CreditCard> cards, String commandString);

}
