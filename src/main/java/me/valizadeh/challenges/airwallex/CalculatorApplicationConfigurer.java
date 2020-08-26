package me.valizadeh.challenges.airwallex;

import me.valizadeh.challenges.airwallex.calculator.Calculator;
import me.valizadeh.challenges.airwallex.calculator.RpnCalculator;
import me.valizadeh.challenges.airwallex.gateway.ConsoleGateway;
import me.valizadeh.challenges.airwallex.gateway.Gateway;
import me.valizadeh.challenges.airwallex.memory.Memory;
import me.valizadeh.challenges.airwallex.memory.UndoableMemory;
import me.valizadeh.challenges.airwallex.operand.BinaryOperandWrapper;
import me.valizadeh.challenges.airwallex.operand.OperandWrapper;
import me.valizadeh.challenges.airwallex.operand.UnaryOperandWrapper;
import me.valizadeh.challenges.airwallex.operator.*;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.Order;

import java.util.function.BiFunction;
import java.util.function.Function;

@Configuration
@EnableConfigurationProperties(CalculatorApplicationConfigModel.class)
public class CalculatorApplicationConfigurer {

    @Bean
    @Order
    public Calculator rpn(@Qualifier("undoableMemory") Memory memory,
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
    public Addition addition(Function<Class<? extends Statement>, OperandWrapper> operandWrapperFunc) {
        BinaryOperandWrapper operandWrapper = (BinaryOperandWrapper) operandWrapperFunc.apply(Addition.class);
        return (Addition) getBinaryOperator(operandWrapper, Addition::new);

    }

    @Bean
    @Scope("prototype")
    public Subtraction subtraction(Function<Class<? extends Statement>, OperandWrapper> operandWrapperFunc) {
        BinaryOperandWrapper operandWrapper = (BinaryOperandWrapper) operandWrapperFunc.apply(Subtraction.class);
        return (Subtraction) getBinaryOperator(operandWrapper, Subtraction::new);

    }

    @Bean
    @Scope("prototype")
    public Multiplication multiplication(Function<Class<? extends Statement>, OperandWrapper> operandWrapperFunc) {
        BinaryOperandWrapper operandWrapper = (BinaryOperandWrapper) operandWrapperFunc.apply(Multiplication.class);
        return (Multiplication) getBinaryOperator(operandWrapper, Multiplication::new);

    }

    @Bean
    @Scope("prototype")
    public Division division(Function<Class<? extends Statement>, OperandWrapper> operandWrapperFunc) {
        BinaryOperandWrapper operandWrapper = (BinaryOperandWrapper) operandWrapperFunc.apply(Division.class);
        return (Division) getBinaryOperator(operandWrapper, Division::new);

    }

    @Bean
    @Scope("prototype")
    public SquareRoot sqrt(Function<Class<? extends Statement>, OperandWrapper> operandWrapperFunc) {
        UnaryOperandWrapper operandWrapper = (UnaryOperandWrapper) operandWrapperFunc.apply(SquareRoot.class);
        return (SquareRoot) getUnaryOperator(operandWrapper, SquareRoot::new);
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
    public Memory undoableMemory() {
        return new UndoableMemory();
    }


    private Statement getBinaryOperator(BinaryOperandWrapper operandWrapper,
                                        BiFunction<Statement, Statement, BinaryStatement> instance) {
        return instance.apply(operandWrapper.getOperand1(), operandWrapper.getOperand2());
    }

    private Statement getUnaryOperator(UnaryOperandWrapper unaryOperandWrapper,
                                       Function<Statement, UnaryStatement> instance) {
        Statement operand = unaryOperandWrapper.getOperand();
        return instance.apply(operand);
    }
}
