package darkchoco.springboot2recipes.calculator.operation;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = Addition.class)
public class AdditionTest {

    private final Addition addition = new Addition();

    @Test
    public void shouldMatchOperation() {
        assertThat(addition.handles('+')).isTrue();
        assertThat(addition.handles('/')).isFalse();
    }

    @Test
    public void shouldCorrectlyApplyFormula() {
        assertThat(addition.apply(2, 2)).isEqualTo(4);
        assertThat(addition.apply(12, 88)).isEqualTo(100);
    }
}
