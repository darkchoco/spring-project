package darkchoco.springboot2recipes.calculator.operation;

import darkchoco.springboot2recipes.calculator.Operation;
import org.springframework.stereotype.Component;

@Component
class Addition implements Operation {

    @Override
    public int apply(int lhs, int rhs) {
        return lhs + rhs;
    }

    @Override
    public boolean handles(char op) {
        return '+' == op;
    }
}
