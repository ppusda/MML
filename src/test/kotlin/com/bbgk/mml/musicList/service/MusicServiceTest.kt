package com.bbgk.mml.musicList.service

import com.bbgk.mml.BaseServiceTest
import com.bbgk.mml.domain.entity.Music
import com.bbgk.mml.domain.exception.MmlBadRequestException
import com.bbgk.mml.musicList.dto.MusicForm
import com.bbgk.mml.musicList.repository.MusicListRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.kotlin.*
import org.springframework.data.domain.PageImpl


class MusicServiceTest: BaseServiceTest() {

    @InjectMocks
    lateinit var musicService: MusicService

    @Mock
    lateinit var musicListRepository: MusicListRepository

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
        val musicsDTOs = musicService.getMusics(0)

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

        `when`(musicListRepository.findMusicById(MUSIC_ID))
                .thenReturn(existingMusic)

        // when
        musicService.updateMusic(MUSIC_ID, musicForm)

        // then
        assertThat(existingMusic.title).isEqualTo(musicForm.title)
        assertThat(existingMusic.artist).isEqualTo(musicForm.artist)
        assertThat(existingMusic.url).isEqualTo(musicForm.url)
    }

    @Test
    @DisplayName("존재하지 않는 음악을 수정하려고 할 때 예외가 발생합니다.")
    fun testUpdateNotExistMusic() {
        // given
        val musicForm = MusicForm("title", "artist", "url")

        `when`(musicListRepository.findMusicById(any()))
                .thenThrow(MmlBadRequestException("존재하지 않는 음악입니다."))

        // when
        val exception = assertThrows<MmlBadRequestException> {
            musicService.updateMusic(MUSIC_ID, musicForm)
        }

        // then
        assertEquals(MESSAGE_NOT_EXIST_MUSIC, exception.message)
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
        verify(musicListRepository).deleteMusicById(MUSIC_ID)
    }

    @Test
    @DisplayName("존재하지 않는 음악을 삭제하려고 할 때 예외가 발생합니다.")
    fun testDeleteNotExistMusic() {
        // given
        `when`(musicListRepository.findMusicById(any()))
                .thenThrow(MmlBadRequestException(MESSAGE_NOT_EXIST_MUSIC))

        // when
        val exception = assertThrows<MmlBadRequestException> {
            musicService.deleteMusic(MUSIC_ID)
        }

        // then
        assertEquals(MESSAGE_NOT_EXIST_MUSIC, exception.message)
        verify(musicListRepository).findMusicById(MUSIC_ID)
        verify(musicListRepository, never()).deleteMusicById(any())
    }

}