package me.valizadeh.challenges.airwallex.gateway;

import java.util.Scanner;

public class ConsoleGateway implements Gateway {

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
        System.out.println(output);
    }
}
