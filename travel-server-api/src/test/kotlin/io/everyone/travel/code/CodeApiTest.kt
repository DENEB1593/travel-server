package io.everyone.travel.code

import io.everyone.travel.MockMvcApiTest
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE

class CodeControllerTest: MockMvcApiTest() {

    @Test
    fun whenRequestingCode_thenReturnNationCodeList() {
        Given {
            contentType(APPLICATION_JSON_VALUE)
        } When {
            get("/api/code/nation")
        } Then {
            statusCode(HttpStatus.OK.value())
            contentType(APPLICATION_JSON_VALUE)
        }
    }

}

