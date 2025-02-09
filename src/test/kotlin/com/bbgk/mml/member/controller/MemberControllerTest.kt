package com.bbgk.mml.member.controller

import com.bbgk.mml.BaseControllerTest
import com.bbgk.mml.domain.entity.Member
import com.bbgk.mml.domain.dto.MemberDTO
import com.bbgk.mml.domain.util.PageUtils
import com.bbgk.mml.member.dto.MemberForm
import com.bbgk.mml.member.dto.MemberLoginResponse
import com.bbgk.mml.member.service.MemberService
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

@WebMvcTest(MemberController::class)
class MemberControllerTest(
        @Autowired private val mockMvc: MockMvc
) : BaseControllerTest(mockMvc) {

    @MockBean
    private lateinit var memberService: MemberService

    @Test
    @DisplayName("Members 조회")
    fun testGetMembers() {
        // given
        val uri = "/v1/members?page=$PAGE"

        val memberDTOs = memberList.map { MemberDTO(it) }
        val members: Page<MemberDTO> = PageImpl(memberDTOs, pageable, PageUtils.PAGE_SIZE.toLong())

        `when`(memberService.getMembers(any()))
                .thenReturn(members)

        // when, then
        mockMvc.perform(MockMvcRequestBuilders.get(uri))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.content", hasSize<Any>(3)))
            .andReturn()

        verify(memberService).getMembers(any())
    }

    @Test
    fun `재생목록 생성 시 필요한 회원 로그인`() {
        // given
        val uri = "/v1/members"

        val form = MemberForm(member.email, member.password)
        val memberLoginResponse = MemberLoginResponse(member)

        `when`(memberService.loginMember(any()))
            .thenReturn(memberLoginResponse)

        // when
        performPost(uri, form, MockMvcResultMatchers.status().isOk)

        // then
        verify(memberService).loginMember(any())
    }

}