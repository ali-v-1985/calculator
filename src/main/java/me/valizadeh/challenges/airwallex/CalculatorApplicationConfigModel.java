package me.valizadeh.challenges.airwallex;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("calculator")
public class CalculatorApplicationConfigModel {

    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
