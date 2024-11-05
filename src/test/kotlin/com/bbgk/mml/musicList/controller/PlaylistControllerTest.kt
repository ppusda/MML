package com.bbgk.mml.musicList.controller

import com.bbgk.mml.BaseControllerTest
import com.bbgk.mml.musicList.dto.PlaylistForm
import com.bbgk.mml.musicList.service.MusicService
import org.assertj.core.api.Assertions
import org.json.JSONObject
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.nio.charset.StandardCharsets


class PlaylistControllerTest(
        @Autowired private val mockMvc: MockMvc
) : BaseControllerTest(mockMvc) {

    @MockBean
    private lateinit var playlistService: MusicService

    @Test
    @DisplayName("Playlists 조회")
    fun testGetPlaylists() {
        // given
        val uri = "/v1/playlists?page=0"

        // when
        val mvcResult = performGet(uri, MockMvcResultMatchers.status().isOk)
        val contentAsString = mvcResult.response.getContentAsString(StandardCharsets.UTF_8)
        val jsonObject = JSONObject(contentAsString)

        // then
        Assertions.assertThat(jsonObject.optJSONArray("content").length()).isPositive()
    }

    @Test
    @DisplayName("N번 Playlist 조회")
    fun testGetNPlaylist() {
        // given
        val uri = "/v1/playlists/${N}/musics"

        // when
        val mvcResult = performGet(uri, MockMvcResultMatchers.status().isOk)
        val contentAsString = mvcResult.response.getContentAsString(StandardCharsets.UTF_8)
        val jsonObject = JSONObject(contentAsString)

        // then
        Assertions.assertThat(jsonObject.optJSONArray("musics").length()).isPositive()
    }

    @Test
    @DisplayName("Playlist Post 요청 시 생성 성공")
    fun testPostPlaylist_Success() {
        // given
        val uri = "/v1/playlists"
        val playlistForm = PlaylistForm("playlist", member)

        // when
        val mvcResult = performPostWithId(uri, playlistForm, "uid", USER_ID)
        val response = mvcResult.response

        // then
        Assertions.assertThat(response.status).isEqualTo(200)
    }

    @Test
    @DisplayName("Playlist Post 요청 시 경로 오류")
    fun testPostPlaylist_NotFound() {
        // given
        val uri = "/v1/playlists-error"
        val playlistForm = PlaylistForm("playlist", member)

        // when
        val mvcResult = performPostWithId(uri, playlistForm, "uid", USER_ID)
        val response = mvcResult.response

        // then
        Assertions.assertThat(response.status).isEqualTo(404)
    }

    @Test
    @DisplayName("Playlist Post 요청 시 클라이언트 오류")
    fun testPostPlaylist_ServerError() {
        // given
        val uri = "/v1/playlists"
        val playlistForm = PlaylistForm("playlist", member)

        USER_ID = 5L

        // when
        val mvcResult = performPostWithId(uri, playlistForm, "uid", USER_ID)
        val response = mvcResult.response

        // then
        Assertions.assertThat(response.status).isEqualTo(400) // MmlBadRequestException
    }

    @Test
    @DisplayName("Playlist Patch 요청 시 수정 성공")
    fun testPatchPlaylist_Success() {
        // given
        val uri = "/v1/playlists/1"
        val playlistForm = PlaylistForm("edited playlist", member)

        // when
        val mvcResult = performPatch(uri, playlistForm)
        val response = mvcResult.response

        // then
        Assertions.assertThat(response.status).isEqualTo(200)
    }

    @Test
    @DisplayName("Playlist Patch 요청 시 경로 오류")
    fun testPatchPlaylist_NotFound() {
        // given
        val uri = "/v1/playlists-error/1"

        val playlistForm = PlaylistForm("edited playlist", member)

        // when
        val mvcResult = performPatch(uri, playlistForm)
        val response = mvcResult.response

        // then
        Assertions.assertThat(response.status).isEqualTo(404)
    }

    @Test
    @DisplayName("Playlist Patch 요청 시 클라이언트 오류")
    fun testPatchPlaylist_ServerError() {
        // given
        val uri = "/v1/playlists/5" // 초기 데이터에 5번 없음
        val playlistForm = PlaylistForm("edited playlist", member)

        // when
        val mvcResult = performPatch(uri, playlistForm)
        val response = mvcResult.response

        // then
        Assertions.assertThat(response.status).isEqualTo(400) // MmlBadRequestException
    }

    @Test
    @DisplayName("Playlist Delete 요청 시 삭제 성공")
    fun testDeletePlaylist_Success() {
        // given
        val uri = "/v1/playlists/1"

        // when
        val mvcResult = performDelete(uri)
        val response = mvcResult.response

        // then
        Assertions.assertThat(response.status).isEqualTo(200)
    }

    @Test
    @DisplayName("Playlist Delete 요청 시 경로 오류")
    fun testDeletePlaylist_NotFound() {
        // given
        val uri = "/v1/playlists-error/1"

        // when
        val mvcResult = performDelete(uri)
        val response = mvcResult.response

        // then
        Assertions.assertThat(response.status).isEqualTo(404)
    }

    @Test
    @DisplayName("Playlist Delete 요청 시 클라이언트 오류")
    fun testDeletePlaylist_ServerError() {
        // given
        val uri = "/v1/playlists/5"

        // when
        val mvcResult = performDelete(uri)
        val response = mvcResult.response

        // then
        Assertions.assertThat(response.status).isEqualTo(400) // MmlBadRequestException
    }

}