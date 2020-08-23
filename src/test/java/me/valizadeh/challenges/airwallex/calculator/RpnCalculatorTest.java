package me.valizadeh.challenges.airwallex.calculator;

import me.valizadeh.challenges.airwallex.exception.InsufficientParametersException;
import me.valizadeh.challenges.airwallex.exception.UnknownOperator;
import me.valizadeh.challenges.airwallex.memory.MemoryManager;
import me.valizadeh.challenges.airwallex.operator.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.Deque;

import static org.mockito.Mockito.*;
import static org.springframework.test.util.AssertionErrors.assertEquals;


@ExtendWith(MockitoExtension.class)
public class RpnCalculatorTest {

    @InjectMocks
    private RpnCalculator calculator;

    @Mock
    private OperatorFactory operatorFactory;

    @Spy
    private MemoryManager memoryManager;

    @Test
    public void testTestCase1() throws UnknownOperator {

        String input = "5 2";

        String calculate = calculator.calculate(input);

        assertEquals("Result is not as expected!", "stack: 5 2", calculate);
        verify(operatorFactory, never()).get(anyString());
    }

    /*@Test
    public void testTestCase2() throws UnknownOperator, InsufficientParametersException {

        String input1 = "2 sqrt";

        Operator mockedSquareRoot = mock(SquareRoot.class);
        when(operatorFactory.get("sqrt")).thenReturn(mockedSquareRoot);
        Deque<BigDecimal> value1 = new ArrayDeque<>();
        value1.push(BigDecimal.valueOf(1.4142135623));
        when(mockedSquareRoot.calculate(any(), anyInt())).thenReturn(value1);
        String calculate = calculator.calculate(input1);

        assertEquals("Result1 should be as expected!", "stack: 1.4142135623", calculate);

        String input2 = "clear 9 sqrt";
        Operator mockedClear = mock(Clear.class);
        when(operatorFactory.get("clear")).thenReturn(mockedClear);
        Deque<BigDecimal> value2 = new ArrayDeque<>();
        when(mockedClear.calculate(any(), anyInt())).thenReturn(value2);

        Deque<BigDecimal> value21 = new ArrayDeque<>();
        value21.push(BigDecimal.valueOf(3));
        when(mockedSquareRoot.calculate(any(), anyInt())).thenReturn(value21);


        String calculate2 = calculator.calculate(input2);

        assertEquals("Result2 should be as expected!", "stack: 3", calculate2);


        verify(mockedClear).calculate(any(), anyInt());
        verify(mockedSquareRoot, times(2)).calculate(any(), anyInt());
    }

    @Test
    public void testTestCase3() throws UnknownOperator, InsufficientParametersException {

        String input1 = "5 2 -";
        Operator mockedSubtraction = mock(Subtraction.class);
        when(operatorFactory.get("-")).thenReturn(mockedSubtraction);
        Deque<BigDecimal> value = new ArrayDeque<>();
        value.push(BigDecimal.valueOf(3));
        when(mockedSubtraction.calculate(any(), anyInt())).thenReturn(value);

        String calculate = calculator.calculate(input1);

        assertEquals("Result1 should be as expected!", "stack: 3", calculate);

        String input2 = "3 -";
        Deque<BigDecimal> value2 = new ArrayDeque<>();
        value2.push(BigDecimal.valueOf(0));
        when(mockedSubtraction.calculate(any(), anyInt())).thenReturn(value2);

        String calculate2 = calculator.calculate(input2);

        assertEquals("Result2 should be as expected!", "stack: 0", calculate2);

        String input3 = "clear";
        Operator mockedClear = mock(Clear.class);
        when(operatorFactory.get("clear")).thenReturn(mockedClear);
        Deque<BigDecimal> value3 = new ArrayDeque<>();
        when(mockedClear.calculate(any(), anyInt())).thenReturn(value3);

        String calculate3 = calculator.calculate(input3);

        assertEquals("Result3 should be as expected!", "stack:", calculate3);

        verify(mockedSubtraction, times(2)).calculate(any(), anyInt());
        verify(mockedClear).calculate(any(), anyInt());

    }

    @Test
    public void testTestCase5() throws UnknownOperator, InsufficientParametersException {

        String input1 = "7 12 2 /";
        Operator mockedDivision = mock(Division.class);
        when(operatorFactory.get("/")).thenReturn(mockedDivision);
        Deque<BigDecimal> value = new ArrayDeque<>();
        value.push(BigDecimal.valueOf(7));
        value.push(BigDecimal.valueOf(6));
        when(mockedDivision.calculate(any(), anyInt())).thenReturn(value);

        String calculate = calculator.calculate(input1);

        assertEquals("Result1 should be as expected!", "stack: 7 6", calculate);

        String input2 = "*";
        Operator mockedMultiplication = mock(Multiplication.class);
        when(operatorFactory.get("*")).thenReturn(mockedMultiplication);
        Deque<BigDecimal> value2 = new ArrayDeque<>();
        value2.push(BigDecimal.valueOf(42));
        when(mockedMultiplication.calculate(any(), anyInt())).thenReturn(value2);

        String calculate2 = calculator.calculate(input2);

        assertEquals("Result2 should be as expected!", "stack: 42", calculate2);

        String input3 = "4 /";
        Deque<BigDecimal> value3 = new ArrayDeque<>();
        value3.push(BigDecimal.valueOf(10.5));
        when(mockedDivision.calculate(any(), anyInt())).thenReturn(value3);

        String calculate3 = calculator.calculate(input3);

        assertEquals("Result3 should be as expected!", "stack: 10.5", calculate3);

        verify(mockedDivision, times(2)).calculate(any(), anyInt());
        verify(mockedMultiplication).calculate(any(), anyInt());

    }

    @Test
    public void testTestCase6() throws UnknownOperator, InsufficientParametersException {

        String input1 = "1 2 3 4 5";

        String calculate = calculator.calculate(input1);

        assertEquals("Result1 should be as expected!", "stack: 1 2 3 4 5", calculate);

        String input2 = "*";
        Operator mockedMultiplication = mock(Multiplication.class);
        when(operatorFactory.get("*")).thenReturn(mockedMultiplication);
        Deque<BigDecimal> value2 = new ArrayDeque<>();
        value2.push(BigDecimal.valueOf(1));
        value2.push(BigDecimal.valueOf(2));
        value2.push(BigDecimal.valueOf(3));
        value2.push(BigDecimal.valueOf(20));
        when(mockedMultiplication.calculate(any(), anyInt())).thenReturn(value2);

        String calculate2 = calculator.calculate(input2);

        assertEquals("Result2 should be as expected!", "stack: 1 2 3 20", calculate2);

        String input3 = "clear 3 4 -";
        Operator mockedClear = mock(Clear.class);
        when(operatorFactory.get("clear")).thenReturn(mockedClear);
        Deque<BigDecimal> value3 = new ArrayDeque<>();
        when(mockedClear.calculate(any(), anyInt())).thenReturn(value3);

        Operator mockedSubtraction = mock(Subtraction.class);
        when(operatorFactory.get("-")).thenReturn(mockedSubtraction);
        Deque<BigDecimal> value31 = new ArrayDeque<>();
        value31.push(BigDecimal.valueOf(-1));
        when(mockedSubtraction.calculate(any(), anyInt())).thenReturn(value31);

        String calculate3 = calculator.calculate(input3);

        assertEquals("Result3 should be as expected!", "stack: -1", calculate3);

        verify(mockedMultiplication).calculate(any(), anyInt());
        verify(mockedClear).calculate(any(), anyInt());
        verify(mockedSubtraction).calculate(any(), anyInt());

    }

    @Test
    public void testTestCase7() throws UnknownOperator, InsufficientParametersException {

        String input1 = "1 2 3 4 5";

        String calculate = calculator.calculate(input1);

        assertEquals("Result1 should be as expected!", "stack: 1 2 3 4 5", calculate);

        String input2 = "* * * *";
        Operator mockedMultiplication = mock(Multiplication.class);
        when(operatorFactory.get("*")).thenReturn(mockedMultiplication);
        Deque<BigDecimal> value1 = new ArrayDeque<>();
        value1.push(BigDecimal.valueOf(1));
        value1.push(BigDecimal.valueOf(2));
        value1.push(BigDecimal.valueOf(3));
        value1.push(BigDecimal.valueOf(20));

        Deque<BigDecimal> value2 = new ArrayDeque<>();
        value2.push(BigDecimal.valueOf(1));
        value2.push(BigDecimal.valueOf(2));
        value2.push(BigDecimal.valueOf(60));

        Deque<BigDecimal> value3 = new ArrayDeque<>();
        value3.push(BigDecimal.valueOf(1));
        value3.push(BigDecimal.valueOf(120));

        Deque<BigDecimal> value4 = new ArrayDeque<>();
        value4.push(BigDecimal.valueOf(120));

        when(mockedMultiplication.calculate(any(), anyInt())).thenAnswer(new Answer<Deque<BigDecimal>>() {
            private int calls = 0;

            @Override
            public Deque<BigDecimal> answer(InvocationOnMock invocation) {
                calls++;
                if (calls == 1) {
                    return value1;
                } else if (calls == 2) {
                    return value2;
                } else if (calls == 3) {
                    return value3;
                } else if (calls == 4) {
                    return value4;
                } else {
                    return value1;
                }
            }
        });

        String calculate2 = calculator.calculate(input2);

        assertEquals("Result2 should be as expected!", "stack: 120", calculate2);


        verify(mockedMultiplication, times(4)).calculate(any(), anyInt());

    }

    @Test
    public void testTestCase8() throws UnknownOperator, InsufficientParametersException {

        String input1 = "1 2 3 * 5 + * * 6 5";
        Operator mockedMultiplication = mock(Multiplication.class);
        when(operatorFactory.get("*")).thenReturn(mockedMultiplication);
        Deque<BigDecimal> value1 = new ArrayDeque<>();
        value1.push(BigDecimal.valueOf(6));
        Deque<BigDecimal> value2 = new ArrayDeque<>();
        value2.push(BigDecimal.valueOf(11));
        when(mockedMultiplication.calculate(any(), anyInt())).thenAnswer(new Answer<Deque<BigDecimal>>() {
            private int calls = 0;

            @Override
            public Deque<BigDecimal> answer(InvocationOnMock invocation) throws Throwable {
                calls++;
                if (calls == 1) {
                    return value1;
                } else if (calls == 2) {
                    return value2;
                } else if (calls == 3) {
                    throw new InsufficientParametersException("Operator * (position: 15): insufficient parameters");
                } else {
                    return value1;
                }
            }
        });

        Operator mockedAddition = mock(Addition.class);
        when(operatorFactory.get("+")).thenReturn(mockedAddition);
        Deque<BigDecimal> value3 = new ArrayDeque<>();
        value1.push(BigDecimal.valueOf(11));
        when(mockedAddition.calculate(any(), anyInt())).thenReturn(value3);

        String calculate = calculator.calculate(input1);

        assertEquals("Result1 should be as expected!", "Operator * (position: 15): insufficient parameters\r\n" +
                "stack: 11", calculate);


        verify(mockedMultiplication, times(3)).calculate(any(), anyInt());
        verify(mockedAddition).calculate(any(), anyInt());

    }*/


}
