package com.bbgk.mml.dto

import com.bbgk.mml.domain.entity.Playlist

data class PlaylistDTO(
        val name: String,
        val musics: List<MusicDTO>
) {
    constructor(playlist: Playlist) : this (
            name = playlist.name,
            musics = playlist.playlistMusics.map { MusicDTO(it.music) }
    )
}