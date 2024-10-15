package com.bbgk.mml.controller

import org.assertj.core.api.Assertions
import org.json.JSONArray
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import java.nio.charset.StandardCharsets

@SpringBootTest
@AutoConfigureMockMvc
class MusicControllerTest : BaseControllerTest() {

    @Test
    @DisplayName("Musics 조회")
    fun testGetMusics() {
        // given
        val uri = "/music"

        // when
        val mvcResult = performGet(uri)
        val contentAsString = mvcResult.response.getContentAsString(StandardCharsets.UTF_8)
        val jsonArray = JSONArray(contentAsString)

        // then
        Assertions.assertThat(jsonArray.length()).isPositive()
    }

}
