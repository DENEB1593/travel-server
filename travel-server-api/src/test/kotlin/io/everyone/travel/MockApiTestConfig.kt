package io.everyone.travel

import com.fasterxml.jackson.databind.ObjectMapper
import io.restassured.config.EncoderConfig
import io.restassured.config.ObjectMapperConfig
import io.restassured.module.mockmvc.RestAssuredMockMvc
import io.restassured.module.mockmvc.config.MockMvcConfig
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.web.context.WebApplicationContext

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
abstract class MockMvcApiTest {

    @BeforeEach
    fun setUp(
        @Autowired webApplicationContext: WebApplicationContext,
        @Autowired objectMapper: ObjectMapper
    ) {
        RestAssuredMockMvc.webAppContextSetup(webApplicationContext)
        RestAssuredMockMvc.config = RestAssuredMockMvc.config()
            .objectMapperConfig(
                // Jackson
                ObjectMapperConfig.objectMapperConfig()
                    .jackson2ObjectMapperFactory { _, _ -> objectMapper }
            )
            .encoderConfig(
                EncoderConfig.encoderConfig()
                    .appendDefaultContentCharsetToContentTypeIfUndefined(false)
            )
            .mockMvcConfig(MockMvcConfig.mockMvcConfig()
                .dontAutomaticallyApplySpringSecurityMockMvcConfigurer()
            )

        // 결과는 print 한다.
        RestAssuredMockMvc.resultHandlers(MockMvcResultHandlers.print(System.out))
    }

    @AfterEach
    fun tearDown() {
        RestAssuredMockMvc.reset()
    }

}
