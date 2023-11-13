package io.everyone.travel.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;
import org.zalando.problem.spring.web.advice.ProblemHandling;

@Slf4j
@RestControllerAdvice
public class GeneralExceptionHandler implements ProblemHandling {

    @ExceptionHandler
    public ResponseEntity<?> handleNotFoundException(
            NotFoundException e
    ) {
        log.debug("not found exception - message: {}", e.getMessage(), e);

        var problem = Problem.builder()
                .withTitle(Status.NOT_FOUND.getReasonPhrase())
                .withStatus(Status.NOT_FOUND)
                .withDetail(e.getMessage())
                .build();

        return createResponseEntity(HttpStatus.NOT_FOUND, problem);
    }


    private static ResponseEntity<?> createResponseEntity(HttpStatus status, Problem problem) {
        return ResponseEntity
                .status(status)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(problem);
    }


}