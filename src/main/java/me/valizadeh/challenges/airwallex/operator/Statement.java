package me.valizadeh.challenges.airwallex.operator;

import java.math.BigDecimal;
import java.util.List;

/**
 * The api of the {@link Statement}s which {@link me.valizadeh.challenges.airwallex.calculator.Calculator} supports.
 */
public interface Statement {

    /**
     * Executes the operator on the operand(s) and return a {@link BigDecimal} value.
     * @return the result of the execution of the {@link Statement}
     */
    BigDecimal execute();

    /**
     * Unexecutes the execution of the Operation.
     * Returns the operands it executed on if necessary.
     * @return list of {@link Statement}(s) which this {@link Statement} execute on.
     */
    List<Statement> unExecute();

}
