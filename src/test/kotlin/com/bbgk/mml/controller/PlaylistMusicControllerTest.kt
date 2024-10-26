package com.bbgk.mml.controller

import com.bbgk.mml.musicList.dto.PlaylistForm
import org.assertj.core.api.Assertions
import org.json.JSONArray
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.transaction.annotation.Transactional
import java.nio.charset.StandardCharsets


class PlaylistMusicControllerTest : BaseControllerTest() {

    /**
     * SpringBootTest를 사용해도 좋습니다.
     * WebMvcTest를 활용해 인터페이스만 검증하는 방법도 있습니다.
     */

    val mid = 1L

    @Test
    @Transactional
    @DisplayName("Playlist 내 Music 담기 성공")
    fun testPostPlaylistMusic_Success() {
        // given
        val uri = "/playlist/1/music"
        val playlistForm = PlaylistForm("playlist", member)

        // when
        val mvcResult = performPostWithId(uri, playlistForm, "mid", mid)
        val response = mvcResult.response

        // then
        Assertions.assertThat(response.status).isEqualTo(200)
    }

    @Test
    @DisplayName("Playlist 내 Music 담기 요청 시 경로 오류")
    fun testPostPlaylist_NotFound() {
        // given
        val uri = "/playlists/1/music"
        val playlistForm = PlaylistForm("playlist", member)

        // when
        val mvcResult = performPostWithId(uri, playlistForm, "mid", mid)
        val response = mvcResult.response

        // then
        Assertions.assertThat(response.status).isEqualTo(404)
    }

    @Test
    @DisplayName("Playlist 내 Music 담기 요청 시 서버 오류")
    fun testPostPlaylist_ServerError() {
        // given
        val uri = "/playlist/5/music"
        val playlistForm = PlaylistForm("playlist", member)

        // when
        val mvcResult = performPostWithId(uri, playlistForm, "mid", mid)
        val response = mvcResult.response

        // then
        Assertions.assertThat(response.status).isEqualTo(500)
    }

    @Test
    @Transactional
    @DisplayName("Playlist 내 Music 삭제 요청 시 삭제 성공")
    fun testDeletePlaylist_Success() {
        // given
        val uri = "/playlist/1/music"

        // when
        val mvcResult = performDeleteWithId(uri, "mid", mid)
        val response = mvcResult.response

        // then
        Assertions.assertThat(response.status).isEqualTo(200)
    }

    @Test
    @DisplayName("Playlist 내 Music 삭제 요청 시 경로 오류")
    fun testDeletePlaylist_NotFound() {
        // given
        val uri = "/playlists/1/music"

        // when
        val mvcResult = performDeleteWithId(uri, "mid", mid)
        val response = mvcResult.response

        // then
        Assertions.assertThat(response.status).isEqualTo(404)
    }

    @Test
    @DisplayName("Playlist 내 Music 삭제 요청 시 서버 오류")
    fun testDeletePlaylist_ServerError() {
        // given
        val uri = "/playlist/5/music"

        // when
        val mvcResult = performDeleteWithId(uri, "mid", mid)
        val response = mvcResult.response

        // then
        Assertions.assertThat(response.status).isEqualTo(500)
    }


}