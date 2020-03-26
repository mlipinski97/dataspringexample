package pl.lipinski.springdataexample.util.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.I_AM_A_TEAPOT)
public class WrongCassetteStatusException extends RuntimeException {
    public WrongCassetteStatusException() {
        super();
    }
    public WrongCassetteStatusException(String message, Throwable cause) {
        super(message, cause);
    }
    public WrongCassetteStatusException(String message) {
        super(message);
    }
    public WrongCassetteStatusException(Throwable cause) {
        super(cause);
    }
}
