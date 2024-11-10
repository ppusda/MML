package com.bbgk.mml.member.controller

import com.bbgk.mml.BaseControllerTest
import com.bbgk.mml.domain.entity.Member
import com.bbgk.mml.domain.dto.MemberDTO
import com.bbgk.mml.member.service.MemberService
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
        val uri = "/v1/members?page=0"

        val memberList = listOf(
                Member("test1", "test1"),
                Member("test2", "test2"),
                Member("test3", "test3"),
        )

        val memberDTOs = memberList.map { MemberDTO(it) }
        val members: Page<MemberDTO> = PageImpl(memberDTOs, pageable, DATA_SIZE.toLong())

        `when`(memberService.getMembers(any()))
                .thenReturn(members)

        // when
        performGet(uri, MockMvcResultMatchers.status().isOk)

        // then
        verify(memberService).getMembers(any())
    }

}