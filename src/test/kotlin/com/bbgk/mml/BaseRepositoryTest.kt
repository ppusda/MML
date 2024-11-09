package com.bbgk.mml

import com.bbgk.mml.domain.entity.Member
import com.bbgk.mml.domain.entity.Music
import com.bbgk.mml.domain.entity.Playlist
import com.bbgk.mml.domain.entity.PlaylistMusic
import com.bbgk.mml.domain.repository.MemberRepository
import com.bbgk.mml.domain.repository.MusicRepository
import com.bbgk.mml.domain.repository.PlaylistMusicRepository
import com.bbgk.mml.domain.repository.PlaylistRepository
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
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

    // Member Config
    protected val member = Member("testMember", "1234")

}