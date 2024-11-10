package com.bbgk.mml.domain.dto

import com.bbgk.mml.domain.entity.Music
import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.test.Test

class MusicDTOTest {

    @Test
    fun `MusicDTO 테스트`() {
        // given
        val music = Music("title", "artist", "url")
        val musicDTO = MusicDTO(music)

        // when, then
        assertEquals(music.title, musicDTO.title)
        assertEquals(music.artist, musicDTO.artist)
        assertEquals(music.url, musicDTO.url)
    }
}