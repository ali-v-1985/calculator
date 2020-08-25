package me.valizadeh.challenges.airwallex.operator;

import me.valizadeh.challenges.airwallex.exception.InsufficientParametersException;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;

import java.text.MessageFormat;
import java.util.Deque;
import java.util.NoSuchElementException;

import static me.valizadeh.challenges.airwallex.utils.MessageUtil.INSUFFICIENT_PARAMETERS_WARN;

public class OperatorFactory {

    private final BeanFactory beanFactory;
    private final OperatorSignMapper operatorSignMapper;

    public OperatorFactory(BeanFactory beanFactory, OperatorSignMapper operatorSignMapper) {
        this.beanFactory = beanFactory;
        this.operatorSignMapper = operatorSignMapper;
    }

    public Operator get(String sign, int pos, Deque<Operator> operators) {
        try {
            return beanFactory.getBean(operatorSignMapper.get(sign), operators);
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
