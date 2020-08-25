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
    public Addition addition(Deque<Operator> operators) {
        return (Addition) getBinaryOperator(operators, Addition::new);

    }

    @Bean
    @Scope("prototype")
    public Subtraction subtraction(Deque<Operator> operators) {
        return (Subtraction) getBinaryOperator(operators, Subtraction::new);

    }

    @Bean
    @Scope("prototype")
    public Multiplication multiplication(Deque<Operator> operators) {
        return (Multiplication) getBinaryOperator(operators, Multiplication::new);

    }

    @Bean
    @Scope("prototype")
    public Division division(Deque<Operator> operators) {
        return (Division) getBinaryOperator(operators, Division::new);

    }

    @Bean
    @Scope("prototype")
    public SquareRoot sqrt(Deque<Operator> operators) {
        return (SquareRoot) getUnaryOperator(operators, SquareRoot::new);
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



    private Operator getBinaryOperator(Deque<Operator> operators,
                                       BiFunction<Operator, Operator, BinaryOperator> instance) {
        Operator operand2 = null;
        Operator operand1;
        try {
            operand2 = operators.pop();
            operand1 = operators.pop();
        } catch (NoSuchElementException e) {
            if (operand2 != null) {
                operators.push(operand2);
            }
            throw e;
        }
        return instance.apply(operand1, operand2);
    }

    private Operator getUnaryOperator(Deque<Operator> operators,
                                      Function<Operator, UnaryOperator> instance) {
        Operator operand = operators.pop();
        return instance.apply(operand);
    }
}
