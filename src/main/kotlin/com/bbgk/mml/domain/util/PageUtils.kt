package com.bbgk.mml.domain.util

import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable

class PageUtils {
    fun getDefaultPageable() {
        throw UnsupportedOperationException("유틸 클래스는 인스턴스화 할 수 없습니다.")
    }

    companion object {
        const val PAGE_SIZE = 4

        fun getDefaultPageable(page: Int): Pageable {
            return PageRequest.of(page, PAGE_SIZE)
        }
    }
}