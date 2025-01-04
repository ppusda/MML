package com.bbgk.mml.member.dto

import com.bbgk.mml.domain.entity.Member

data class MemberLoginResponse(
        val id: Long?,
        val email: String
) {
    constructor(member: Member) : this (
            id = member.id,
            email = member.email
    )
}