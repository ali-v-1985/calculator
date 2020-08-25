package me.valizadeh.challenges.airwallex;

import me.valizadeh.challenges.airwallex.calculator.Calculator;
import me.valizadeh.challenges.airwallex.calculator.RpnCalculator;
import me.valizadeh.challenges.airwallex.gateway.ConsoleGateway;
import me.valizadeh.challenges.airwallex.gateway.Gateway;
import me.valizadeh.challenges.airwallex.memory.Memory;
import me.valizadeh.challenges.airwallex.operator.*;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.Order;

import java.util.Deque;
import java.util.NoSuchElementException;
import java.util.function.BiFunction;
import java.util.function.Function;

@Configuration
@EnableConfigurationProperties(CalculatorApplicationConfigModel.class)
public class CalculatorApplicationConfigurer {

    @Bean
    @Order
    public Calculator rpn(Memory memory,
                          OperatorFactory operatorFactory) {
        return new RpnCalculator(memory, operatorFactory);
    }

    @Bean
    public Calculator calculator(ApplicationContext context,
                                 @Value("${calculator.type}") String calculatorType) {
        return (Calculator) context.getBean(calculatorType);
    }

    @Bean
    public OperatorFactory operatorFactory(BeanFactory beanFactory, OperatorSignMapper operatorSignMapper) {
        return new OperatorFactory(beanFactory, operatorSignMapper);
    }

    @Bean
    public OperatorSignMapper operatorSignMapper() {
        return new OperatorSignMapper();
    }

    @Bean
    @Scope("prototype")
    public Addition addition(Deque<Statement> statements) {
        return (Addition) getBinaryOperator(statements, Addition::new);

    }

    @Bean
    @Scope("prototype")
    public Subtraction subtraction(Deque<Statement> statements) {
        return (Subtraction) getBinaryOperator(statements, Subtraction::new);

    }

    @Bean
    @Scope("prototype")
    public Multiplication multiplication(Deque<Statement> statements) {
        return (Multiplication) getBinaryOperator(statements, Multiplication::new);

    }

    @Bean
    @Scope("prototype")
    public Division division(Deque<Statement> statements) {
        return (Division) getBinaryOperator(statements, Division::new);

    }

    @Bean
    @Scope("prototype")
    public SquareRoot sqrt(Deque<Statement> statements) {
        return (SquareRoot) getUnaryOperator(statements, SquareRoot::new);
    }

    @Bean
    @Order
    public Gateway console() {
        return new ConsoleGateway();
    }

    @Bean
    public Gateway gateway(ApplicationContext context,
                           @Value("${calculator.gateway}") String gateway) {
        return (Gateway) context.getBean(gateway);
    }

    @Bean
    public Memory memory() {
        return new Memory();
    }



    private Statement getBinaryOperator(Deque<Statement> statements,
                                        BiFunction<Statement, Statement, BinaryStatement> instance) {
        Statement operand2 = null;
        Statement operand1;
        try {
            operand2 = statements.pop();
            operand1 = statements.pop();
        } catch (NoSuchElementException e) {
            if (operand2 != null) {
                statements.push(operand2);
            }
            throw e;
        }
        return instance.apply(operand1, operand2);
    }

    private Statement getUnaryOperator(Deque<Statement> statements,
                                       Function<Statement, UnaryStatement> instance) {
        Statement operand = statements.pop();
        return instance.apply(operand);
    }
}
