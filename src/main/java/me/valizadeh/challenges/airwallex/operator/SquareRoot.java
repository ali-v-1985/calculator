package me.valizadeh.challenges.airwallex.operator;

/**
 * The square root {@link Statement}
 */
public class SquareRoot extends Root {

    private static final int ROOT_BASE = 2;

    /**
     * @param operand the operand which square root should be applied on.
     */
    public SquareRoot(Statement operand) {
        super(operand, ROOT_BASE);
    }
}
