package com.bbgk.mml.domain.util

import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable

class PageUtils {

    companion object {

        private const val PAGE_SIZE = 4

        fun getDefaultPageable(page: Int): Pageable {
            return PageRequest.of(page, PAGE_SIZE)
        }
    }
}