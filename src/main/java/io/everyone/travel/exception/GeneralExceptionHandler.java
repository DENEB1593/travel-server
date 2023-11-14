package io.everyone.travel.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;
import org.zalando.problem.spring.web.advice.ProblemHandling;

import java.util.List;

@Slf4j
@RestControllerAdvice
public class GeneralExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<?> handleNotFoundException(NotFoundException e) {
        log.debug("not found exception - message: {}", e.getMessage(), e);

        var problem = Problem.builder()
            .withTitle(Status.NOT_FOUND.getReasonPhrase())
            .withStatus(Status.NOT_FOUND)
            .withDetail(e.getMessage())
            .build();

        return createResponseEntity(HttpStatus.NOT_FOUND, problem);
    }

    @ExceptionHandler
    public ResponseEntity<?> handleMethodArgumentNotValidException(
        MethodArgumentNotValidException e
    ) {

        var error = e.getBindingResult().getFieldError();
        String defaultMessage = error.getDefaultMessage();

        log.warn("invalid argument: {}", defaultMessage);

        var problem = Problem.builder()
            .withTitle(Status.BAD_REQUEST.getReasonPhrase())
            .withStatus(Status.BAD_REQUEST)
            .withDetail(defaultMessage)
            .build();

        return createResponseEntity(HttpStatus.BAD_REQUEST, problem);
    }


    private static ResponseEntity<?> createResponseEntity(
        HttpStatus status, Problem problem
    ) {
        return ResponseEntity
            .status(status)
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .body(problem);
    }


}