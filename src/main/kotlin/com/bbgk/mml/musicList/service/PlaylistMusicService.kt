package com.bbgk.mml.musicList.service

import com.bbgk.mml.domain.entity.PlaylistMusic
import com.bbgk.mml.domain.exception.MmlBadRequestException
import com.bbgk.mml.domain.repository.PlaylistMusicRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * 재생목록 내 음악과 서비스를 제공하는 클래스입니다.
 *
 * @property playlistService 재생목록 서비스
 * @property musicService 음악 서비스
 * @property playlistMusicRepository 재생목록 내 음악 리포지토리
 */
@Service
class PlaylistMusicService(
        private val playlistService: PlaylistService,
        private val musicService: MusicService,
        private val playlistMusicRepository: PlaylistMusicRepository
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
        val playlist = playlistService.findPlayListById(playlistId)
        val music = musicService.findMusicById(musicId)

        // 중복 체크
        if (playlistMusicRepository.findByPlaylistIdAndMusicId(playlistId, musicId).isPresent) {
            throw MmlBadRequestException("이미 재생목록 내 존재하는 음악입니다.")
        }

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
        val playlistMusic = findByPlaylistIdAndMusicId(playlistId, musicId)
        playlistMusicRepository.deleteById(playlistMusic.id!!)
    }

    /**
     * 아이디로 재생목록 내 음악을 검색합니다.
     *
     * @param playlistId 재생목록 아이디
     * @param musicId 음악 아이디
     * @return 검색된 재생목록 내 음악
     * @throws MmlBadRequestException 재생목록 내 존재하지 않는 음악을 검색했을 때 발생
     */
    @Transactional(readOnly = true)
    fun findByPlaylistIdAndMusicId(playlistId: Long, musicId: Long): PlaylistMusic {
        return playlistMusicRepository.findByPlaylistIdAndMusicId(playlistId, musicId).orElseThrow {
            throw MmlBadRequestException("존재하지 않는 플레이리스트 내 음악입니다.")
        }
    }

}