package com.bbgk.mml.musicList.dto

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

    // Application -> Domain
}