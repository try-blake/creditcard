package com.braintree.interview;

import java.io.BufferedInputStream;
import java.io.File;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *  This is the main controller for the app.
 *  It accepts input from a file of commands or commands from stdin.
 *
 *  MODIFICATION: if you add a Type in the command string so a person can charge
 *                  to different accounts
 */
public class Main
{
    static Logger log = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) throws Exception
    {
        Scanner in;
        if (args.length > 0) // read input file
            in = new Scanner(new File(args[0]));
        else // input from stdin
            in = new Scanner(new BufferedInputStream(System.in));

        Main.run(in);
    }

    static void run(Scanner in)
    {
        final CommandFactory cf = CommandFactory.initialize();

        Map<String, CreditCard> cards = new HashMap<>();
        while (in.hasNextLine()) // process commands
        {
            String line = in.nextLine();
            log.fine("read:" + line);
            cf.executeCommand(cards, line);
        }

        log.fine("number cards:" + cards.size());

        // sort the cards by name
        Map<String, CreditCard> sortedCards = cards.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        // print out each balance unless the card is invalid, then print out "error"
        sortedCards.forEach((k,v)-> {
            if (v.isCardValid())
                System.out.println(v.getName() + ": $" + v.getBalance());
            else
                System.out.println(v.getName() + ": error");

        });
    }

}
