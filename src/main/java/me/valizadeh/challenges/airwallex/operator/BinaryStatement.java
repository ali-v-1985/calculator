package me.valizadeh.challenges.airwallex.operator;

import java.util.ArrayList;
import java.util.List;

/**
 * The abstract {@link Statement} which contains two operands of type {@link Statement}.
 */
public abstract class BinaryStatement implements Statement {

    protected final Statement operand1;
    protected final Statement operand2;

    public BinaryStatement(Statement operand1, Statement operand2) {
        this.operand1 = operand1;
        this.operand2 = operand2;
    }

    /**
     * @return the two operands of any {@link BinaryStatement}.
     */
    @Override
    public List<Statement> unExecute() {
        List<Statement> statements = new ArrayList<>();
        statements.add(operand1);
        statements.add(operand2);
        return statements;
    }

}
