package com.bbgk.mml.musicList.repository

import com.bbgk.mml.domain.entity.Music
import com.bbgk.mml.domain.entity.Playlist
import com.bbgk.mml.domain.entity.PlaylistMusic
import com.bbgk.mml.domain.exception.MmlBadRequestException
import com.bbgk.mml.domain.repository.MusicRepository
import com.bbgk.mml.domain.repository.PlaylistMusicRepository
import com.bbgk.mml.domain.repository.PlaylistRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

/**
 * 음악, 재생목록과 관련된 DB 기능을 제공하는 리포지토리입니다.
 *
 * @property musicRepository 음악 리포지토리
 * @property playlistRepository 재생목록 리포지토리
 * @property playlistMusicRepository 재생목록 내 음악 리포지토리
 */
@Repository
class MusicListRepository( // 퍼사드 패턴 적용, 간단한 DB 기능 분리
        private val musicRepository: MusicRepository,
        private val playlistRepository: PlaylistRepository,
        private val playlistMusicRepository: PlaylistMusicRepository
) {

    /**
     * 음악 목록을 페이지 별로 조회합니다.
     *
     * @param pageable 페이지 정보
     * @return 음악 목록을 담은 페이지
     */
    @Transactional(readOnly = true)
    fun getMusicsForPage(pageable: Pageable): Page<Music> {
        return musicRepository.findAll(pageable)
    }

    /**
     * 음악을 저장합니다.
     *
     * @param music 저장할 음악
     */
    @Transactional
    fun saveMusic(music: Music) {
        musicRepository.save(music)
    }

    /**
     * 아이디에 해당하는 음악을 삭제합니다.
     *
     * @param musicId 음악 아이디
     */
    @Transactional
    fun deleteMusicById(musicId: Long) {
        musicRepository.deleteById(musicId)
    }

    /**
     * 아이디로 음악을 검색합니다.
     *
     * @param id 검색할 음악 아이디
     * @return 검색된 음악
     * @throws MmlBadRequestException 존재하지 않는 음악을 검색했을 때 발생
     */
    @Transactional(readOnly = true)
    fun findMusicById(id: Long): Music {
        return musicRepository.findById(id).orElseThrow{
            throw MmlBadRequestException("존재하지 않는 음악입니다.")
        }
    }

    /**
     * 재생목록 목록을 페이지 별로 조회합니다.
     *
     * @param pageable 페이지 정보
     * @return 재생목록 목록을 담은 페이지
     */
    @Transactional(readOnly = true)
    fun getPlaylistsForPage(pageable: Pageable): Page<Playlist> {
        return playlistRepository.findAll(pageable)
    }

    /**
     * 재생목록을 저장합니다.
     *
     * @param playlist 저장할 재생목록
     */
    @Transactional
    fun savePlaylist(playlist: Playlist) {
        playlistRepository.save(playlist)
    }

    /**
     * 아이디에 해당하는 재생목록을 삭제합니다.
     *
     * @param playlistId 재생목록 아이디
     */
    @Transactional
    fun deletePlaylistById(playlistId: Long) {
        playlistRepository.deleteById(playlistId)
    }

    /**
     * 아이디로 재생목록을 검색합니다.
     *
     * @param id 검색할 재생목록 아이디
     * @return 검색된 재생목록
     * @throws MmlBadRequestException 존재하지 않는 재생목록을 검색했을 때 발생
     */
    @Transactional(readOnly = true)
    fun findPlayListById(id: Long): Playlist {
        return playlistRepository.findById(id).orElseThrow {
            throw MmlBadRequestException("존재하지 않는 플레이리스트입니다.")
        }
    }

    /**
     * 아이디에 해당하는 재생목록 내 음악을 삭제합니다.
     *
     * @param playlistMusicId 재생목록 내 음악 아이디
     */
    @Transactional
    fun deletePlaylistMusicById(playlistMusicId: Long) {
        playlistMusicRepository.deleteById(playlistMusicId)
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
    fun findByPlaylistIdAndMusicId(playlistId: Long, musicId: Long, message: String): PlaylistMusic {
        return playlistMusicRepository.findByPlaylistIdAndMusicId(playlistId, musicId).orElseThrow {
            throw MmlBadRequestException(message)
        }
    }

    /**
     * 키워드로 음악을 검색합니다.
     *
     * @param keyword 검색 키워드
     * @return 검색된 음악 목록
     */
    @Transactional(readOnly = true)
    fun searchMusics(keyword: String): List<Music> {
        return musicRepository.findMusicsByKeyword(keyword);
    }
}