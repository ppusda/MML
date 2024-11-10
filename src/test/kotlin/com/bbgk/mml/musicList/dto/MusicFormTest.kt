package com.bbgk.mml.musicList.dto

import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.test.Test

class MusicFormTest {

    @Test
    fun `MusicForm 테스트`() {
        // given
        val musicForm = MusicForm(
                title = "title",
                artist = "artist",
                url = "url"
        )

        // when, then
        assertEquals("title", musicForm.title)
        assertEquals("artist", musicForm.artist)
        assertEquals("url", musicForm.url)
    }
}