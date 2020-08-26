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
        ValueStatement mocked5ValueStatement = mock(ValueStatement.class);
        when(operatorFactory.get(eq("5"), anyInt(), any())).thenReturn(mocked5ValueStatement);
        ValueStatement mocked2ValueStatement = mock(ValueStatement.class);
        when(operatorFactory.get(eq("2"), anyInt(), any())).thenReturn(mocked2ValueStatement);

        calculator.execute(input);

        verify(operatorFactory).get(eq("5"), anyInt(), any());
        verify(operatorFactory).get(eq("2"), anyInt(), any());
        verify(memory, times(2)).save(any());
        verify(memory, never()).getOperations();
        verify(memory).result();
    }

    @Test
    public void testTestCase2() {

        String input1 = "2 sqrt";

        SquareRoot mockedSquareRoot = mock(SquareRoot.class);
        when(operatorFactory.get(eq("sqrt"), anyInt(), any())).thenReturn(mockedSquareRoot);
        ValueStatement mocked2ValueStatement = mock(ValueStatement.class);
        when(operatorFactory.get(eq("2"), anyInt(), any())).thenReturn(mocked2ValueStatement);

        String input2 = "clear 9 sqrt";
        ValueStatement mocked9ValueStatement = mock(ValueStatement.class);
        when(operatorFactory.get(eq("9"), anyInt(), any())).thenReturn(mocked9ValueStatement);

        calculator.execute(input1);
        calculator.execute(input2);

        verify(memory, times(4)).save(any());
        verify(memory).clear();
        verify(operatorFactory, times(2)).get(eq("sqrt"), anyInt(), any());
        verify(operatorFactory).get(eq("2"), anyInt(), any());
        verify(operatorFactory).get(eq("9"), anyInt(), any());
        verify(memory, times(2)).result();
    }

    @Test
    public void testTestCase3() {

        String input1 = "5 2 -";
        String input2 = "3 -";
        String input3 = "clear";

        Subtraction mockedSubtraction = mock(Subtraction.class);
        when(operatorFactory.get(eq("-"), anyInt(), any())).thenReturn(mockedSubtraction);
        ValueStatement mocked5ValueStatement = mock(ValueStatement.class);
        when(operatorFactory.get(eq("5"), anyInt(), any())).thenReturn(mocked5ValueStatement);
        ValueStatement mocked2ValueStatement = mock(ValueStatement.class);
        when(operatorFactory.get(eq("2"), anyInt(), any())).thenReturn(mocked2ValueStatement);
        ValueStatement mocked3ValueStatement = mock(ValueStatement.class);
        when(operatorFactory.get(eq("3"), anyInt(), any())).thenReturn(mocked3ValueStatement);

        calculator.execute(input1);
        calculator.execute(input2);
        calculator.execute(input3);


        verify(memory, times(5)).save(any());
        verify(memory).clear();
        verify(operatorFactory, times(2)).get(eq("-"), anyInt(), any());
        verify(operatorFactory).get(eq("5"), anyInt(), any());
        verify(operatorFactory).get(eq("2"), anyInt(), any());
        verify(operatorFactory).get(eq("3"), anyInt(), any());
        verify(memory, times(3)).result();
    }

    @Test
    public void testTestCase5() {

        String input1 = "7 12 2 /";
        String input2 = "*";
        String input3 = "4 /";

        ValueStatement mocked7ValueStatement = mock(ValueStatement.class);
        when(operatorFactory.get(eq("7"), anyInt(), any())).thenReturn(mocked7ValueStatement);
        ValueStatement mocked2ValueStatement = mock(ValueStatement.class);
        when(operatorFactory.get(eq("2"), anyInt(), any())).thenReturn(mocked2ValueStatement);
        ValueStatement mocked12ValueStatement = mock(ValueStatement.class);
        when(operatorFactory.get(eq("12"), anyInt(), any())).thenReturn(mocked12ValueStatement);
        ValueStatement mocked4ValueStatement = mock(ValueStatement.class);
        when(operatorFactory.get(eq("4"), anyInt(), any())).thenReturn(mocked4ValueStatement);
        Multiplication mockedMultiplication = mock(Multiplication.class);
        when(operatorFactory.get(eq("*"), anyInt(), any())).thenReturn(mockedMultiplication);
        Division mockedDivision = mock(Division.class);
        when(operatorFactory.get(eq("/"), anyInt(), any())).thenReturn(mockedDivision);

        calculator.execute(input1);
        calculator.execute(input2);
        calculator.execute(input3);

        verify(memory, times(7)).save(any());
        verify(operatorFactory, times(2)).get(eq("/"), anyInt(), any());
        verify(operatorFactory).get(eq("*"), anyInt(), any());
        verify(operatorFactory).get(eq("7"), anyInt(), any());
        verify(operatorFactory).get(eq("2"), anyInt(), any());
        verify(operatorFactory).get(eq("12"), anyInt(), any());
        verify(operatorFactory).get(eq("4"), anyInt(), any());
        verify(memory, times(3)).result();
    }

    @Test
    public void testTestCase6() {

        String input1 = "1 2 3 4 5";
        String input2 = "*";
        String input3 = "clear 3 4 -";

        ValueStatement mocked1ValueStatement = mock(ValueStatement.class);
        when(operatorFactory.get(eq("1"), anyInt(), any())).thenReturn(mocked1ValueStatement);
        ValueStatement mocked2ValueStatement = mock(ValueStatement.class);
        when(operatorFactory.get(eq("2"), anyInt(), any())).thenReturn(mocked2ValueStatement);
        ValueStatement mocked3ValueStatement = mock(ValueStatement.class);
        when(operatorFactory.get(eq("3"), anyInt(), any())).thenReturn(mocked3ValueStatement);
        ValueStatement mocked4ValueStatement = mock(ValueStatement.class);
        when(operatorFactory.get(eq("4"), anyInt(), any())).thenReturn(mocked4ValueStatement);
        ValueStatement mocked5ValueStatement = mock(ValueStatement.class);
        when(operatorFactory.get(eq("5"), anyInt(), any())).thenReturn(mocked5ValueStatement);
        Multiplication mockedMultiplication = mock(Multiplication.class);
        when(operatorFactory.get(eq("*"), anyInt(), any())).thenReturn(mockedMultiplication);
        Subtraction mockedSubtraction = mock(Subtraction.class);
        when(operatorFactory.get(eq("-"), anyInt(), any())).thenReturn(mockedSubtraction);

        calculator.execute(input1);
        calculator.execute(input2);
        calculator.execute(input3);


        verify(memory, times(9)).save(any());
        verify(operatorFactory).get(eq("-"), anyInt(), any());
        verify(operatorFactory).get(eq("*"), anyInt(), any());
        verify(operatorFactory).get(eq("1"), anyInt(), any());
        verify(operatorFactory).get(eq("2"), anyInt(), any());
        verify(operatorFactory, times(2)).get(eq("3"), anyInt(), any());
        verify(operatorFactory, times(2)).get(eq("4"), anyInt(), any());
        verify(operatorFactory).get(eq("5"), anyInt(), any());
        verify(memory, times(3)).result();
        verify(memory).clear();

    }

    @Test
    public void testTestCase7() {

        String input1 = "1 2 3 4 5";
        String input2 = "* * * *";

        ValueStatement mocked1ValueStatement = mock(ValueStatement.class);
        when(operatorFactory.get(eq("1"), anyInt(), any())).thenReturn(mocked1ValueStatement);
        ValueStatement mocked2ValueStatement = mock(ValueStatement.class);
        when(operatorFactory.get(eq("2"), anyInt(), any())).thenReturn(mocked2ValueStatement);
        ValueStatement mocked3ValueStatement = mock(ValueStatement.class);
        when(operatorFactory.get(eq("3"), anyInt(), any())).thenReturn(mocked3ValueStatement);
        ValueStatement mocked4ValueStatement = mock(ValueStatement.class);
        when(operatorFactory.get(eq("4"), anyInt(), any())).thenReturn(mocked4ValueStatement);
        ValueStatement mocked5ValueStatement = mock(ValueStatement.class);
        when(operatorFactory.get(eq("5"), anyInt(), any())).thenReturn(mocked5ValueStatement);
        Multiplication mockedMultiplication = mock(Multiplication.class);
        when(operatorFactory.get(eq("*"), anyInt(), any())).thenReturn(mockedMultiplication);

        calculator.execute(input1);
        calculator.execute(input2);

        verify(operatorFactory, times(4)).get(eq("*"), anyInt(), any());
        verify(operatorFactory).get(eq("1"), anyInt(), any());
        verify(operatorFactory).get(eq("2"), anyInt(), any());
        verify(operatorFactory).get(eq("3"), anyInt(), any());
        verify(operatorFactory).get(eq("4"), anyInt(), any());
        verify(operatorFactory).get(eq("5"), anyInt(), any());
        verify(memory, times(9)).save(any());
        verify(memory, times(2)).result();
    }

    @Test
    public void testTestCase8() {

        String input1 = "1 2 3 * 5 + * * 6 5";

        ValueStatement mocked1ValueStatement = mock(ValueStatement.class);
        when(operatorFactory.get(eq("1"), anyInt(), any())).thenReturn(mocked1ValueStatement);
        ValueStatement mocked2ValueStatement = mock(ValueStatement.class);
        when(operatorFactory.get(eq("2"), anyInt(), any())).thenReturn(mocked2ValueStatement);
        ValueStatement mocked3ValueStatement = mock(ValueStatement.class);
        when(operatorFactory.get(eq("3"), anyInt(), any())).thenReturn(mocked3ValueStatement);
        ValueStatement mocked5ValueStatement = mock(ValueStatement.class);
        when(operatorFactory.get(eq("5"), anyInt(), any())).thenReturn(mocked5ValueStatement);
        Addition mockedAddition = mock(Addition.class);
        when(operatorFactory.get(eq("+"), anyInt(), any())).thenReturn(mockedAddition);
        Multiplication mockedMultiplication = mock(Multiplication.class);
        when(operatorFactory.get(eq("*"), anyInt(), any())).thenAnswer(new Answer<Statement>() {
            private int calls = 0;

            @Override
            public Statement answer(InvocationOnMock invocation) {
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
        verify(operatorFactory).get(eq("1"), anyInt(), any());
        verify(operatorFactory).get(eq("2"), anyInt(), any());
        verify(operatorFactory).get(eq("3"), anyInt(), any());
        verify(operatorFactory).get(eq("5"), anyInt(), any());
        verify(operatorFactory, never()).get(eq("6"), anyInt(), any());
        verify(memory, times(7)).save(any());
        verify(memory).result();

    }


}
