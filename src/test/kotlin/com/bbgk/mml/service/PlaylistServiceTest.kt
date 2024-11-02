package com.bbgk.mml.service

import com.bbgk.mml.domain.entity.Member
import com.bbgk.mml.domain.entity.Playlist
import com.bbgk.mml.musicList.repository.MusicListRepository
import com.bbgk.mml.musicList.service.PlaylistService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable


@ExtendWith(MockitoExtension::class)
class PlaylistServiceTest {

    /**
     * 단위 테스트를 어떻게 짜야할지 모를 때는 Jacoco로 커버리지를 측정하면 좋습니다.
     * 코드의 어떤 라인이 실행되지 않았는지 보는 것만으로도 케이스를 추가할 수 있습니다.
     * 하지만 커버리지만으로 커버되지 않는 케이스가 반드시 있으니 주의해야 합니다.
     *
     * 현재 테스트가 작성되지 않은 메소드가 많습니다.
     * 분기나 예외 던지는 케이스까지 고려해서 커버리지 100%를 달성해보면 배우는 게 있을 것입니다.
     * 토스 슬래시: https://www.youtube.com/watch?v=jdlBu2vFv58
     */

    @InjectMocks
    lateinit var playlistService: PlaylistService

    @Mock
    lateinit var musicListRepository: MusicListRepository // MusicListRepository의 Mock 객체

    val DATA_SIZE = 5
    val TEST_MEMBER = Member("testMember", "1234")

    val PAGE_NUMBER = 0
    val PAGE_SIZE = 5
    val pageable: Pageable = PageRequest.of(PAGE_NUMBER, PAGE_SIZE)

    @Test
    fun testGetPlaylists() {
        // given
        val playlists = mutableListOf<Playlist>()
        for (i in 1..DATA_SIZE) {
            val playlist = Playlist("$i", TEST_MEMBER)
            playlists.add(playlist)
        }

        val page = PageImpl(playlists, pageable, DATA_SIZE.toLong())
        Mockito.`when`(musicListRepository.getPlaylistsForPage(pageable))
                .thenReturn(page)

        // when
        val musicsDTOs = playlistService.getPlaylists(0)

        // then
        assertThat(musicsDTOs).hasSize(DATA_SIZE)
    }

    @Test
    fun testGetPlaylist() {
        // given
        val playlist = Playlist("1", TEST_MEMBER)

        Mockito.`when`(musicListRepository.findPlayListById(1))
            .thenReturn(playlist)

        // when
        val musicsDTO = playlistService.findPlaylistById(1)

        // then
        assertThat(musicsDTO.name).isEqualTo("1")
    }
}