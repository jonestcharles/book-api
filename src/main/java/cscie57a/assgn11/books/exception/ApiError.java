package cscie57a.assgn11.books.exception;

import java.time.LocalDateTime;

public class ApiError {

    private LocalDateTime timestamp;
    private int status;
    private String message;
    private String error;
    private String path;

    public ApiError(int status, String message, String error, String path) {
        super();
        this.status = status;
        this.message = message;
        this.error = error;
        this.timestamp = LocalDateTime.now();
        this.path = path;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getPath() {
        return path;
    }
}
