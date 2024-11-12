package com.bbgk.mml.musicList.controller

import com.bbgk.mml.BaseControllerTest
import com.bbgk.mml.domain.dto.MusicDTO
import com.bbgk.mml.domain.entity.Music
import com.bbgk.mml.musicList.service.MusicService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@WebMvcTest(MusicListViewController::class)
class IndexControllerTest(
        @Autowired private val mockMvc: MockMvc
): BaseControllerTest(mockMvc) {

    @MockBean
    private lateinit var musicService: MusicService

    @Test
    fun `기본 페이지로 요청했을 때, View와 Model을 제대로 반환한다`() {
        val musicList = listOf(
                Music("title1", "artist1", "url1"),
                Music("title2", "artist2", "url2"),
                Music("title3", "artist3", "url3"),
        )

        val musicDTOs = musicList.map { MusicDTO(it) }
        val musics: Page<MusicDTO> = PageImpl(musicDTOs, pageable, DATA_SIZE.toLong())

        `when`(musicService.getMusics(0)).thenReturn(musics)

        mockMvc.perform(MockMvcRequestBuilders.get("/")
                .accept(MediaType.TEXT_HTML))
                .andExpect(MockMvcResultMatchers.status().isOk) // HTTP 200 응답 확인
                .andExpect(MockMvcResultMatchers.view().name("index")) // 반환되는 뷰 이름 확인
                .andExpect(MockMvcResultMatchers.model().attribute("musics", musics)) // 모델 내 "musics" 속성 확인
    }
}
