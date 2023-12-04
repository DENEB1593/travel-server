package io.everyone.travel.api.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

@Slf4j
@RestControllerAdvice
public class GeneralExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<?> handleNotFoundException(
        NotFoundException e, HttpServletRequest request
    ) {
        var uri = request.getRequestURI();
        log.warn("not found exception - uri:{}, message: {}", uri, e.getMessage());

        var problem = Problem.builder()
            .withTitle(Status.NOT_FOUND.getReasonPhrase())
            .withStatus(Status.NOT_FOUND)
            .withDetail(e.getMessage())
            .build();

        return createResponseEntity(HttpStatus.NOT_FOUND, problem);
    }

    @ExceptionHandler
    public ResponseEntity<?> handleMethodArgumentNotValidException(
        MethodArgumentNotValidException e, HttpServletRequest request
    ) {

        var uri = request.getRequestURI();
        var error = e.getBindingResult().getFieldError();
        String defaultMessage = error.getDefaultMessage();

        log.warn("invalid argument - uri: {}, message: {}", uri, defaultMessage);

        var problem = Problem.builder()
            .withTitle(Status.BAD_REQUEST.getReasonPhrase())
            .withStatus(Status.BAD_REQUEST)
            .withDetail(defaultMessage)
            .build();

        return createResponseEntity(HttpStatus.BAD_REQUEST, problem);
    }


    @ExceptionHandler
    public ResponseEntity<?> handleException(Exception e) {
        log.error("unexpected exception - message: {}", e.getMessage(), e);

        var problem = Problem.builder()
            .withTitle(Status.INTERNAL_SERVER_ERROR.getReasonPhrase())
            .withStatus(Status.INTERNAL_SERVER_ERROR)
            .withDetail("서버 오류가 발생하였습니다") // 예외 메세지는 직접 노출하지 않는다
            .build();

        return createResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, problem);
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