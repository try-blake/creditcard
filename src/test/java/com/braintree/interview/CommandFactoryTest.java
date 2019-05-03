package com.braintree.interview;

import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 */
public class CommandFactoryTest
{
    /**
     * tests CommandFactory
     */
    @Test
    public void testCommandFactory()
    {
        CommandFactory factory = CommandFactory.initialize();

        // test retrieval of configured command
        assertNotNull(factory.getCommand("Add"));

        // test retrieval of non-existent command
        assertNull(factory.getCommand("InvalidCommand"));

        // test addition of new command to factory
        factory.addCommand("Noop", (cards, commandString)->
        {
            // do nothing
        });
        Command noop = factory.getCommand("Noop");
        assertNotNull(noop);
    }
}
