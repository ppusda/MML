package com.bbgk.mml.musicList.service

import com.bbgk.mml.domain.entity.Member
import com.bbgk.mml.domain.exception.MmlBadRequestException
import com.bbgk.mml.musicList.dto.PlaylistDTO
import com.bbgk.mml.musicList.dto.PlaylistForm
import com.bbgk.mml.musicList.repository.MusicListRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * 재생목록과 관련된 서비스를 제공하는 클래스입니다.
 *
 * @property musicListRepository 음악, 재생목록 관련 기능 제공 리포지토리
 */
@Service
class PlaylistService(
    val musicListRepository: MusicListRepository,
) {

    /**
     * 재생목록 목록을 조회합니다.
     *
     * @param page 현재 페이지
     * @return 재생목록 목록을 담은 페이지
     */
    @Transactional(readOnly = true)
    fun getPlaylists(page: Int): Page<PlaylistDTO> {
        val pageable = PageRequest.of(page, 5)
        val playlists = musicListRepository.getPlaylistsForPage(pageable)

        return playlists.map { PlaylistDTO(it) }
    }

    /**
     * 입력받은 재생목록 정보를 저장합니다.
     *
     * @param owner 재생목록 생성자
     * @param form 저장할 재생목록 정보
     */
    @Transactional
    fun savePlaylist(owner: Member, form: PlaylistForm) {
        val playlist = form.toEntity(owner)
        musicListRepository.savePlaylist(playlist)
    }

    /**
     * 아이디로 검색한 재생목록을 입력받은 재생목록 정보로 수정합니다.
     *
     * @param id 재생목록 아이디
     * @param form 수정할 재생목록 정보
     * @throws MmlBadRequestException 존재하지 않는 재생목록을 검색했을 때 발생
     */
    @Transactional
    fun updatePlaylist(id: Long, form: PlaylistForm) {
        val playlist = musicListRepository.findPlayListById(id)
        playlist.update(name = form.name) // 향후 코틀린의 장점을 살려 향후 파라미터 추가 시에도 동작을 위해 명시
    }

    /**
     * 아이디로 검색한 재생목록을 삭제합니다.
     *
     * @param id 재생목록 아이디
     * @throws MmlBadRequestException 존재하지 않는 재생목록을 검색했을 때 발생
     */
    @Transactional
    fun deletePlaylist(id: Long) {
        musicListRepository.findPlayListById(id) // 존재하지 않는 재생목록을 검색했을 때, 예외를 던지지 않기에 사전 검색을 진행 함
        musicListRepository.deletePlaylistById(id)
    }

    /**
     * 아이디로 재생목록을 검색합니다.
     *
     * @param id 검색할 재생목록 아이디
     * @return 검색된 재생목록 정보
     * @throws MmlBadRequestException 존재하지 않는 음악을 검색했을 때 발생
     */
    @Transactional(readOnly = true)
    fun findPlaylistById(id: Long): PlaylistDTO {
        val playlist = musicListRepository.findPlayListById(id)
        return PlaylistDTO(playlist)
    }

}