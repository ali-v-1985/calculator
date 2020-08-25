package me.valizadeh.challenges.airwallex.operator;

import me.valizadeh.challenges.airwallex.exception.InsufficientParametersException;
import me.valizadeh.challenges.airwallex.operand.OperandWrapper;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;

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
     * Gets an instance of {@link Statement} based on the {@link Statement}'s sign.
     * @param sign the sign which {@link Statement} implementation has been mapped to.
     * @param pos the position of the sign in the input
     * @param operandWrapperFunc A function which receive a {@link Statement} class
     *                           and return a subclass of {@link OperandWrapper}
     * @return an instance of {@link Statement}
     */
    public Statement get(String sign, int pos,
                         Function<Class<? extends Statement>, OperandWrapper> operandWrapperFunc) {
        try {
            return beanFactory.getBean(operatorSignMapper.map(sign), operandWrapperFunc);
        } catch (BeansException e) {
            handleException(sign, pos, e);
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
