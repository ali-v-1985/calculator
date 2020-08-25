package me.valizadeh.challenges.airwallex;

import me.valizadeh.challenges.airwallex.calculator.Calculator;
import me.valizadeh.challenges.airwallex.gateway.Gateway;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
@Import(CalculatorApplicationConfigurer.class)
public class Application {

    private final Calculator calculator;
    private final Gateway gateway;


    public Application(@Qualifier("calculator") Calculator calculator,
                       @Qualifier("gateway") Gateway gateway) {
        this.calculator = calculator;
        this.gateway = gateway;
    }


    public static void main(String[] args) {
        new SpringApplicationBuilder(Application.class)
                .build(args)
                .run();
    }


    @Bean
    @Profile("!test")
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            String input;
            while ((input = gateway.read()) != null && !input.isEmpty()) {
                gateway.write(calculator.execute(input));
            }
        };
    }

}
