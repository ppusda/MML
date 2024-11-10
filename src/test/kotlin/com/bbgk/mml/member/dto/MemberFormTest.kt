package com.bbgk.mml.member.dto

import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.test.Test

class MemberFormTest {
    @Test
    fun `MemberForm 테스트`() {
        // given
        val memberForm = MemberForm(
                email = "email",
                password = "password"
        )

        // when, then
        assertEquals("email", memberForm.email)
        assertEquals("password", memberForm.password)
    }
}