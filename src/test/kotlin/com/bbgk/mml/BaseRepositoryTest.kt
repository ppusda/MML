package com.bbgk.mml

import com.bbgk.mml.domain.entity.Member
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable

@DataJpaTest
abstract class BaseRepositoryTest {

    // Id config
    protected val PLAYLIST_ID = 1L
    protected val MUSIC_ID = 1L
    protected val PLAYLIST_MUSIC_ID = 1L

    // Page Config
    private val PAGE_NUMBER = 0
    private val PAGE_SIZE = 5
    protected val DATA_SIZE = 5
    protected val pageable: Pageable = PageRequest.of(PAGE_NUMBER, PAGE_SIZE)

    // Message Config
    protected val MESSAGE_MUSIC_NOT_EXIST = "존재하지 않는 음악입니다."
    protected val MESSAGE_PLAYLIST_NOT_EXIST = "존재하지 않는 플레이리스트입니다."

    // Member Config
    protected val member = Member("testMember", "1234")
}