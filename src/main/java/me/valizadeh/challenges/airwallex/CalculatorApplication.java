package me.valizadeh.challenges.airwallex;

import me.valizadeh.challenges.airwallex.calculator.Calculator;
import me.valizadeh.challenges.airwallex.exception.InsufficientParametersException;
import me.valizadeh.challenges.airwallex.exception.UnknownOperator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import java.util.Scanner;

@SpringBootApplication
@Import(CalculatorApplicationConfigurer.class)
public class CalculatorApplication {

    public CalculatorApplication(@Qualifier("calculator") Calculator calculator) {
        this.calculator = calculator;
    }

    public static void main(String[] args) {
        String springProfiles = "prod";
        if (args.length >= 1) {
            springProfiles = args[0];
        }

        new SpringApplicationBuilder(CalculatorApplication.class)
                .profiles(springProfiles)
                .build(args)
                .run();
    }

    private final Calculator calculator;


    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            Scanner scanner = new Scanner(System.in);
            String input;
            while ((input = scanner.nextLine()) != null && !input.isEmpty()) {
                try {
                    System.out.println(calculator.calculate(input));
                } catch (UnknownOperator | InsufficientParametersException e) {
                    e.printStackTrace();
                }
            }

        };
    }
}
