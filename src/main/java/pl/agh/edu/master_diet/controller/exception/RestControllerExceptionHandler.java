package pl.agh.edu.master_diet.controller.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pl.agh.edu.master_diet.exception.DeleteException;
import pl.agh.edu.master_diet.exception.UserPlanNotFoundException;

@Slf4j
@ControllerAdvice
public final class RestControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({DeleteException.class})
    public ResponseEntity<Object> handleDeleteException(final DeleteException e,
                                                        final WebRequest request) {
        log.error(e.getMessage(), e);
        return handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler({UserPlanNotFoundException.class})
    public ResponseEntity<Object> handleUserPlanNotFoundException(final UserPlanNotFoundException e,
                                                                  final WebRequest request) {
        log.error(e.getMessage(), e);
        return handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), HttpStatus.UNPROCESSABLE_ENTITY, request);
    }
}

