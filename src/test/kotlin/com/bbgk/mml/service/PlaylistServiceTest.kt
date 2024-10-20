package com.bbgk.mml.service

import com.bbgk.mml.domain.entity.Member
import com.bbgk.mml.domain.entity.Playlist
import com.bbgk.mml.domain.repository.MusicRepository
import com.bbgk.mml.domain.repository.PlaylistRepository
import com.bbgk.mml.musicList.service.PlaylistService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*


@ExtendWith(MockitoExtension::class)
class PlaylistServiceTest {

    @InjectMocks
    lateinit var playlistService: PlaylistService

    @Mock
    lateinit var playlistRepository: PlaylistRepository

    val DATA_SIZE = 5
    val TEST_MEMBER = Member("testMember", "1234")

    @Test
    fun testGetPlaylists() {
        // given
        val playlists = mutableListOf<Playlist>()
        for (i in 1..DATA_SIZE) {
            val playlist = Playlist("$i", TEST_MEMBER)
            playlists.add(playlist)
        }

        Mockito.`when`(playlistRepository.findAll())
                .thenReturn(playlists)

        // when
        val musicsDTOs = playlistService.getPlaylists()

        // then
        assertThat(musicsDTOs).hasSize(DATA_SIZE)
    }

    @Test
    fun testGetPlaylist() {
        // given
        val playlist = Playlist("1", TEST_MEMBER)

        Mockito.`when`(playlistRepository.findById(1))
                .thenReturn(Optional.of(playlist))

        // when
        val musicsDTO = playlistService.getPlaylist(1)

        // then
        assertThat(musicsDTO.name).isEqualTo("1")
    }
}