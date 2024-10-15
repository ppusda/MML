package com.bbgk.mml.service

import com.bbgk.mml.domain.repository.PlaylistMusicRepository
import com.bbgk.mml.domain.repository.PlaylistRepository
import com.bbgk.mml.dto.PlaylistDTO
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PlaylistService(
        val playlistRepository: PlaylistRepository,
        val playlistMusicRepository: PlaylistMusicRepository
) {

    @Transactional(readOnly = true)
    fun getPlaylists(): List<PlaylistDTO> {
        val playlists = playlistRepository.findAll()
        return playlists.map { PlaylistDTO(it) }
    }

    @Transactional(readOnly = true)
    fun getPlaylist(id: Long): PlaylistDTO {
        val playlist = playlistRepository.findById(id)
        return PlaylistDTO(playlist.get())
    }
}