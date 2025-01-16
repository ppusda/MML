package com.bbgk.mml.domain.data

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.springframework.http.HttpStatus
import kotlin.test.Test

class ApiResponseTest {

    @Test
    @DisplayName("ApiResponse 생성 검증")
    fun testApiResponseInitialize() {
        // given
        val response = ApiResponse<String>(HttpStatus.OK)

        // then
        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
    }

    @Test
    @DisplayName("데이터 생성 성공 검증")
    fun testSuccessCreate() {
        // when
        val response = ApiResponse.successCreate()

        // then
        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(response.body).isEqualTo("데이터가 저장되었습니다.")
    }

    @Test
    @DisplayName("데이터 수정 성공 검증")
    fun testSuccessUpdate() {
        // when
        val response = ApiResponse.successUpdate()

        // then
        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(response.body).isEqualTo("데이터가 수정되었습니다.")
    }

    @Test
    @DisplayName("데이터 삭제 성공 검증")
    fun testSuccessDelete() {
        // when
        val response = ApiResponse.successDelete()

        // then
        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(response.body).isEqualTo("데이터가 삭제되었습니다.")
    }
}