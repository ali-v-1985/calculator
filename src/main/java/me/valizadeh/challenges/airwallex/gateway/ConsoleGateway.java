package me.valizadeh.challenges.airwallex.gateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class ConsoleGateway implements Gateway {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConsoleGateway.class);
    private final Scanner scanner;

    public ConsoleGateway() {
        scanner = new Scanner(System.in);
    }

    @Override
    public String read() {
        return scanner.nextLine();
    }

    @Override
    public void write(String output) {
        LOGGER.info(output);
    }
}
