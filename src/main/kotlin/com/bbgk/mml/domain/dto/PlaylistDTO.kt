package com.bbgk.mml.domain.dto

import com.bbgk.mml.domain.entity.Playlist

data class PlaylistDTO(
    val name: String,
    val ownerEmail: String,
    val musics: List<MusicDTO>
) {
    constructor(playlist: Playlist) : this (
        name = playlist.name,
        ownerEmail = playlist.member.email,
        musics = playlist.playlistMusics.map { MusicDTO(it.music) }
    )
}

// 해당 DTO가 Application 쪽 보다는 Domain 쪽에 걸맞은 보여 경로 수정