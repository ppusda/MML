package com.bbgk.mml.musicList.service

import com.bbgk.mml.domain.entity.PlaylistMusic
import com.bbgk.mml.domain.repository.PlaylistMusicRepository
import com.bbgk.mml.domain.exception.MmlInternalServerErrorException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PlaylistMusicService(
    private val playlistService: PlaylistService,
    private val musicService: MusicService,
    private val playlistMusicRepository: PlaylistMusicRepository
) {

    @Transactional
    fun addMusicInPlaylist(playlistId: Long, musicId: Long) {
        val playlist = playlistService.getPlayListById(playlistId)
        val music = musicService.findMusicById(musicId)

        val playlistMusic = mutableListOf<PlaylistMusic>(
            PlaylistMusic(playlist, music)
        )

        playlist.addMusics(playlistMusic)
        /**
         * CascadeType.ALL로 설정했기 때문에 Save 호출까진 필요없습니다.
         * Playlist를 조회한 순간 영속 상태가 되고, PlaylistMusic도 상태가 전이됩니다.
         *
         * 한 플레이리스트에 같은 음악은 들어갈 수 있을까요?
         */
        playlistMusicRepository.save(playlistMusic[0])
    }

    @Transactional
    fun deleteMusicInPlaylist(playlistId: Long, musicId: Long) {
        /**
         * ifPresentOrElse를 사용해볼 수도 있습니다.
         */
        val playlistMusic = playlistMusicRepository.findByPlaylistIdAndMusicId(playlistId, musicId).orElseThrow {
            throw MmlInternalServerErrorException("존재하지 않는 플레이리스트 내 음악입니다.")
        }

        playlistMusicRepository.deleteById(playlistMusic.id!!)
    }

}