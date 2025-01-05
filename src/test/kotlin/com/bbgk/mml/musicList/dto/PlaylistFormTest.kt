package com.bbgk.mml.musicList.dto

import com.bbgk.mml.domain.entity.Member
import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.test.Test

class PlaylistFormTest {

    @Test
    fun `PlaylistForm 테스트`() {
        // given
        val member = Member(email = "email", password = "password")
        val playlistForm = PlaylistForm(name = "name")

        // when, then
        assertEquals("name", playlistForm.name)
    }
}