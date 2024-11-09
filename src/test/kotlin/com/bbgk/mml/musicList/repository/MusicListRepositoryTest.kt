package com.bbgk.mml.musicList.repository

import com.bbgk.mml.BaseServiceTest
import com.bbgk.mml.domain.entity.Music
import com.bbgk.mml.domain.repository.MusicRepository
import com.bbgk.mml.domain.repository.PlaylistMusicRepository
import com.bbgk.mml.domain.repository.PlaylistRepository
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.kotlin.verify
import org.springframework.data.domain.PageImpl

class MusicListRepositoryTest: BaseServiceTest() {

    @InjectMocks
    lateinit var musicListRepository: MusicListRepository

    @Mock
    lateinit var musicRepository: MusicRepository

    @Mock
    lateinit var playlistRepository: PlaylistRepository

    @Mock
    lateinit var playlistMusicRepository: PlaylistMusicRepository

    @Test
    @DisplayName("음악 목록을 페이지로 조회한다.")
    fun testFindAllMusics() {
        // given
        val musics: List<Music> = mutableListOf(
                Music("title1", "artist1", "url1"),
                Music("title2", "artist2", "url2"),
                Music("title3", "artist3", "url3")
        )

        val size = musics.size
        val page = PageImpl(musics, pageable, size.toLong())

        `when`(musicRepository.findAll(pageable))
                .thenReturn(page)

        // when
        val musicPage = musicListRepository.getMusicsForPage(pageable)

        // then
        verify(musicRepository).findAll(pageable)
        Assertions.assertThat(musicPage).hasSize(size)
    }
}

/**
 * // 진짜 DB에 접근하는 Repository를 테스트하는게 아니라면 사실상 퍼사드 패턴을 적용한 musicListRepository도 비즈니스 로직을 처리하기 위한 서비스에 가깝다는 것을 알게되었다.
 * // Repository(Interface)는 JaCoCo의 100% 대상이 아닐 뿐더러 Spring Data JPA가 제공하는 내장된 메서드들은 이미 테스트가 검증 된 메서드이기 때문에 테스트가 불필요하다.
 *
 * // 커스텀한 메서드를 추가한 경우에는 테스트가 필요하기에 PlaylistMusicRepository의 findByPlaylistIdAndMusicId는 DataJpaTest로 검증하였음.
 *
 * // 아래는 테스트 코드를 작성한 예제
 * // https://github.com/MangKyu/ATDD-Membership/blob/master/src/test/java/com/mang/atdd/membership/app/membership/controller/MembershipControllerTest.java
 */
