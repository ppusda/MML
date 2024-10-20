package com.bbgk.mml.member.dto

import com.bbgk.mml.domain.entity.Member
import com.bbgk.mml.domain.entity.Playlist
import jakarta.validation.constraints.NotBlank

data class MemberForm(
        @field:NotBlank(message = "이메일은 필수 값입니다.")
        val email: String,
        @field:NotBlank(message = "패스워드는 필수 값입니다.")
        val password: String
) {

    fun toEntity(): Member {
        return Member(
                email = this.email,
                password = this.password
        )
    }
}