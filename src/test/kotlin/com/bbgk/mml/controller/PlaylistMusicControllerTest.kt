package com.bbgk.mml.controller

import com.bbgk.mml.musicList.dto.PlaylistForm
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.transaction.annotation.Transactional


class PlaylistMusicControllerTest : BaseControllerTest() {

    /**
     * SpringBootTest를 사용해도 좋습니다.
     * WebMvcTest를 활용해 인터페이스만 검증하는 방법도 있습니다.
     */

    val musicId = 2L

    @Test
    @Transactional
    @DisplayName("Playlist 내 Music 담기 성공")
    fun testPostPlaylistMusic_Success() {
        // given
        val uri = "/v1/playlists/1/musics"
        val playlistForm = PlaylistForm("playlist", member)

        // when
        val mvcResult = performPostWithId(uri, playlistForm, "musicId", musicId)
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
        val mvcResult = performPostWithId(uri, playlistForm, "musicId", musicId)
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
        val mvcResult = performPostWithId(uri, playlistForm, "musicId", musicId)
        val response = mvcResult.response

        // then
        Assertions.assertThat(response.status).isEqualTo(400)
    }

    @Test
    @Transactional
    @DisplayName("Playlist 내 Music 삭제 요청 시 삭제 성공")
    fun testDeletePlaylist_Success() {
        // given
        val uri = "/v1/playlists/1/musics"

        // when
        val mvcResult = performDeleteWithId(uri, "musicId", musicId)
        val response = mvcResult.response

        // then
        Assertions.assertThat(response.status).isEqualTo(200) // Mml
    }

    @Test
    @DisplayName("Playlist 내 Music 삭제 요청 시 경로 오류")
    fun testDeletePlaylist_NotFound() {
        // given
        val uri = "/v1/playlists/1/musics-error"

        // when
        val mvcResult = performDeleteWithId(uri, "musicId", musicId)
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
        val mvcResult = performDeleteWithId(uri, "musicId", musicId)
        val response = mvcResult.response

        // then
        Assertions.assertThat(response.status).isEqualTo(400)
    }


}