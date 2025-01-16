package com.bbgk.mml.musicList.controller

import com.bbgk.mml.BaseControllerTest
import com.bbgk.mml.domain.dto.MusicDTO
import com.bbgk.mml.domain.dto.PlaylistDTO
import com.bbgk.mml.domain.entity.Music
import com.bbgk.mml.domain.util.PageUtils
import com.bbgk.mml.musicList.service.PlaylistService
import org.junit.jupiter.api.Test
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
class MusicListViewControllerTest(
        @Autowired private val mockMvc: MockMvc
): BaseControllerTest(mockMvc) {

    @MockBean
    private lateinit var playlistService: PlaylistService

    @Test
    fun `기본 페이지로 요청했을 때, View와 Model을 제대로 반환한다`() {
        val musicList = mutableListOf(
                Music("title1", "artist1", "url1"),
                Music("title2", "artist2", "url2"),
                Music("title3", "artist3", "url3"),
        )

        val playlistDTOs = mutableListOf(
                PlaylistDTO("name", "email", musicList.map { MusicDTO(it) })
        )

        val playlists: Page<PlaylistDTO> = PageImpl(playlistDTOs, pageable, PageUtils.PAGE_SIZE.toLong())

        `when`(playlistService.getPlaylists(0)).thenReturn(playlists)

        mockMvc.perform(MockMvcRequestBuilders.get("/")
                .accept(MediaType.TEXT_HTML))
                .andExpect(MockMvcResultMatchers.status().isOk) // HTTP 200 응답 확인
                .andExpect(MockMvcResultMatchers.view().name("index"))
                .andExpect(MockMvcResultMatchers.model().attribute("playlists", playlists))
    }

    @Test
    fun `재생목록 페이지로 요청했을 때, View를 제대로 반환한다`() {
        mockMvc.perform(MockMvcRequestBuilders.get("/playlist")
                .accept(MediaType.TEXT_HTML))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.view().name("playlist"))
    }
}
