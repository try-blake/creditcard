package com.braintree.interview;


import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 *     This class configures the valid commands and stores the functionality of each.
 *     It provides a way to retrieve and execute them.
 *
 *     Format of valid commands:
 *
 *     Add <name> <card number> <limit>
 *          eg. Add Tom 4111111111111111 $1000
 *     Charge <name> <amount>
 *          eg Charge Tom $500
 *     Credit <name> <amount>
 *          eg Credit Lisa $100
 */
public class CommandFactory
{

    static Logger log = Logger.getLogger(CommandFactory.class.getName());

    private final Map<String, Command> commands;

    /**
     * Can only be instantiated via the initialize() method
     */
    private CommandFactory()
    {
        commands = new HashMap<>();
    }

    /**
     * From the commandString, execute the command if it is one of the valid ones configured.
     *
     * @param cards - map of credit cards
     * @param commandString - string of command and its parameters
     */
    public void executeCommand(Map<String, CreditCard> cards, String commandString)
    {
        String command = commandString.split("\\s+")[0];
        if (commands.containsKey(command))
        {
            log.fine("parsed valid command: " + command);
            commands.get(command).execute(cards, commandString);
        }
        else
            log.warning(command + " is not a valid command");
    }

    /**
     * @param name of command
     * @param command associated with name
     */
    public void addCommand(String name, Command command)
    {
        commands.put(name, command);
    }

    /**
     * @param name of command
     * @return Command corresponding to name
     */
    public Command getCommand(String name)
    {
        return commands.get(name);
    }

    /**
     * Initializes the factory and configures the valid commands and their functionality.
     *
     * @return CommandFactory that contains the valid commands
     */
    public static CommandFactory initialize()
    {
        final CommandFactory factory = new CommandFactory();

        factory.addCommand("Add", (map, commandString) ->
        {
            String[] tokens = commandString.split("\\s+");
            String name = tokens[1];
            String cardNumber = tokens[2];
            int amount = Integer.parseInt(tokens[3].substring(1)); // skip "$"

            CreditCard creditCard = new CreditCard(name,
                    cardNumber, amount);
            map.put(tokens[1], creditCard);
        });

        factory.addCommand("Charge", (cards, commandString) ->
        {
            String[] tokens = commandString.split("\\s+");
            String name = tokens[1];
            int amount = Integer.parseInt(tokens[2].substring(1)); // skip "$"
            CreditCard creditCard = cards.get(name);
            if (cards != null)
                creditCard.charge(amount);
        });

        factory.addCommand("Credit", (cards, commandString) ->
        {
            String[] tokens = commandString.split("\\s+");
            String name = tokens[1];
            int amount = Integer.parseInt(tokens[2].substring(1)); // skip "$"
            CreditCard creditCard = cards.get(name);
            if (creditCard != null)
                creditCard.credit(amount);
        });


        factory.addCommand("Remove", (cards, commandString) ->
        {
            String[] tokens = commandString.split("\\s+");
            String name = tokens[1];
            CreditCard creditCard = cards.get(name);
            if (cards != null)
                cards.remove(name);
        });

        factory.addCommand("Increase", (cards, commandString) ->
        {
            String[] tokens = commandString.split("\\s+");
            String name = tokens[1];
            int amount = Integer.parseInt(tokens[2].substring(1)); // skip "$"
            CreditCard creditCard = cards.get(name);
            if (creditCard != null)
                creditCard.increaseLimit(amount);
        });

        return factory;
    }
}

