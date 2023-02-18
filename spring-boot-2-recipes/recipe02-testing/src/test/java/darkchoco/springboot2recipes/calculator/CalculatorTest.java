package darkchoco.springboot2recipes.calculator;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;

import static org.mockito.Mockito.*;

public class CalculatorTest {

    private Calculator calculator;
    private Operation mockOperation;

//    @Before
//    public void setup() {
//        mockOperation = Mockito.mock(Operation.class);
//        calculator = new Calculator(Collections.singletonList(mockOperation));
//    }

//    @Test(expected = IllegalArgumentException.class)
    @Test
    public void throwExceptionWhenNoSuitableOperationFound() {
        when(mockOperation.handles(anyChar())).thenReturn(false);
        calculator.calculate(2, 2, '*');
    }

    @Test
    public void shouldCallApplyMethodWhenSuitableOperationFound() {
        when(mockOperation.handles(anyChar())).thenReturn(true);
        when(mockOperation.apply(2, 2)).thenReturn(4);

        calculator.calculate(2, 2, '*');

        verify(mockOperation, times(1)).apply(2,2);
    }
}
