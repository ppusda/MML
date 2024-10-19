package com.bbgk.mml.musicList.dto

import com.bbgk.mml.domain.entity.Music

data class MusicDTO(
        val title: String,
        val artist: String,
        val url: String
) {
    constructor(music: Music) : this (
            title = music.title,
            artist = music.artist,
            url = music.url
    )
}