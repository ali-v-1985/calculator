package me.valizadeh.challenges.airwallex.operator;

import me.valizadeh.challenges.airwallex.exception.InsufficientParametersException;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;

import java.text.MessageFormat;
import java.util.Deque;
import java.util.NoSuchElementException;

import static me.valizadeh.challenges.airwallex.utils.MessageUtil.INSUFFICIENT_PARAMETERS_WARN;

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
     * @param statements stack of the statements which has been pushed already to extract the operands of it.
     * @return an instance of {@link Statement}
     */
    public Statement get(String sign, int pos, Deque<Statement> statements) {
        try {
            return beanFactory.getBean(operatorSignMapper.map(sign), statements);
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
