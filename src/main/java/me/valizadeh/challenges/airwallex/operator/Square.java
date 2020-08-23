package me.valizadeh.challenges.airwallex.operator;

public class Square extends Root {

    public Square() {
        rootValue = 2;
    }

    @Override
    public String getName() {
        return OperatorFactory.SQRT;
    }
}
