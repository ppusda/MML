package com.bbgk.mml.domain.entity

import com.bbgk.mml.domain.dto.MusicDTO
import com.bbgk.mml.domain.dto.PlaylistDTO
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.test.Test

class PlaylistTest {

    @Test
    fun `Playlist에 음악을 추가할 때, PlaylistMusic 정보가 null이라면 추가되지 않습니다`() {
        // given
        val member = Member(email = "email", password = "password")
        val playlist = Playlist("name", member)

        val playlistMusic = null
        playlist.addMusics(playlistMusic)

        val playlistDTO = PlaylistDTO(playlist)

        // when, then
        assertEquals(playlist.name, playlistDTO.name)
        assertEquals(playlist.member.email, playlistDTO.ownerEmail)
        assertEquals(emptyList<MusicDTO>(), playlistDTO.musics)
    }

    @Test
    fun `재생목록 정보를 수정할 때 name이 비어있을 경우 기존의 값을 유지합니다`() {
        // given
        val member = Member(email = "email", password = "password")
        val playlist = Playlist("name", member)

        // when
        playlist.update("")

        // then
        assertThat(playlist.name).isEqualTo(playlist.name)
    }
}