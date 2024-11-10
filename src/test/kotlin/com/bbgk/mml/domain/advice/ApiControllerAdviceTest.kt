package com.bbgk.mml.domain.advice

import com.bbgk.mml.domain.exception.MmlBadRequestException
import com.bbgk.mml.domain.exception.MmlInternalServerErrorException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import kotlin.test.Test

class ApiControllerAdviceTest {

    private val MESSAGE_BAD_REQUEST = "잘못된 입력입니다."
    private val MESSAGE_INTERNAL_SERVER_ERROR = "예기치 못한 오류가 발생했습니다."

    @Test
    fun `MmlBadRequestException 처리 테스트`() {
        // Given
        val exception = MmlBadRequestException(MESSAGE_BAD_REQUEST)

        // When
        val responseEntity: ResponseEntity<String> = ApiControllerAdvice().handleException(exception)

        // Then
        assert(responseEntity.statusCode == HttpStatus.BAD_REQUEST)
        assert(responseEntity.body == MESSAGE_BAD_REQUEST)
    }

    @Test
    fun `MmlInternalServerErrorException 처리 테스트`() {
        // Given
        val exception = MmlInternalServerErrorException(MESSAGE_INTERNAL_SERVER_ERROR)

        // When
        val responseEntity: ResponseEntity<String> = ApiControllerAdvice().handleException(exception)

        // Then
        assert(responseEntity.statusCode == HttpStatus.INTERNAL_SERVER_ERROR)
        assert(responseEntity.body == MESSAGE_INTERNAL_SERVER_ERROR)
    }
}