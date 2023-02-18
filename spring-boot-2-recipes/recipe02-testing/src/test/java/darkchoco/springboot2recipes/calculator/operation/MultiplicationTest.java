package darkchoco.springboot2recipes.calculator.operation;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = Multiplication.class)
public class MultiplicationTest {

    private final Multiplication addition = new Multiplication();

    @Test
    public void shouldMatchOperation() {
        assertThat(addition.handles('*')).isTrue();
        assertThat(addition.handles('/')).isFalse();
    }

    @Test
    public void shouldCorrectlyApplyFormula() {
        assertThat(addition.apply(2, 2)).isEqualTo(4);
        assertThat(addition.apply(12, 10)).isEqualTo(120);
    }
}
