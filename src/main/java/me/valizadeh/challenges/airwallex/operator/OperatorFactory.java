package me.valizadeh.challenges.airwallex.operator;

import me.valizadeh.challenges.airwallex.exception.UnknownOperator;
import org.springframework.beans.factory.BeanFactory;

import java.util.Deque;

import static me.valizadeh.challenges.airwallex.operator.OperatorSigns.*;

public class OperatorFactory {

    private final BeanFactory beanFactory;

    public OperatorFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public Operator get(String sign, Deque<Operator> operators) throws UnknownOperator {
        switch (sign) {
            case ADDITION_SIGN:
                return beanFactory.getBean(Addition.class, operators);
            case SUBTRACTION_SIGN:
                return beanFactory.getBean(Subtraction.class, operators);
            case MULTIPLICATION_SIGN:
                return beanFactory.getBean(Multiplication.class, operators);
            case DIVISION_SIGN:
                return beanFactory.getBean(Division.class, operators);
            case SQRT_SIGN:
                return beanFactory.getBean(SquareRoot.class, operators);
            default:
                throw new UnknownOperator(sign);
        }

    }
}
