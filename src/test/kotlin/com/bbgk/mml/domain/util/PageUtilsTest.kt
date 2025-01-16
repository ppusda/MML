package com.bbgk.mml.domain.util

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.data.domain.PageRequest

class PageUtilsTest {

    @Test
    fun `PageUtils 생성 테스트`() {
        // when
        val exception = assertThrows<UnsupportedOperationException> {
            val pageUtils = PageUtils()
            pageUtils.getDefaultPageable()
        }

        // then
        assertEquals("유틸 클래스는 인스턴스화 할 수 없습니다.", exception.message)

    }

    @Test
    fun `기본 Pageable 정보를 가져온다`() {
        // given
        val page = 1

        // when
        val pageable = PageUtils.getDefaultPageable(page)
        val validPageable = PageRequest.of(page, 4);

        // then
        assertEquals(pageable, validPageable)
    }
}