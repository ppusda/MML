package com.bbgk.mml.member.controller

import com.bbgk.mml.BaseControllerTest
import org.assertj.core.api.Assertions
import org.json.JSONObject
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.nio.charset.StandardCharsets


class MemberControllerTest : BaseControllerTest() {

    @Test
    @DisplayName("Members 조회")
    fun testGetMembers() {
        // given
        val uri = "/v1/members?page=0"

        // when
        val mvcResult = performGet(uri)
        val contentAsString = mvcResult.response.getContentAsString(StandardCharsets.UTF_8)
        val jsonObject = JSONObject(contentAsString)

        // then
        Assertions.assertThat(jsonObject.optJSONArray("content").length()).isPositive()
    }


}