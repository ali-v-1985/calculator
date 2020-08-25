package me.valizadeh.challenges.airwallex.calculator;

import me.valizadeh.challenges.airwallex.exception.InsufficientParametersException;
import me.valizadeh.challenges.airwallex.exception.UnknownOperator;
import me.valizadeh.challenges.airwallex.memory.Memory;
import me.valizadeh.challenges.airwallex.operator.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.util.ArrayDeque;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class RpnCalculatorTest {

    @InjectMocks
    private RpnCalculator calculator;

    @Mock
    private OperatorFactory operatorFactory;

    @Mock
    private Memory memory;

    @Test
    public void testTestCase1() throws UnknownOperator {

        String input = "5 2";
        when(memory.getOperations()).thenReturn(new ArrayDeque<>());

        calculator.execute(input);

        verify(operatorFactory, never()).get(anyString(), any());
        verify(memory, times(2)).getOperations();
        verify(memory).getMemory();
    }

    @Test
    public void testTestCase2() throws UnknownOperator {

        String input1 = "2 sqrt";

        SquareRoot mockedSquareRoot = mock(SquareRoot.class);
        when(operatorFactory.get(eq("sqrt"), any())).thenReturn(mockedSquareRoot);
        when(memory.getOperations()).thenReturn(new ArrayDeque<>());

        String input2 = "clear 9 sqrt";

        calculator.execute(input1);
        calculator.execute(input2);

        verify(memory, times(6)).getOperations();
        verify(memory).clear();
        verify(operatorFactory, times(2)).get(eq("sqrt"), any());
        verify(memory, times(2)).getMemory();
    }

    @Test
    public void testTestCase3() throws UnknownOperator {

        String input1 = "5 2 -";
        String input2 = "3 -";
        String input3 = "clear";

        when(memory.getOperations()).thenReturn(new ArrayDeque<>());
        Subtraction mockedSubtraction = mock(Subtraction.class);
        when(operatorFactory.get(eq("-"), any())).thenReturn(mockedSubtraction);

        calculator.execute(input1);
        calculator.execute(input2);
        calculator.execute(input3);


        verify(memory, times(7)).getOperations();
        verify(memory).clear();
        verify(operatorFactory, times(2)).get(eq("-"), any());
        verify(memory, times(3)).getMemory();
    }

    @Test
    public void testTestCase5() throws UnknownOperator {

        String input1 = "7 12 2 /";
        String input2 = "*";
        String input3 = "4 /";

        when(memory.getOperations()).thenReturn(new ArrayDeque<>());
        Multiplication mockedMultiplication = mock(Multiplication.class);
        when(operatorFactory.get(eq("*"), any())).thenReturn(mockedMultiplication);
        Division mockedDivision = mock(Division.class);
        when(operatorFactory.get(eq("/"), any())).thenReturn(mockedDivision);

        calculator.execute(input1);
        calculator.execute(input2);
        calculator.execute(input3);

        verify(memory, times(10)).getOperations();
        verify(operatorFactory, times(2)).get(eq("/"), any());
        verify(operatorFactory).get(eq("*"), any());
        verify(memory, times(3)).getMemory();
    }

    @Test
    public void testTestCase6() throws UnknownOperator {

        String input1 = "1 2 3 4 5";
        String input2 = "*";
        String input3 = "clear 3 4 -";

        when(memory.getOperations()).thenReturn(new ArrayDeque<>());
        Multiplication mockedMultiplication = mock(Multiplication.class);
        when(operatorFactory.get(eq("*"), any())).thenReturn(mockedMultiplication);
        Subtraction mockedSubtraction = mock(Subtraction.class);
        when(operatorFactory.get(eq("-"), any())).thenReturn(mockedSubtraction);

        calculator.execute(input1);
        calculator.execute(input2);
        calculator.execute(input3);


        verify(memory, times(11)).getOperations();
        verify(operatorFactory).get(eq("-"), any());
        verify(operatorFactory).get(eq("*"), any());
        verify(memory, times(3)).getMemory();
        verify(memory).clear();

    }

    @Test
    public void testTestCase7() throws UnknownOperator {

        String input1 = "1 2 3 4 5";
        String input2 = "* * * *";

        when(memory.getOperations()).thenReturn(new ArrayDeque<>());
        Multiplication mockedMultiplication = mock(Multiplication.class);
        when(operatorFactory.get(eq("*"), any())).thenReturn(mockedMultiplication);

        calculator.execute(input1);
        calculator.execute(input2);

        verify(operatorFactory, times(4)).get(eq("*"), any());
        verify(memory, times(13)).getOperations();
        verify(memory, times(2)).getMemory();
    }

    @Test
    public void testTestCase8() throws UnknownOperator {

        String input1 = "1 2 3 * 5 + * * 6 5";
        when(memory.getOperations()).thenReturn(new ArrayDeque<>());
        Addition mockedAddition = mock(Addition.class);
        when(operatorFactory.get(eq("+"), any())).thenReturn(mockedAddition);
        Multiplication mockedMultiplication = mock(Multiplication.class);
        when(operatorFactory.get(eq("*"), any())).thenAnswer(new Answer<Operator>() {
            private int calls = 0;
            @Override
            public Operator answer(InvocationOnMock invocation) throws Throwable {
                calls++;
                if (calls < 3) {
                    return mockedMultiplication;
                } else {
                    throw new InsufficientParametersException("");
                }
            }
        });

        calculator.execute(input1);

        verify(operatorFactory, times(3)).get(eq("*"), any());
        verify(operatorFactory).get(eq("+"), any());
        verify(memory, times(11)).getOperations();
        verify(memory).getMemory();

    }


}
