package darkchoco.msacomponents.exception;

import darkchoco.msacomponents.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException ex) {
        System.out.println("Error Message: " + ex.getErrorMessage());
        return new ResponseEntity<>(new ErrorResponse(ex.getErrorMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FileDownloadException.class)
    public ResponseEntity<ErrorResponse> handleFileDownloadException(FileDownloadException ex) {
        System.out.println("Error Message: " + ex.getMessage());
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        System.out.println("Error Message: " + ex.getMessage());
        return new ResponseEntity<>(new ErrorResponse("System error"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
