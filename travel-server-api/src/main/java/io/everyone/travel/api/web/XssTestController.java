package io.everyone.travel.api.web;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Profile("local")
@RestController
@RequestMapping("api/some")
public class XssTestController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @GetMapping("/")
    public ResponseEntity<String> xssTest(
        @RequestParam String message, HttpServletRequest request
    ) {
        log.info("header:{}. message: {}", request.getHeader("XSS-HEADER"), message);

        return ResponseEntity.ok(message);
    }
}
