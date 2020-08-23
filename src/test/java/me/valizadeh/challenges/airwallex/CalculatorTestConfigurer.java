package me.valizadeh.challenges.airwallex;


import me.valizadeh.challenges.airwallex.gateway.FileGateway;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;

@TestConfiguration
@Import(CalculatorApplicationConfigurer.class)
@EnableConfigurationProperties(CalculatorApplicationConfigModel.class)
public class CalculatorTestConfigurer {

    @Bean
    @Order
    public FileGateway file() {
        return new FileGateway();
    }

}
