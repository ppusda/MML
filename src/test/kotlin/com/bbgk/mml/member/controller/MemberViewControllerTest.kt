package com.bbgk.mml.member.controller

import com.bbgk.mml.BaseControllerTest
import com.bbgk.mml.member.service.MemberService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@WebMvcTest(MemberViewController::class)
class MemberViewControllerTest(
        @Autowired private val mockMvc: MockMvc
): BaseControllerTest(mockMvc) {

    @MockBean
    private lateinit var memberService: MemberService

    @Test
    fun `로그인 페이지로 요청했을 때, View를 제대로 반환한다`() {
        mockMvc.perform(MockMvcRequestBuilders.get("/login")
                .accept(MediaType.TEXT_HTML))
                .andExpect(MockMvcResultMatchers.status().isOk) // HTTP 200 응답 확인
                .andExpect(MockMvcResultMatchers.view().name("login"))
    }
}
