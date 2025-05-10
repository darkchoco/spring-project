package darkchoco.msacomponents.exception;

import lombok.Getter;

@Getter
public class BadRequestException extends RuntimeException {

    private final String errorMessage;

    // BadRequestException 예외가 발생하는 과정에서 errorMessage를 반드시 넣도록 해당 메시지를 인자로 받는 생성자를
    // 하나만 놔둔다.
    public BadRequestException(String errorMessage) {
        super();
        this.errorMessage = errorMessage;
    }
}
