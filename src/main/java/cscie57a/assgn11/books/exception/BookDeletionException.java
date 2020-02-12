package cscie57a.assgn11.books.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BookDeletionException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public BookDeletionException() {
        super();
    }

    public BookDeletionException(String message, Throwable cause, boolean enableSuppression,
                                 boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public BookDeletionException(String message, Throwable cause) {
        super(message, cause);
    }

    public BookDeletionException(String message) {
        super(message);
    }

    public BookDeletionException(Throwable cause) {
        super(cause);
    }
}
