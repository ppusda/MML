package com.bbgk.mml.musicList.controller

import com.bbgk.mml.BaseControllerTest
import com.bbgk.mml.domain.entity.Music
import com.bbgk.mml.domain.exception.MmlBadRequestException
import com.bbgk.mml.musicList.dto.MusicDTO
import com.bbgk.mml.musicList.dto.MusicForm
import com.bbgk.mml.musicList.service.MusicService
import org.assertj.core.api.Assertions.*
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@WebMvcTest(MusicController::class)
class MusicControllerTest(
        @Autowired private val mockMvc: MockMvc
) : BaseControllerTest(mockMvc) {

    @MockBean
    private lateinit var musicService: MusicService

    @Test
    @DisplayName("Musics 조회 요청")
    fun testGetMusics() {
        // given
        val uri = "/v1/musics?page=0"

        val musicList = listOf(
                Music("title1", "artist1", "url1"),
                Music("title2", "artist2", "url2"),
                Music("title3", "artist3", "url3"),
        )

        val musicDTOs = musicList.map { MusicDTO(it) }
        val musics: Page<MusicDTO> = PageImpl(musicDTOs, pageable, DATA_SIZE.toLong())

        `when`(musicService.getMusics(any()))
                .thenReturn(musics)

        // when
        performGet(uri, MockMvcResultMatchers.status().isOk)

        // then
        verify(musicService).getMusics(any())
    }

    @Test
    @DisplayName("Music Post 요청 시 생성 성공")
    fun testPostMusic_Success() {
        // given
        val uri = "/v1/musics"
        val musicForm = MusicForm("music", "artist", "url")

        // when
        performPost(uri, musicForm, MockMvcResultMatchers.status().isOk)

        // then
        verify(musicService).saveMusic(any())
    }

    @Test
    @DisplayName("Music Post 요청 시 경로 오류")
    fun testPostMusic_NotFound() {
        // given
        val uri = "/v1/musics-error"
        val musicForm = MusicForm("music", "artist", "url")

        // when, then
        performPost(uri, musicForm, MockMvcResultMatchers.status().isNotFound)
    }

    @Test
    @DisplayName("Music Post 요청 시 필수 값 미입력")
    fun testPostMusic_ServerError() {
        // given
        val uri = "/v1/musics"
        val musicForm = MusicForm("", "artist", "url")

        // when
        val mvcResult = performPost(uri, musicForm, MockMvcResultMatchers.status().isBadRequest)
        val response = mvcResult.response

        // then
        assertThat(response.contentAsString).contains(MESSAGE_REQUIRED)
    }

    @Test
    @DisplayName("Music Put 요청 시 수정 성공")
    fun testPatchMusic_Success() {
        // given
        val uri = "/v1/musics/1"
        val musicForm = MusicForm("music", "artist", "url")

        // when
        performPut(uri, musicForm, MockMvcResultMatchers.status().isOk)

        // then
        verify(musicService).updateMusic(any(), any())
    }

    @Test
    @DisplayName("Music Put 요청 시 경로 오류")
    fun testPatchMusic_NotFound() {
        // given
        val uri = "/v1/musics-error/1"
        val musicForm = MusicForm("music", "artist", "url")

        // when, then
        performPut(uri, musicForm, MockMvcResultMatchers.status().isNotFound)
    }

    @Test
    @DisplayName("Music Put 요청 시 클라이언트 요청 오류")
    fun testPatchMusic_ServerError() {
        // given
        val uri = "/v1/musics/1"
        val musicForm = MusicForm("music", "artist", "url")

        `when`(musicService.updateMusic(any(), any()))
                .thenThrow(MmlBadRequestException(MESSAGE_NOT_EXIST_MUSIC))

        // when, then
        performPut(uri, musicForm, MockMvcResultMatchers.status().isBadRequest)
    }

    @Test
    @DisplayName("Music Delete 요청 시 삭제 성공")
    fun testDeleteMusic_Success() {
        // given
        val uri = "/v1/musics/1"

        // when
        performDelete(uri, MockMvcResultMatchers.status().isOk)

        // then
        verify(musicService).deleteMusic(any())
    }

    @Test
    @DisplayName("Music Delete 요청 시 경로 오류")
    fun testDeleteMusic_NotFound() {
        // given
        val uri = "/v1/musics-error/1"

        // when, then
        performDelete(uri, MockMvcResultMatchers.status().isNotFound)
    }

    @Test
    @DisplayName("Music Delete 요청 시 클라이언트 요청 오류")
    fun testDeleteMusic_ServerError() {
        // given
        val uri = "/v1/musics/1"

        `when`(musicService.deleteMusic(any()))
                .thenThrow(MmlBadRequestException(MESSAGE_NOT_EXIST_MUSIC))

        // when, then
        performDelete(uri, MockMvcResultMatchers.status().isBadRequest)
    }

}
