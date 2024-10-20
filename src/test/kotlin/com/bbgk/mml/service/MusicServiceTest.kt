package com.bbgk.mml.service

import com.bbgk.mml.domain.entity.Music
import com.bbgk.mml.domain.repository.MusicRepository
import com.bbgk.mml.musicList.service.MusicService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension


@ExtendWith(MockitoExtension::class)
class MusicServiceTest {

    @InjectMocks
    lateinit var musicService: MusicService

    @Mock
    lateinit var musicRepository: MusicRepository

    val DATA_SIZE = 5

    @Test
    fun testGetMusics() {
        // given
        val musics = mutableListOf<Music>()
        for (i in 1..DATA_SIZE) {
            val music = Music("$i", "ARTIST_${i}", "URL_${i}")
            musics.add(music)
        }

        Mockito.`when`(musicRepository.findAll())
                .thenReturn(musics)

        // when
        val musicsDTOs = musicService.getMusics()

        // then
        assertThat(musicsDTOs).hasSize(DATA_SIZE)
    }
}