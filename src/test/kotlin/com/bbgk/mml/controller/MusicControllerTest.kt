package com.bbgk.mml.controller

import com.bbgk.mml.musicList.dto.MusicForm
import org.assertj.core.api.Assertions
import org.json.JSONArray
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

import org.springframework.transaction.annotation.Transactional
import java.nio.charset.StandardCharsets

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

    @Test
    @Transactional
    @DisplayName("Music Post 요청 시 생성 성공")
    fun testPostMusic_Success() {
        // given
        val uri = "/music"
        val musicForm = MusicForm("music", "artist", "url")

        // when
        val mvcResult = performPost(uri, musicForm)
        val response = mvcResult.response

        // then
        Assertions.assertThat(response.status).isEqualTo(200)
    }

    @Test
    @DisplayName("Music Post 요청 시 경로 오류")
    fun testPostMusic_NotFound() {
        // given
        val uri = "/musics"
        val musicForm = MusicForm("music", "artist", "url")

        // when
        val mvcResult = performPost(uri, musicForm)
        val response = mvcResult.response

        // then
        Assertions.assertThat(response.status).isEqualTo(404)
    }

    @Test
    @DisplayName("Music Post 요청 시 필수 값 미입력")
    fun testPostMusic_ServerError() {
        // given
        val uri = "/music"
        val musicForm = MusicForm("", "artist", "url")

        // when
        val mvcResult = performPost(uri, musicForm)
        val response = mvcResult.response

        // then
        Assertions.assertThat(response.status).isEqualTo(400)
    }

    @Test
    @Transactional
    @DisplayName("Music Patch 요청 시 수정 성공")
    fun testPatchMusic_Success() {
        // given
        val uri = "/music/1"
        val musicForm = MusicForm("music", "artist", "url")
        // when
        val mvcResult = performPatch(uri, musicForm)
        val response = mvcResult.response

        // then
        Assertions.assertThat(response.status).isEqualTo(200)
    }

    @Test
    @DisplayName("Music Patch 요청 시 경로 오류")
    fun testPatchMusic_NotFound() {
        // given
        val uri = "/musics/1"
        val musicForm = MusicForm("music", "artist", "url")

        // when
        val mvcResult = performPatch(uri, musicForm)
        val response = mvcResult.response

        // then
        Assertions.assertThat(response.status).isEqualTo(404)
    }

    @Test
    @DisplayName("Music Patch 요청 시 서버 오류")
    fun testPatchMusic_ServerError() {
        // given
        val uri = "/music/10" // 10번 없음
        val musicForm = MusicForm("music", "artist", "url")

        // when
        val mvcResult = performPatch(uri, musicForm)
        val response = mvcResult.response

        // then
        Assertions.assertThat(response.status).isEqualTo(500)
    }

    @Test
    @Transactional
    @DisplayName("Music Delete 요청 시 삭제 성공")
    fun testDeleteMusic_Success() {
        // given
        val uri = "/music/1"

        // when
        val mvcResult = performDelete(uri)
        val response = mvcResult.response

        // then
        Assertions.assertThat(response.status).isEqualTo(200)
    }

    @Test
    @DisplayName("Music Delete 요청 시 경로 오류")
    fun testDeleteMusic_NotFound() {
        // given
        val uri = "/musics/1"

        // when
        val mvcResult = performDelete(uri)
        val response = mvcResult.response

        // then
        Assertions.assertThat(response.status).isEqualTo(404)
    }

    @Test
    @DisplayName("Music Delete 요청 시 서버 오류")
    fun testDeleteMusic_ServerError() {
        // given
        val uri = "/music/5"

        // when
        val mvcResult = performDelete(uri)
        val response = mvcResult.response

        // then
        Assertions.assertThat(response.status).isEqualTo(500)
    }


}
