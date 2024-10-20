package com.bbgk.mml.controller

import org.assertj.core.api.Assertions
import org.json.JSONArray
import org.json.JSONObject
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import java.nio.charset.StandardCharsets


class PlaylistControllerTest : BaseControllerTest() {

    private val N: Int = 1

    @Test
    @DisplayName("Playlists 조회")
    fun testGetPlaylists() {
        // given
        val uri = "/playlist"

        // when
        val mvcResult = performGet(uri)
        val contentAsString = mvcResult.response.getContentAsString(StandardCharsets.UTF_8)
        val jsonArray = JSONArray(contentAsString)

        // then
        Assertions.assertThat(jsonArray.length()).isPositive()
    }

    @Test
    @DisplayName("N번 Playlist 조회")
    fun testGetNPlaylist() {
        // given
        val uri = "/playlist/${N}/music"

        // when
        val mvcResult = performGet(uri)
        val contentAsString = mvcResult.response.getContentAsString(StandardCharsets.UTF_8)
        val jsonObject = JSONObject(contentAsString)

        // then
        Assertions.assertThat(jsonObject.optJSONArray("musics").length()).isPositive()
    }

}