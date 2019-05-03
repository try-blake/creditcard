OVERVIEW
To process the different commands, I've used the Command design pattern to encapsulate
each of their particular functionalities.
I've also used a Factory to provide clients easy access to those commands.
These can then easily be extended to more commands with minimal or no changes to the client.

DEPENDENCIES
1. Java 8
2. Maven (https://maven.apache.org/)
2. Junit 4

I've used Java for it's strength in object-oriented features, and which also more recently adds functional features.
Also, Junit is available for it as a testing library to facilitate Test Driven Development.

COMPILING
I've constructed this as a Maven project so it downloads all dependencies automatically.
This project can be compiled by executing:

    mvn clean install

RUNNING
Execute this command with input file as an argument:

    java -cp target/creditcard-1.0-SNAPSHOT.jar com.braintree.interview.Main <input filename>

or by redirecting a file to it using stdin:

        java -cp target/creditcard-1.0-SNAPSHOT.jar com.braintree.interview.Main < <input filename>


There is also a log file that can be enabled for more information of apps' detailed activity:

    java -cp target/creditcard-1.0-SNAPSHOT.jar -Djava.util.logging.config.file=src/main/resources/logging.properties com.braintree.interview.Main <input filename>


TESTING
To run the tests, execute:

    mvn test

The test results can be found in ~/target/surefire-reports


