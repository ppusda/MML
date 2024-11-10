package com.bbgk.mml.domain.dto

import com.bbgk.mml.domain.entity.Member

data class MemberDTO(
        val id: Long?,
        val email: String,
        val playlistCount: Int
) {
    constructor(member: Member) : this (
            id = member.id,
            email = member.email,
            playlistCount = member.playlists.count()
    )
}

// 해당 DTO가 Application 쪽 보다는 Domain 쪽에 걸맞은 보여 경로 수정