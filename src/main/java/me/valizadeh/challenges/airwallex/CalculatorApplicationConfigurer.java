package me.valizadeh.challenges.airwallex;

import me.valizadeh.challenges.airwallex.calculator.Calculator;
import me.valizadeh.challenges.airwallex.calculator.RpnCalculator;
import me.valizadeh.challenges.airwallex.gateway.ConsoleGateway;
import me.valizadeh.challenges.airwallex.gateway.Gateway;
import me.valizadeh.challenges.airwallex.memory.MemoryManager;
import me.valizadeh.challenges.airwallex.operator.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
@EnableConfigurationProperties(CalculatorApplicationConfigModel.class)
public class CalculatorApplicationConfigurer {

    @Bean
    @Order
    public Calculator rpn(OperatorFactory operatorFactory,
                          MemoryManager memoryManager) {
        return new RpnCalculator(operatorFactory, memoryManager);
    }

    @Bean
    public Calculator calculator(ApplicationContext context,
                                 @Value("${calculator.type}") String calculatorType) {
        return (Calculator) context.getBean(calculatorType);
    }

    @Bean
    public OperatorFactory operatorFactory(
            @Qualifier("addition") Operator addition,
            @Qualifier("subtraction") Operator subtraction,
            @Qualifier("multiplication") Operator multiplication,
            @Qualifier("division") Operator division,
            @Qualifier("sqrt") Operator sqrt,
            @Qualifier("clear") Operator clear,
            @Qualifier("undo") Operator undo) {
        OperatorFactory operatorFactory = new OperatorFactory();
        operatorFactory.setAddition(addition);
        operatorFactory.setSubtraction(subtraction);
        operatorFactory.setMultiplication(multiplication);
        operatorFactory.setDivision(division);
        operatorFactory.setSqrt(sqrt);
        operatorFactory.setClear(clear);
        operatorFactory.setUndo(undo);
        return operatorFactory;
    }

    @Bean
    public Operator addition() {
        return new Addition();
    }

    @Bean
    public Operator subtraction() {
        return new Subtraction();
    }

    @Bean
    public Operator multiplication() {
        return new Multiplication();
    }

    @Bean
    public Operator division() {
        return new Division();
    }

    @Bean
    public Operator sqrt() {
        return new SquareRoot();
    }

    @Bean
    public Operator clear() {
        return new Clear();
    }

    @Bean
    public Operator undo() {
        return new Undo();
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
    public MemoryManager memoryManager() {
        return new MemoryManager();
    }
}
