package com.bbgk.mml.domain.dto

import com.bbgk.mml.domain.entity.Member
import org.junit.jupiter.api.Assertions.*
import kotlin.test.Test

class MemberDTOTest {

    @Test
    fun `MemberDTO 테스트`() {
        // given
        val member = Member("email", "password")
        val memberDTO = MemberDTO(member)

        // when, then
        assertEquals(member.email, memberDTO.email)
    }
}