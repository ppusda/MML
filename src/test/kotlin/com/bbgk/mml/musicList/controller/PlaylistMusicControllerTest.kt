package com.bbgk.mml.musicList.controller

import com.bbgk.mml.BaseControllerTest
import com.bbgk.mml.musicList.dto.PlaylistForm
import com.bbgk.mml.musicList.service.PlaylistService
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc

@WebMvcTest(PlaylistMusicController::class)
class PlaylistMusicControllerTest(
        @Autowired private val mockMvc: MockMvc
) : BaseControllerTest(mockMvc) {

    @MockBean
    private lateinit var playlistService: PlaylistService

    @Test
    @DisplayName("Playlist 내 Music 담기 성공")
    fun testPostPlaylistMusic_Success() {
        // given
        val uri = "/v1/playlists/1/musics"
        val playlistForm = PlaylistForm("playlist", member)

        // when
        val mvcResult = performPostWithId(uri, playlistForm, "musicId", MUSIC_ID)
        val response = mvcResult.response

        // then
        Assertions.assertThat(response.status).isEqualTo(200)
    }

    @Test
    @DisplayName("Playlist 내 Music 담기 요청 시 경로 오류")
    fun testPostPlaylist_NotFound() {
        // given
        val uri = "/v1/playlists/1/musics-error"
        val playlistForm = PlaylistForm("playlist", member)

        // when
        val mvcResult = performPostWithId(uri, playlistForm, "musicId", MUSIC_ID)
        val response = mvcResult.response

        // then
        Assertions.assertThat(response.status).isEqualTo(404)
    }

    @Test
    @DisplayName("Playlist 내 Music 담기 요청 시 클라이언트 오류")
    fun testPostPlaylist_ServerError() {
        // given
        val uri = "/v1/playlists/5/musics" // 존재하지 않는 플레이리스트
        val playlistForm = PlaylistForm("playlist", member)

        // when
        val mvcResult = performPostWithId(uri, playlistForm, "musicId", MUSIC_ID)
        val response = mvcResult.response

        // then
        Assertions.assertThat(response.status).isEqualTo(400)
    }

    @Test
    @DisplayName("Playlist 내 Music 삭제 요청 시 삭제 성공")
    fun testDeletePlaylist_Success() {
        // given
        val uri = "/v1/playlists/1/musics"

        // when
        val mvcResult = performDeleteWithId(uri, "musicId", MUSIC_ID)
        val response = mvcResult.response

        // then
        Assertions.assertThat(response.status).isEqualTo(200)
    }

    @Test
    @DisplayName("Playlist 내 Music 삭제 요청 시 경로 오류")
    fun testDeletePlaylist_NotFound() {
        // given
        val uri = "/v1/playlists/1/musics-error"

        // when
        val mvcResult = performDeleteWithId(uri, "musicId", MUSIC_ID)
        val response = mvcResult.response

        // then
        Assertions.assertThat(response.status).isEqualTo(404)
    }

    @Test
    @DisplayName("Playlist 내 Music 삭제 요청 시 클라이언트 오류")
    fun testDeletePlaylist_ServerError() {
        // given
        val uri = "/v1/playlists/5/musics"

        // when
        val mvcResult = performDeleteWithId(uri, "musicId", MUSIC_ID)
        val response = mvcResult.response

        // then
        Assertions.assertThat(response.status).isEqualTo(400)
    }

}