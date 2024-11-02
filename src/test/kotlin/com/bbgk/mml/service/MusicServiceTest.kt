package com.bbgk.mml.service

import com.bbgk.mml.domain.entity.Music
import com.bbgk.mml.musicList.repository.MusicListRepository
import com.bbgk.mml.musicList.service.MusicService
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
class MusicServiceTest {

    @InjectMocks
    lateinit var musicService: MusicService

    @Mock
    lateinit var musicListRepository: MusicListRepository

    val DATA_SIZE = 5

    val PAGE_NUMBER = 0
    val PAGE_SIZE = 5
    val pageable: Pageable = PageRequest.of(PAGE_NUMBER, PAGE_SIZE)

    @Test
    fun testGetMusics() {
        // given
        val musics = mutableListOf<Music>()
        for (i in 1..DATA_SIZE) {
            val music = Music("$i", "ARTIST_${i}", "URL_${i}")
            musics.add(music)
        }

        val page = PageImpl(musics, pageable, DATA_SIZE.toLong())
        Mockito.`when`(musicListRepository.getMusicsForPage(pageable))
                .thenReturn(page)

        // when
        val musicsDTOs = musicService.getMusics(0)

        // then
        assertThat(musicsDTOs).hasSize(DATA_SIZE)
    }
}