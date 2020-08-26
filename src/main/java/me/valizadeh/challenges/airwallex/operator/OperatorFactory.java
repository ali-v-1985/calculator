package me.valizadeh.challenges.airwallex.operator;

import me.valizadeh.challenges.airwallex.exception.InsufficientParametersException;
import me.valizadeh.challenges.airwallex.operand.OperandWrapper;
import me.valizadeh.challenges.airwallex.utils.NumberHelper;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.NoSuchElementException;
import java.util.function.Function;

import static me.valizadeh.challenges.airwallex.utils.Constants.INSUFFICIENT_PARAMETERS_WARN;

/**
 * Get an instance of an {@link Statement} from {@link BeanFactory}.
 * The {@link Statement} will try find its operands on the stack of the {@link Statement}s.
 * The {@link InsufficientParametersException} will be thrown
 * if enough operands have not been found on the stack of {@link Statement}s.
 */
public class OperatorFactory {

    private final BeanFactory beanFactory;
    private final OperatorSignMapper operatorSignMapper;

    public OperatorFactory(BeanFactory beanFactory, OperatorSignMapper operatorSignMapper) {
        this.beanFactory = beanFactory;
        this.operatorSignMapper = operatorSignMapper;
    }

    /**
     * Gets an instance of {@link Statement} based on the {@link Statement}'s input.
     *
     * @param input              the input which {@link Statement} implementation should be mapped to.
     * @param pos                the position of the input
     *                           in the {@link me.valizadeh.challenges.airwallex.calculator.Calculator}'s input
     * @param operandWrapperFunc A function which receive a {@link Statement} class
     *                           and return a subclass of {@link OperandWrapper}
     *                           to extract the operand(s) of each {@link Statement}
     * @return an instance of {@link Statement}
     */
    public Statement get(String input, int pos,
                         Function<Class<? extends Statement>, OperandWrapper> operandWrapperFunc) {
        try {
            if (NumberHelper.isNumeric(input)) {
                return beanFactory.getBean(ValueStatement.class, new BigDecimal(input));
            }
            return beanFactory.getBean(operatorSignMapper.map(input), operandWrapperFunc);
        } catch (BeansException e) {
            handleException(input, pos, e);
        }
        return null;
    }

    private void handleException(String sign, int pos, BeansException e) {
        if (e.getRootCause() instanceof NoSuchElementException) {
            throw new InsufficientParametersException(MessageFormat.format(INSUFFICIENT_PARAMETERS_WARN,
                    sign,
                    pos));
        } else {
            throw e;
        }
    }
}
