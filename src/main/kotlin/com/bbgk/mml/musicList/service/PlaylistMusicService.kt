package com.bbgk.mml.musicList.service

import com.bbgk.mml.domain.entity.Member
import com.bbgk.mml.domain.entity.Playlist
import com.bbgk.mml.domain.entity.PlaylistMusic
import com.bbgk.mml.domain.repository.PlaylistMusicRepository
import com.bbgk.mml.domain.repository.PlaylistRepository
import com.bbgk.mml.musicList.dto.MusicForm
import com.bbgk.mml.musicList.dto.PlaylistDTO
import com.bbgk.mml.musicList.dto.PlaylistForm
import jakarta.transaction.Transactional.TxType
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PlaylistMusicService(
        private val playlistService: PlaylistService,
        private val musicService: MusicService,
        private val playlistMusicRepository: PlaylistMusicRepository
) {

    @Transactional
    fun addMusicInPlaylist(pid: Long, mid: Long) {
        val playlist = playlistService.findPlayListById(pid)
        val music = musicService.findMusicById(mid)

        val playlistMusic = mutableListOf<PlaylistMusic>(
                PlaylistMusic(playlist, music)
        )

        playlist.addMusics(playlistMusic)
        playlistMusicRepository.save(playlistMusic[0])
    }

    @Transactional
    fun deleteMusicInPlaylist(pid: Long, mid: Long) {
        val playlistMusic = playlistMusicRepository.findByPlaylistIdAndMusicId(pid, mid).orElseThrow {
            throw RuntimeException("존재하지 않는 플레이리스트 내 음악입니다.")
        }

        playlistMusicRepository.deleteById(playlistMusic.id!!)
    }

}