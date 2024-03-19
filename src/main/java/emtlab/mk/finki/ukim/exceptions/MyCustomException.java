package emtlab.mk.finki.ukim.exceptions;

import org.springframework.dao.DataAccessException;

public class MyCustomException extends RuntimeException {

    public MyCustomException(String message, DataAccessException cause) {
        super(message, cause);
    }
}
