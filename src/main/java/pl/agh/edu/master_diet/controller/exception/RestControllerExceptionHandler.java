package pl.agh.edu.master_diet.controller.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pl.agh.edu.master_diet.core.model.standard.ErrorResponse;
import pl.agh.edu.master_diet.core.model.standard.StandardApiResponse;
import pl.agh.edu.master_diet.exception.DeleteException;

import java.util.Arrays;

@Slf4j
@ControllerAdvice
public final class RestControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({DeleteException.class})
    public ResponseEntity<StandardApiResponse> handleDeleteException(final DeleteException e) {
        final StandardApiResponse apiResponse = StandardApiResponse.builder()
                .success(false)
                .message("Problem with deletion")
                .error(ErrorResponse.builder()
                        .code("deletion-error")
                        .debugDetails(e.getLocalizedMessage())
                        .build())
                .build();

        log.warn(e.getMessage());
        log.warn(Arrays.toString(e.getStackTrace()));

        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(apiResponse);
    }
}

