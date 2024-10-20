package com.bbgk.mml.musicList.service

import com.bbgk.mml.domain.entity.Member
import com.bbgk.mml.domain.entity.Playlist
import com.bbgk.mml.domain.repository.PlaylistRepository
import com.bbgk.mml.musicList.dto.PlaylistDTO
import com.bbgk.mml.musicList.dto.PlaylistForm
import com.bbgk.mml.domain.exception.MmlInternalServerErrorException
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
        val playlist = findPlayListById(id)
        return PlaylistDTO(playlist)
    }

    @Transactional
    fun savePlaylist(owner: Member, form: PlaylistForm) {
        val music = form.toEntity(owner)
        playlistRepository.save(music)
    }

    @Transactional
    fun updatePlaylist(id: Long, form: PlaylistForm) {
        findPlayListById(id)
        val playlist = findPlayListById(id)
        playlist.update(form.name)
    }

    @Transactional
    fun deletePlaylist(id: Long) {
        findPlayListById(id)
        playlistRepository.deleteById(id)
    }

    @Transactional(readOnly = true)
    fun findPlayListById(id: Long): Playlist  {
        return playlistRepository.findById(id).orElseThrow {
            throw MmlInternalServerErrorException("존재하지 않는 플레이리스트입니다.")
        }
    }

}