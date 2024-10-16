package com.bbgk.mml.service

import com.bbgk.mml.domain.repository.PlaylistRepository
import com.bbgk.mml.dto.PlaylistDTO
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PlaylistService(
        val playlistRepository: PlaylistRepository,
) {

    @Transactional(readOnly = true)
    fun getPlaylists(): List<PlaylistDTO> {
        val playlists = playlistRepository.findAll()
        return playlists.map { PlaylistDTO(it) }
    }

    @Transactional(readOnly = true)
    fun getPlaylist(id: Long): PlaylistDTO {
        val playlist = playlistRepository.findById(id).orElseThrow {
            RuntimeException("존재하지 않는 플레이리스트입니다.")
        }
        return PlaylistDTO(playlist)
    }
}