package com.bbgk.mml.musicList.dto

import com.bbgk.mml.domain.entity.Playlist

data class PlaylistCreateResponse(
        val id: Long?,
) {
    companion object {
        fun of(playlist: Playlist): PlaylistCreateResponse {
            return PlaylistCreateResponse(playlist.id)
        }
    }
}