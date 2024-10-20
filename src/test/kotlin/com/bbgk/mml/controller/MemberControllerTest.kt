package com.bbgk.mml.controller

import org.assertj.core.api.Assertions
import org.json.JSONArray
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.nio.charset.StandardCharsets


class MemberControllerTest : BaseControllerTest() {

    @Test
    @DisplayName("Members 조회")
    fun testGetMembers() {
        // given
        val uri = "/member"

        // when
        val mvcResult = performGet(uri)
        val contentAsString = mvcResult.response.getContentAsString(StandardCharsets.UTF_8)
        val jsonArray = JSONArray(contentAsString)

        // then
        Assertions.assertThat(jsonArray.length()).isPositive()
    }
}