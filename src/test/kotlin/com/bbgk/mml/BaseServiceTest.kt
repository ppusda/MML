package com.bbgk.mml

import com.bbgk.mml.domain.entity.Member
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable

@ExtendWith(MockitoExtension::class)
abstract class BaseServiceTest {

    // Id config
    val PLAYLIST_ID = 1L
    val MUSIC_ID = 1L
    val PLAYLIST_MUSIC_ID = 1L

    // Page Config
    val DATA_SIZE = 5
    val PAGE_NUMBER = 0
    val PAGE_SIZE = 5
    val pageable: Pageable = PageRequest.of(PAGE_NUMBER, PAGE_SIZE)

    // Message Config
    val PLAYLIST_MESSAGE = "존재하지 않는 플레이리스트 내 음악입니다."

    // Member Config
    val OWNER = Member("testMember", "1234")
}