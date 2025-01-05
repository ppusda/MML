package com.bbgk.mml.domain.dto

import com.bbgk.mml.domain.entity.Music

data class MusicDTO(
        val id: Long?,
        val title: String,
        val artist: String,
        val url: String
) {
    constructor(music: Music) : this (
            id = music.id,
            title = music.title,
            artist = music.artist,
            url = music.url
    )
}

// 해당 DTO가 Application 쪽 보다는 Domain 쪽에 걸맞은 보여 경로 수정