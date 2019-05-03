package com.braintree.interview;

import org.junit.Test;

import java.io.File;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

/**
 * Uses the expected output from the sample commands described in project requirements as a test case.
 */
public class MainTest
{


    /**
     * Compares the expected output for the commands in file "inputCommands.txt" with the actual output of Main.run()
     * The output for this test is stored in ~/src/test/resources/actualOutput.txt
     *
     * @throws Exception
     */
    @Test
    public void doit() throws Exception
    {
        // redirect output to stdout to a file for comparison with expected results
        System.setOut(new PrintStream(new File("src/test/resources/actualOutput.txt")));
        Main.run(new Scanner(new File("src/test/resources/inputCommands.txt")));

        List<String> file1  = Files.readAllLines(Paths.get("src/test/resources/expectedOutput.txt"));
        List<String> file2  = Files.readAllLines(Paths.get("src/test/resources/actualOutput.txt"));

        assertEquals(file1.size(), file2.size());

        for(int i = 0; i < file1.size(); i++)
            assertEquals(file1.get(i), file2.get(i));


    }
}
