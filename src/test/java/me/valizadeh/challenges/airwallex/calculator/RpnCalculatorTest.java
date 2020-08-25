package me.valizadeh.challenges.airwallex.calculator;

import me.valizadeh.challenges.airwallex.exception.InsufficientParametersException;
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
    public void testTestCase1() {

        String input = "5 2";

        calculator.execute(input);

        verify(operatorFactory, never()).get(anyString(), anyInt(), any());
        verify(memory, times(2)).save(any());
        verify(memory, never()).getOperations();
        verify(memory).result();
    }

    @Test
    public void testTestCase2() {

        String input1 = "2 sqrt";

        SquareRoot mockedSquareRoot = mock(SquareRoot.class);
        when(operatorFactory.get(eq("sqrt"), anyInt(), any())).thenReturn(mockedSquareRoot);
        when(memory.getOperations()).thenReturn(new ArrayDeque<>());

        String input2 = "clear 9 sqrt";

        calculator.execute(input1);
        calculator.execute(input2);

        verify(memory, times(2)).getOperations();
        verify(memory, times(4)).save(any());
        verify(memory).clear();
        verify(operatorFactory, times(2)).get(eq("sqrt"), anyInt(), any());
        verify(memory, times(2)).result();
    }

    @Test
    public void testTestCase3() {

        String input1 = "5 2 -";
        String input2 = "3 -";
        String input3 = "clear";

        when(memory.getOperations()).thenReturn(new ArrayDeque<>());
        Subtraction mockedSubtraction = mock(Subtraction.class);
        when(operatorFactory.get(eq("-"), anyInt(), any())).thenReturn(mockedSubtraction);

        calculator.execute(input1);
        calculator.execute(input2);
        calculator.execute(input3);


        verify(memory, times(2)).getOperations();
        verify(memory, times(5)).save(any());
        verify(memory).clear();
        verify(operatorFactory, times(2)).get(eq("-"), anyInt(), any());
        verify(memory, times(3)).result();
    }

    @Test
    public void testTestCase5() {

        String input1 = "7 12 2 /";
        String input2 = "*";
        String input3 = "4 /";

        when(memory.getOperations()).thenReturn(new ArrayDeque<>());
        Multiplication mockedMultiplication = mock(Multiplication.class);
        when(operatorFactory.get(eq("*"), anyInt(), any())).thenReturn(mockedMultiplication);
        Division mockedDivision = mock(Division.class);
        when(operatorFactory.get(eq("/"), anyInt(), any())).thenReturn(mockedDivision);

        calculator.execute(input1);
        calculator.execute(input2);
        calculator.execute(input3);

        verify(memory, times(3)).getOperations();
        verify(memory, times(7)).save(any());
        verify(operatorFactory, times(2)).get(eq("/"), anyInt(), any());
        verify(operatorFactory).get(eq("*"), anyInt(), any());
        verify(memory, times(3)).result();
    }

    @Test
    public void testTestCase6() {

        String input1 = "1 2 3 4 5";
        String input2 = "*";
        String input3 = "clear 3 4 -";

        when(memory.getOperations()).thenReturn(new ArrayDeque<>());
        Multiplication mockedMultiplication = mock(Multiplication.class);
        when(operatorFactory.get(eq("*"), anyInt(), any())).thenReturn(mockedMultiplication);
        Subtraction mockedSubtraction = mock(Subtraction.class);
        when(operatorFactory.get(eq("-"), anyInt(), any())).thenReturn(mockedSubtraction);

        calculator.execute(input1);
        calculator.execute(input2);
        calculator.execute(input3);


        verify(memory, times(2)).getOperations();
        verify(memory, times(9)).save(any());
        verify(operatorFactory).get(eq("-"), anyInt(), any());
        verify(operatorFactory).get(eq("*"), anyInt(), any());
        verify(memory, times(3)).result();
        verify(memory).clear();

    }

    @Test
    public void testTestCase7() {

        String input1 = "1 2 3 4 5";
        String input2 = "* * * *";

        when(memory.getOperations()).thenReturn(new ArrayDeque<>());
        Multiplication mockedMultiplication = mock(Multiplication.class);
        when(operatorFactory.get(eq("*"), anyInt(), any())).thenReturn(mockedMultiplication);

        calculator.execute(input1);
        calculator.execute(input2);

        verify(operatorFactory, times(4)).get(eq("*"), anyInt(), any());
        verify(memory, times(4)).getOperations();
        verify(memory, times(9)).save(any());
        verify(memory, times(2)).result();
    }

    @Test
    public void testTestCase8() {

        String input1 = "1 2 3 * 5 + * * 6 5";
        when(memory.getOperations()).thenReturn(new ArrayDeque<>());
        Addition mockedAddition = mock(Addition.class);
        when(operatorFactory.get(eq("+"), anyInt(), any())).thenReturn(mockedAddition);
        Multiplication mockedMultiplication = mock(Multiplication.class);
        when(operatorFactory.get(eq("*"), anyInt(), any())).thenAnswer(new Answer<Operator>() {
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

        verify(operatorFactory, times(3)).get(eq("*"), anyInt(), any());
        verify(operatorFactory).get(eq("+"), anyInt(), any());
        verify(memory, times(4)).getOperations();
        verify(memory, times(7)).save(any());
        verify(memory).result();

    }


}
