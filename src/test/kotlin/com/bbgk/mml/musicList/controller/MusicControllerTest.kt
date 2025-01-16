package com.bbgk.mml.musicList.controller

import com.bbgk.mml.BaseControllerTest
import com.bbgk.mml.domain.entity.Music
import com.bbgk.mml.domain.exception.MmlBadRequestException
import com.bbgk.mml.domain.dto.MusicDTO
import com.bbgk.mml.domain.exception.GlobalExceptionMessage
import com.bbgk.mml.domain.exception.GlobalExceptionMessage.REQUIRED_ARGUMENT
import com.bbgk.mml.domain.exception.MusicListExceptionMessage
import com.bbgk.mml.domain.exception.MusicListExceptionMessage.NOT_EXIST_MUSIC
import com.bbgk.mml.domain.util.PageUtils
import com.bbgk.mml.musicList.dto.MusicForm
import com.bbgk.mml.musicList.service.MusicService
import org.assertj.core.api.Assertions.*
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
        val uri = "/v1/musics?page=$PAGE"

        val musicDTOs = musicList.map { MusicDTO(it) }
        val musics: Page<MusicDTO> = PageImpl(musicDTOs, pageable, PageUtils.PAGE_SIZE.toLong())

        `when`(musicService.getMusics(any()))
                .thenReturn(musics)

        // when, then
        mockMvc.perform(MockMvcRequestBuilders.get(uri))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.content", hasSize<Any>(3)))
            .andReturn()

        verify(musicService).getMusics(any())
    }

    @Test
    @DisplayName("키워드로 음악 검색 요청을 보낸다")
    fun testSearchMusics() {
        // given
        val keyword = "title"
        val uri = "/v2/musics?keyword=$keyword"


        // 더미 데이터 생성
        val musics = listOf(
            Music("title1", "artist1", "url1"),
            Music("title2", "artist2", "url2"),
            Music("title3", "artist3", "url3"),
        )
        val musicDTOs = musics.map { MusicDTO(it) }

        // Controller 내 비즈니스 로직이 실행되었을 때, 반환될 데이터 설정
        `when`(musicService.searchMusics(any()))
            .thenReturn(musicDTOs)

        // when, then
        mockMvc.perform(MockMvcRequestBuilders.get(uri))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize<Any>(3)))
            .andReturn()

        verify(musicService).searchMusics(keyword) // 실행되었는지 검증
    }

    @Test
    @DisplayName("Music Post 요청 시 생성 성공")
    fun testPostMusic_Success() {
        // given
        val uri = "/v1/musics"

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
        assertThat(response.contentAsString).contains(REQUIRED_ARGUMENT.message)
    }

    @Test
    @DisplayName("Music Put 요청 시 수정 성공")
    fun testPatchMusic_Success() {
        // given
        val uri = "/v1/musics/${MUSIC_ID}"

        // when
        performPut(uri, musicForm, MockMvcResultMatchers.status().isOk)

        // then
        verify(musicService).updateMusic(any(), any())
    }

    @Test
    @DisplayName("Music Put 요청 시 경로 오류")
    fun testPatchMusic_NotFound() {
        // given
        val uri = "/v1/musics-error/${MUSIC_ID}"

        // when, then
        performPut(uri, musicForm, MockMvcResultMatchers.status().isNotFound)
    }

    @Test
    @DisplayName("Music Put 요청 시 클라이언트 요청 오류")
    fun testPatchMusic_ServerError() {
        // given
        val uri = "/v1/musics/${MUSIC_ID}"

        `when`(musicService.updateMusic(any(), any()))
                .thenThrow(MmlBadRequestException(NOT_EXIST_MUSIC.message))

        // when, then
        performPut(uri, musicForm, MockMvcResultMatchers.status().isBadRequest)
    }

    @Test
    @DisplayName("Music Delete 요청 시 삭제 성공")
    fun testDeleteMusic_Success() {
        // given
        val uri = "/v1/musics/${MUSIC_ID}"

        // when
        performDelete(uri, MockMvcResultMatchers.status().isOk)

        // then
        verify(musicService).deleteMusic(any())
    }

    @Test
    @DisplayName("Music Delete 요청 시 경로 오류")
    fun testDeleteMusic_NotFound() {
        // given
        val uri = "/v1/musics-error/${MUSIC_ID}"

        // when, then
        performDelete(uri, MockMvcResultMatchers.status().isNotFound)
    }

    @Test
    @DisplayName("Music Delete 요청 시 클라이언트 요청 오류")
    fun testDeleteMusic_ServerError() {
        // given
        val uri = "/v1/musics/${MUSIC_ID}"

        `when`(musicService.deleteMusic(any()))
                .thenThrow(MmlBadRequestException(NOT_EXIST_MUSIC.message))

        // when, then
        performDelete(uri, MockMvcResultMatchers.status().isBadRequest)
    }

}
