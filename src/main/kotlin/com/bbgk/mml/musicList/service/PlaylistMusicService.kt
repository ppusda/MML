package com.bbgk.mml.musicList.service

import com.bbgk.mml.domain.entity.PlaylistMusic
import com.bbgk.mml.domain.exception.MmlBadRequestException
import com.bbgk.mml.musicList.repository.MusicListRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * 재생목록 내 음악과 서비스를 제공하는 클래스입니다.
 *
 * @property musicListRepository 음악, 재생목록 관련 기능 제공 리포지토리
 */
@Service
class PlaylistMusicService(
        private val musicListRepository: MusicListRepository
) {

    /**
     * 재생목록 내에 음악을 추가합니다.
     *
     * @param playlistId 재생목록 아이디
     * @param musicId 음악 아이디
     * @throws MmlBadRequestException 이미 재생목록 내 존재하는 음악을 추가하려 할 때 발생
     */
    @Transactional
    fun addMusicInPlaylist(playlistId: Long, musicId: Long) {
        val playlist = musicListRepository.findPlayListById(playlistId)
        val music = musicListRepository.findMusicById(musicId)

        musicListRepository.findByPlaylistIdAndMusicId(
                playlistId = playlistId,
                musicId = musicId,
                message = "이미 재생목록 내 존재하는 음악입니다."
        ) // 중복 체크

        val playlistMusic = mutableListOf<PlaylistMusic>(
            PlaylistMusic(playlist, music)
        )

        playlist.addMusics(playlistMusic)
        // playlistMusicRepository.save(playlistMusic[0]), 영속성 전이로 스킵
    }

    /**
     * 아이디로 검색한 재생목록 내 음악을 삭제합니다.
     *
     * @param playlistId 재생목록 아이디
     * @param musicId 음악 아이디
     * @throws MmlBadRequestException 존재하지 않는 재생목록을 검색했을 때 발생
     */
    @Transactional
    fun deleteMusicInPlaylist(playlistId: Long, musicId: Long) {
        val playlistMusic = musicListRepository.findByPlaylistIdAndMusicId(
                playlistId = playlistId,
                musicId = musicId,
                message = "존재하지 않는 플레이리스트 내 음악입니다."
        )

        musicListRepository.deletePlaylistMusicById(playlistMusic.id!!)
    }

}