package me.valizadeh.challenges.airwallex.gateway;

import java.text.MessageFormat;
import java.util.Scanner;

public class FileGateway implements Gateway {

    private static final String TEST_CASE_PATH = "/testcases/{0}.txt";
    private Scanner scanner;

    public FileGateway() {
        System.out.println(this.hashCode());
    }

    @Override
    public String read() {
        try {
            return scanner.nextLine();
        } catch (Exception e) {
            return null;
        }
    }

    public void loadTestCase(String testCaseName) {
        scanner = new Scanner(FileGateway.class.getResourceAsStream(
                MessageFormat.format(TEST_CASE_PATH, testCaseName)));
    }

    public void unloadTestCase() {
        scanner.close();
    }
}
