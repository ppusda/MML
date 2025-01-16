package com.bbgk.mml.musicList.dto

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class PlaylistCreateResponseTest {

    @Test
    fun `Id를 정상적으로 가져온다`() {
        // given
        val playlistCreateResponse = PlaylistCreateResponse(1L)

        // when, then
        assertEquals(1L, playlistCreateResponse.id)
    }

}