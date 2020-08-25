package me.valizadeh.challenges.airwallex.gateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

/**
 * The console implementation of the {@link Gateway}
 */
public class ConsoleGateway implements Gateway {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConsoleGateway.class);
    private final Scanner scanner;

    public ConsoleGateway() {
        scanner = new Scanner(System.in);
    }

    /**
     * Reads the values from {@link Scanner} which has been created on {@link System#in}
     * @return one line of {@link System#in}
     */
    @Override
    public String read() {
        return scanner.nextLine();
    }

    /**
     * Writes on the console by logger.
     * @param output the output which should be written.
     */
    @Override
    public void write(String output) {
        LOGGER.info(output);
    }
}
