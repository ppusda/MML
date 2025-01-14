package com.bbgk.mml.musicList.controller

import com.bbgk.mml.BaseControllerTest
import com.bbgk.mml.domain.entity.Playlist
import com.bbgk.mml.domain.exception.MmlBadRequestException
import com.bbgk.mml.member.service.MemberService
import com.bbgk.mml.domain.dto.PlaylistDTO
import com.bbgk.mml.musicList.dto.PlaylistForm
import com.bbgk.mml.musicList.service.PlaylistService
import org.assertj.core.api.Assertions
import org.hamcrest.Matchers.hasSize
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.mockito.kotlin.any
import org.mockito.kotlin.verify
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@WebMvcTest(PlaylistController::class)
class PlaylistControllerTest(
        @Autowired private val mockMvc: MockMvc
) : BaseControllerTest(mockMvc) {

    @MockBean
    private lateinit var playlistService: PlaylistService

    @MockBean
    private lateinit var memberService: MemberService

    @Test
    @DisplayName("Playlists 조회")
    fun testGetPlaylists() {
        // given
        val uri = "/v1/playlists?page=0"

        val playlistList = listOf(
                Playlist("name1", member),
                Playlist("name2", member),
                Playlist("name3", member),
        )

        val playlistDTOs = playlistList.map { PlaylistDTO(it) }
        val playlists: Page<PlaylistDTO> = PageImpl(playlistDTOs, pageable, DATA_SIZE.toLong())

        `when`(playlistService.getPlaylists(any()))
                .thenReturn(playlists)

        // when, then
        mockMvc.perform(MockMvcRequestBuilders.get(uri))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.content", hasSize<Any>(3)))
            .andReturn()

        verify(playlistService).getPlaylists(any())
    }

    @Test
    @DisplayName("Playlist Post 요청 시 생성 성공")
    fun testPostPlaylist_Success() {
        // given
        val uri = "/v1/playlists"
        val playlistForm = PlaylistForm("playlist")

        // when, then
        performPostWithId(uri, playlistForm, "uid", USER_ID, MockMvcResultMatchers.status().isOk)
    }

    @Test
    @DisplayName("Playlist Post 요청 시 경로 오류")
    fun testPostPlaylist_NotFound() {
        // given
        val uri = "/v1/playlists-error"
        val playlistForm = PlaylistForm("playlist")

        // when, then
        performPostWithId(uri, playlistForm, "uid", USER_ID, MockMvcResultMatchers.status().isNotFound)
    }

    @Test
    @DisplayName("Playlist Post 요청 시 필수 값 미입력")
    fun testPostPlaylist_ServerError() {
        // given
        val uri = "/v1/playlists"
        val playlistForm = PlaylistForm("")

        // when
        val mvcResult = performPostWithId(uri, playlistForm, "uid", USER_ID, MockMvcResultMatchers.status().isBadRequest)
        val response = mvcResult.response

        // then
        Assertions.assertThat(response.contentAsString).contains(MESSAGE_REQUIRED)
    }

    @Test
    @DisplayName("Playlist Patch 요청 시 수정 성공")
    fun testPatchPlaylist_Success() {
        // given
        val uri = "/v1/playlists/1"
        val playlistForm = PlaylistForm("edited playlist")

        // when
        performPatch(uri, playlistForm, MockMvcResultMatchers.status().isOk)

        // then
        verify(playlistService).updatePlaylist(any(), any())
    }

    @Test
    @DisplayName("Playlist Patch 요청 시 경로 오류")
    fun testPatchPlaylist_NotFound() {
        // given
        val uri = "/v1/playlists-error/1"

        val playlistForm = PlaylistForm("edited playlist")

        // when
        performPatch(uri, playlistForm, MockMvcResultMatchers.status().isNotFound)

    }

    @Test
    @DisplayName("Playlist Patch 요청 시 클라이언트 오류")
    fun testPatchPlaylist_ServerError() {
        // given
        val uri = "/v1/playlists/5" // 초기 데이터에 5번 없음
        val playlistForm = PlaylistForm("edited playlist")

        `when`(playlistService.updatePlaylist(any(), any()))
                .thenThrow(MmlBadRequestException(MESSAGE_NOT_EXIST_PLAYLIST))

        // when, then
        performPatch(uri, playlistForm, MockMvcResultMatchers.status().isBadRequest)
    }

    @Test
    @DisplayName("Playlist Delete 요청 시 삭제 성공")
    fun testDeletePlaylist_Success() {
        // given
        val uri = "/v1/playlists/1"

        // when
        performDelete(uri, MockMvcResultMatchers.status().isOk)

        // then
        verify(playlistService).deletePlaylist(any())
    }

    @Test
    @DisplayName("Playlist Delete 요청 시 경로 오류")
    fun testDeletePlaylist_NotFound() {
        // given
        val uri = "/v1/playlists-error/1"

        // when, then
        performDelete(uri, MockMvcResultMatchers.status().isNotFound)
    }

    @Test
    @DisplayName("Playlist Delete 요청 시 클라이언트 오류")
    fun testDeletePlaylist_ServerError() {
        // given
        val uri = "/v1/playlists/1"

        `when`(playlistService.deletePlaylist(any()))
                .thenThrow(MmlBadRequestException(MESSAGE_NOT_EXIST_PLAYLIST))

        // when, then
        performDelete(uri, MockMvcResultMatchers.status().isBadRequest)
    }

}