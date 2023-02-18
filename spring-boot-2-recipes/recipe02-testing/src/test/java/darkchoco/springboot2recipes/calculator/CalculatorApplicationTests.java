package darkchoco.springboot2recipes.calculator;

//import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
//import org.springframework.boot.test.system.OutputCaptureRule;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

// JUnit5에서는 @SpringBootTest에 @RunWith(SpringRunner.class)가 포함 되어있고,
// public을 명시하지 않아도 된다.
@SpringBootTest(classes = CalculatorApplication.class)
@ExtendWith(OutputCaptureExtension.class)
class CalculatorApplicationTests {

	// @Rule은 JUnit rule을 구성한다. 아래의 경우 OutputCapture rule을 사용하여
    // System.out, System.err를 intercept 후 assetion은 그 streams(System.out, System.err) 위에
	// 생성되는 output에 쓰게 된다.
//	public OutputCaptureRule capture = new OutputCaptureRule();

	// 아래 annotation으로 Operation이 하나 더 추가된다.
	@MockBean(name ="division")
	private Operation mockOperation;

	@Autowired
	private Calculator calculator;

	@Test
	public void calculatorShouldHave3Operations() {
		Object operations = ReflectionTestUtils.getField(calculator, "operations");
		assertThat((Collection) operations).hasSize(3);
	}

	@Test
	public void mockDivision(CapturedOutput output) {
		when(mockOperation.handles('/')).thenReturn(true);
		when(mockOperation.apply(14, 7)).thenReturn(2);

		calculator.calculate(14,7, '/');
        assertThat(output).contains("14 / 7 = 2");
//		capture.expect(Matchers.containsString("14 / 7 = 2"));
	}

	@Test
	public void doingMultiplicationShouldSucceed(CapturedOutput output) {
		calculator.calculate(12,13, '*');
        assertThat(output).contains("12 * 13 = 156");
//		capture.expect(Matchers.containsString("12 * 13 = 156"));
	}

	@Test
	public void doingAdditionShouldSucceed(CapturedOutput output) {
		calculator.calculate(12,13, '+');
        assertThat(output).contains("12 + 13 = 25");
//		capture.expect(Matchers.containsString("12 + 13 = 25"));
	}

//	@Test(expected = IllegalArgumentException.class)
//	public void doingDivisionShouldFail() {
//		calculator.calculate(12,13, '/');
//	}
    @Test
    public void doingDivisionShouldFail() {
        assertThrows(IllegalArgumentException.class, () -> calculator.calculate(12,13, '/'));
    }
}
