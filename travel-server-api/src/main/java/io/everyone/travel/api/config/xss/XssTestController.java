package io.everyone.travel.api.config.xss;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "XSS 테스트용 API", description = "XSS 테스트용 API이며, local 환경에서만 구동된다.")
@Profile("local")
@RestController
@RequestMapping("api/xss")
public class XssTestController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @GetMapping("/test")
    public ResponseEntity<String> xssTest(
        @RequestParam String message, HttpServletRequest request
    ) {
        log.info("header:{}. message: {}", request.getHeader("XSS-HEADER"), message);

        return ResponseEntity.ok(message);
    }
}
