package com.bbgk.mml.member.dto

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