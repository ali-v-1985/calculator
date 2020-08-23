package me.valizadeh.challenges.airwallex.integration;

import me.valizadeh.challenges.airwallex.CalculatorTestConfigurer;
import me.valizadeh.challenges.airwallex.calculator.Calculator;
import me.valizadeh.challenges.airwallex.gateway.FileGateway;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertNotNull;

@ExtendWith(SpringExtension.class)
@Import(CalculatorTestConfigurer.class)
@SpringBootTest
@ActiveProfiles("test")
public class CalculatorTest {

    @Autowired
    private Calculator calculator;

    @Autowired
    @Qualifier("file")
    private FileGateway gateway;

    @Test
    public void testCalculatorAutowired() {
        assertNotNull("Calculator should not be null!", calculator);
        assertNotNull("FileReader should not be null!", gateway);
    }

    @ParameterizedTest
    @ValueSource(strings = {"TestCase1", "TestCase2",
            "TestCase3", "TestCase4",
            "TestCase5", "TestCase6",
            "TestCase7", "TestCase8"})
    public void testScenarios(String testCase) {
        gateway.loadTestCase(testCase);

        String input;
        while ((input = gateway.read()) != null && !input.isEmpty()) {
            String calculate = calculator.calculate(input);
            StringBuilder outputs = new StringBuilder();
            String output;
            while ((output = gateway.read()) != null && !output.isEmpty() && !output.contains("stack:")) {
                outputs.append(output).append("\r\n");
            }
            outputs.append(output);
            assertEquals("Out put should be equal to expected!", outputs.toString(), calculate);
        }



    }

    @AfterEach
    public void cleanup() {
        calculator.reset();
        gateway.unloadTestCase();
    }


}
