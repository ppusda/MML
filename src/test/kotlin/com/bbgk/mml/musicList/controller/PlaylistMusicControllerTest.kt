package com.bbgk.mml.musicList.controller

import com.bbgk.mml.BaseControllerTest
import com.bbgk.mml.domain.entity.Playlist
import com.bbgk.mml.domain.exception.MmlBadRequestException
import com.bbgk.mml.member.service.MemberService
import com.bbgk.mml.domain.dto.PlaylistDTO
import com.bbgk.mml.musicList.dto.PlaylistForm
import com.bbgk.mml.musicList.service.PlaylistMusicService
import com.bbgk.mml.musicList.service.PlaylistService
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.mockito.kotlin.any
import org.mockito.kotlin.verify
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@WebMvcTest(PlaylistMusicController::class)
class PlaylistMusicControllerTest(
        @Autowired private val mockMvc: MockMvc
) : BaseControllerTest(mockMvc) {

    @MockBean
    private lateinit var playlistMusicService: PlaylistMusicService

    @MockBean
    private lateinit var playlistService: PlaylistService

    @MockBean
    private lateinit var memberService: MemberService


    @Test
    @DisplayName("N번 Playlist 조회")
    fun testGetNPlaylist() {
        // given
        val uri = "/v1/playlists/${N}/musics"

        val playlist = Playlist("name", member)
        val playlistDTO = PlaylistDTO(playlist)

        `when`(playlistService.findPlaylistById(any()))
                .thenReturn(playlistDTO)

        // when
        performGet(uri, MockMvcResultMatchers.status().isOk)

        // then
        verify(playlistService).findPlaylistById(any())
    }

    @Test
    @DisplayName("Playlist 내 Music 담기 성공")
    fun testPostPlaylistMusic_Success() {
        // given
        val uri = "/v1/playlists/1/musics"
        val playlistForm = PlaylistForm("playlist", member)

        // when
        performPostWithId(uri, playlistForm, "musicId", MUSIC_ID, MockMvcResultMatchers.status().isOk)

        // then
        verify(playlistMusicService).addMusicInPlaylist(PLAYLIST_ID, MUSIC_ID)
    }

    @Test
    @DisplayName("Playlist 내 Music 담기 요청 시 경로 오류")
    fun testPostPlaylist_NotFound() {
        // given
        val uri = "/v1/playlists/1/musics-error"
        val playlistForm = PlaylistForm("playlist", member)

        // when, then
        performPostWithId(uri, playlistForm, "musicId", MUSIC_ID, MockMvcResultMatchers.status().isNotFound)
    }

    @Test
    @DisplayName("Playlist 내 Music 담기 요청 시 클라이언트 오류")
    fun testPostPlaylist_ServerError() {
        // given
        val uri = "/v1/playlists/1/musics"
        val playlistForm = PlaylistForm("playlist", member)

        `when`(playlistMusicService.addMusicInPlaylist(any(), any()))
                .thenThrow(MmlBadRequestException(MESSAGE_ALREADY_EXIST_PLAYLIST_MUSIC))

        // when, then
        performPostWithId(uri, playlistForm, "musicId", MUSIC_ID, MockMvcResultMatchers.status().isBadRequest)
    }

    @Test
    @DisplayName("Playlist 내 Music 삭제 요청 시 삭제 성공")
    fun testDeletePlaylist_Success() {
        // given
        val uri = "/v1/playlists/1/musics"

        // when
        performDeleteWithId(uri, "musicId", MUSIC_ID, MockMvcResultMatchers.status().isOk)

        // then
        verify(playlistMusicService).deleteMusicInPlaylist(any(), any())
    }

    @Test
    @DisplayName("Playlist 내 Music 삭제 요청 시 경로 오류")
    fun testDeletePlaylist_NotFound() {
        // given
        val uri = "/v1/playlists/1/musics-error"

        // when, then
        performDeleteWithId(uri, "musicId", MUSIC_ID, MockMvcResultMatchers.status().isNotFound)
    }

    @Test
    @DisplayName("Playlist 내 Music 삭제 요청 시 클라이언트 오류")
    fun testDeletePlaylist_ServerError() {
        // given
        val uri = "/v1/playlists/1/musics"

        `when`(playlistMusicService.deleteMusicInPlaylist(any(), any()))
                .thenThrow(MmlBadRequestException(MESSAGE_NOT_EXIST_PLAYLIST_MUSIC))

        // when, then
        performDeleteWithId(uri, "musicId", MUSIC_ID, MockMvcResultMatchers.status().isBadRequest)

    }

}