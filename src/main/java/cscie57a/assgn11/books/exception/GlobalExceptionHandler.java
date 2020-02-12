package cscie57a.assgn11.books.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.util.WebUtils;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * Provides handling for exceptions throughout this service.
     *
     * @param ex The target exception
     * @param request The current request
     */
    @ExceptionHandler({
            BookDeletionException.class,
            ResourceNotFoundException.class
    })
    @Nullable
    public final ResponseEntity<ApiError> handleException(Exception ex, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();

        logger.error("Handling " + ex.getClass().getSimpleName() + " due to " + ex.getMessage());

        if (ex instanceof BookDeletionException) {
            HttpStatus status = HttpStatus.NOT_FOUND;
            BookDeletionException bde = (BookDeletionException) ex;

            return handleBookDeletionException(bde, headers, status, request);
        }
        else if (ex instanceof ResourceNotFoundException) {
            HttpStatus status = HttpStatus.BAD_REQUEST;
            ResourceNotFoundException rnfe = (ResourceNotFoundException) ex;

            return handleResourceNotFoundException(rnfe, headers, status, request);
        }
        else {
            if (logger.isWarnEnabled()) {
                logger.warn("Unknown exception type: " + ex.getClass().getName());
            }

            HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
            return handleExceptionInternal(ex, null, headers, status, request);
        }
    }

    /**
     * Customize the response for BookDeletionException.
     *
     * @param bde The exception
     * @param headers The headers to be written to the response
     * @param status The selected response status
     * @return a {@code ResponseEntity} instance
     */
    protected ResponseEntity<ApiError> handleBookDeletionException(BookDeletionException bde,
                                                                   HttpHeaders headers, HttpStatus status,
                                                                   WebRequest request) {
        String error = bde.getMessage();
        return handleExceptionInternal(bde, new ApiError(status, error, error), headers, status, request);
    }

    /**
     * Customize the response for ResourceNotFoundException.
     *
     * @param enfe The exception
     * @param headers The headers to be written to the response
     * @param status The selected response status
     * @return a {@code ResponseEntity} instance
     */
    protected ResponseEntity<ApiError> handleResourceNotFoundException(ResourceNotFoundException enfe,
                                                                        HttpHeaders headers,
                                                                        HttpStatus status,
                                                                        WebRequest request) {
        String error = enfe.getMessage();
        return handleExceptionInternal(enfe, new ApiError(status, error, error), headers, status, request);
    }

    /**
     * A single place to customize the response body of all Exception types.
     *
     * <p>The default implementation sets the {@link WebUtils#ERROR_EXCEPTION_ATTRIBUTE}
     * request attribute and creates a {@link ResponseEntity} from the given
     * body, headers, and status.
     *
     * @param ex The exception
     * @param body The body for the response
     * @param headers The headers for the response
     * @param status The response status
     * @param request The current request
     */
    protected ResponseEntity<ApiError> handleExceptionInternal(Exception ex, @Nullable ApiError body,
                                                               HttpHeaders headers, HttpStatus status,
                                                               WebRequest request) {
        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
        }

        return new ResponseEntity<>(body, headers, status);
    }

}
