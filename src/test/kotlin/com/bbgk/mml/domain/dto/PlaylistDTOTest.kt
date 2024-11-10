package com.bbgk.mml.domain.dto

import com.bbgk.mml.domain.entity.Member
import com.bbgk.mml.domain.entity.Music
import com.bbgk.mml.domain.entity.Playlist
import com.bbgk.mml.domain.entity.PlaylistMusic
import org.junit.jupiter.api.Assertions.*
import kotlin.test.Test

class PlaylistDTOTest {

    @Test
    fun `PlaylistDTO 테스트`() {
        // given
        val music = Music("title1", "artist1", "url1")
        val musicDTO = MusicDTO(music)

        val member = Member(email = "email", password = "password")
        val playlist = Playlist("name", member)

        val playlistMusic = PlaylistMusic(playlist, music = music)
        playlist.addMusics(mutableListOf(playlistMusic))

        val playlistDTO = PlaylistDTO(playlist)

        // when, then
        assertEquals(playlist.name, playlistDTO.name)
        assertEquals(playlist.member.email, playlistDTO.ownerEmail)
        assertEquals(mutableListOf(musicDTO), playlistDTO.musics)
    }
}