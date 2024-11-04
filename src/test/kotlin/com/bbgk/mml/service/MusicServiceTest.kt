package com.bbgk.mml.service

import com.bbgk.mml.domain.entity.Music
import com.bbgk.mml.domain.exception.MmlBadRequestException
import com.bbgk.mml.musicList.dto.MusicForm
import com.bbgk.mml.musicList.repository.MusicListRepository
import com.bbgk.mml.musicList.service.MusicService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.*
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable


@ExtendWith(MockitoExtension::class)
class MusicServiceTest {

    @InjectMocks
    lateinit var musicService: MusicService

    @Mock
    lateinit var musicListRepository: MusicListRepository

    val MUSIC_ID = 1L
    val DATA_SIZE = 5

    val PAGE_NUMBER = 0
    val PAGE_SIZE = 5
    val pageable: Pageable = PageRequest.of(PAGE_NUMBER, PAGE_SIZE)

    @Test
    @DisplayName("음악 목록을 조회합니다.")
    fun testGetMusics() {
        // given
        val musics = mutableListOf<Music>()
        for (i in 1..DATA_SIZE) {
            val music = Music("$i", "ARTIST_${i}", "URL_${i}")
            musics.add(music)
        }

        val page = PageImpl(musics, pageable, DATA_SIZE.toLong())
        `when`(musicListRepository.getMusicsForPage(pageable))
                .thenReturn(page)

        // when
        val musicsDTOs = musicService.getMusics(PAGE_NUMBER)

        // then
        verify(musicListRepository).getMusicsForPage(pageable)
        assertThat(musicsDTOs).hasSize(DATA_SIZE)
    }

    @Test
    @DisplayName("음악 정보를 저장합니다.")
    fun testSaveMusic() {
        // given
        val musicForm = MusicForm("title", "artist", "url")
        val music = musicForm.toEntity()
        val argumentCaptor = argumentCaptor<Music>()

        doNothing().`when`(musicListRepository).saveMusic(any())

        // when
        musicService.saveMusic(musicForm)

        // then
        verify(musicListRepository).saveMusic(argumentCaptor.capture())

        // 캡처한 객체 검증
        val savedMusic = argumentCaptor.allValues[0]
        assertThat(savedMusic.title).isEqualTo(music.title)
        assertThat(savedMusic.artist).isEqualTo(music.artist)
        assertThat(savedMusic.url).isEqualTo(music.url)
    }

    @Test
    @DisplayName("음악 정보를 수정합니다.")
    fun testUpdateMusic() {
        // given
        val musicForm = MusicForm("updatedTitle", "updatedArtist", "updatedUrl")
        val existingMusic = Music("title", "artist", "url")
        val argumentCaptor = argumentCaptor<Music>()

        `when`(musicListRepository.findMusicById(MUSIC_ID))
                .thenReturn(existingMusic)

        // when
        musicService.updateMusic(MUSIC_ID, musicForm)

        // then
        verify(musicListRepository).saveMusic(argumentCaptor.capture()) // saveMusic 메서드가 호출되었는지 확인

        // 캡처한 객체 검증
        val updatedMusic = argumentCaptor.allValues[0]
        assertThat(updatedMusic.id).isEqualTo(MUSIC_ID)
        assertThat(updatedMusic.title).isEqualTo(musicForm.title)
        assertThat(updatedMusic.artist).isEqualTo(musicForm.artist)
        assertThat(updatedMusic.url).isEqualTo(musicForm.url)
    }

    @Test
    @DisplayName("존재하지 않는 음악을 수정하려고 할 때 예외가 발생합니다.")
    fun testUpdateNotExistMusic() {
        // given
        val musicForm = MusicForm("title", "artist", "url")

        `when`(musicListRepository.findMusicById(any()))
                .thenThrow(MmlBadRequestException("존재하지 않는 음악입니다."))

        // when
        assertThrows<MmlBadRequestException> {
            musicService.updateMusic(MUSIC_ID, musicForm)
        }

        // then
        verify(musicListRepository).findMusicById(MUSIC_ID)
        verify(musicListRepository, never()).saveMusic(any())
    }


    @Test
    @DisplayName("음악을 삭제합니다.")
    fun testDeleteMusic() {
        // given
        val music = Music("title", "artist", "url")
        `when`(musicListRepository.findMusicById(MUSIC_ID))
                .thenReturn(music)
        doNothing().`when`(musicListRepository).deleteMusicById(MUSIC_ID)

        // when
        musicService.deleteMusic(MUSIC_ID)

        // then
        Mockito.verify(musicListRepository).deleteMusicById(MUSIC_ID)
    }

    @Test
    @DisplayName("존재하지 않는 음악을 삭제하려고 할 때 예외가 발생합니다.")
    fun testDeleteNotExistMusic() {
        // given
        `when`(musicListRepository.findMusicById(any()))
                .thenThrow(MmlBadRequestException("존재하지 않는 음악입니다."))

        // when
        assertThrows<MmlBadRequestException> {
            musicService.deleteMusic(MUSIC_ID)
        }

        // then
        verify(musicListRepository).findMusicById(MUSIC_ID)
        verify(musicListRepository, never()).deleteMusicById(any())
    }

}