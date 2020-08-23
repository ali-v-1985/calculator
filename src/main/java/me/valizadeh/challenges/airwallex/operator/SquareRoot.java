package me.valizadeh.challenges.airwallex.operator;

public class SquareRoot extends Root {

    public SquareRoot() {
        super(2);
    }

    @Override
    public String getName() {
        return OperatorFactory.SQRT_SIGN;
    }
}
