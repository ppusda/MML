package com.bbgk.mml.domain.util

import com.bbgk.mml.domain.exception.GlobalExceptionMessage
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable

class PageUtils {
    fun getDefaultPageable() {
        throw UnsupportedOperationException(GlobalExceptionMessage.CANNOT_INITIALIZE_UTILS.message)
    }

    companion object {
        const val PAGE_SIZE = 4

        fun getDefaultPageable(page: Int): Pageable {
            return PageRequest.of(page, PAGE_SIZE)
        }
    }
}