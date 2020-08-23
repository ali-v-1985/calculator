package me.valizadeh.challenges.airwallex.operator;

public class Square extends Root {

    public Square() {
        super(2);
    }

    @Override
    public String getName() {
        return OperatorFactory.SQRT;
    }
}
