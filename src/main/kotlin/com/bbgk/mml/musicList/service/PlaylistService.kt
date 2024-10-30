package com.bbgk.mml.musicList.service

import com.bbgk.mml.domain.entity.Member
import com.bbgk.mml.domain.entity.Playlist
import com.bbgk.mml.domain.exception.MmlBadRequestException
import com.bbgk.mml.domain.repository.PlaylistRepository
import com.bbgk.mml.musicList.dto.PlaylistDTO
import com.bbgk.mml.musicList.dto.PlaylistForm
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * 재생목록과 관련된 서비스를 제공하는 클래스입니다.
 *
 * @property playlistRepository 재생목록 리포지토리
 */
@Service
class PlaylistService(
    val playlistRepository: PlaylistRepository,
) {

    /**
     * 전반적으로 코드를 작게 잘 쪼갰고 재사용성이 높습니다(findPlayListById())
     * 복잡한 서비스라면 DB 관련 간단한 기능을 래핑한 메소드는 별도의 클래스로 분리해도 좋습니다.
     * 일종의 Facade 패턴입니다.
     */

    /**
     * 재생목록 목록을 조회합니다.
     *
     * @param page 현재 페이지
     * @return 재생목록 목록을 담은 페이지
     */
    @Transactional(readOnly = true)
    fun getPlaylists(page: Int): Page<PlaylistDTO> {
        val pageable = PageRequest.of(page, 5)
        val playlists = playlistRepository.findAll(pageable)

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
        val music = form.toEntity(owner)
        playlistRepository.save(music)
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
        val playlist = findPlayListById(id)
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
        findPlayListById(id)
        playlistRepository.deleteById(id) // 존재하지 않는 재생목록을 검색했을 때, 예외를 던지지 않기에 사전 검색을 진행 함
    }

    /**
     * 아이디로 재생목록을 검색합니다.
     *
     * @param id 검색할 재생목록 아이디
     * @return 검색된 재생목록 정보
     * @throws MmlBadRequestException 존재하지 않는 음악을 검색했을 때 발생
     *
     * todo: 아래 내용은 위에서 언급된 퍼사드 패턴으로 수정할 계획임.
     */
    @Transactional(readOnly = true)
    fun findPlaylistById(id: Long): PlaylistDTO {
        val playlist = findPlayListById(id)
        return PlaylistDTO(playlist)
    }

    /**
     * 아이디로 재생목록을 검색합니다.
     *
     * @param id 검색할 재생목록 아이디
     * @return 검색된 재생목록
     * @throws MmlBadRequestException 존재하지 않는 재생목록을 검색했을 때 발생
     */
    @Transactional(readOnly = true)
    fun findPlayListById(id: Long): Playlist  {
        return playlistRepository.findById(id).orElseThrow {
            throw MmlBadRequestException("존재하지 않는 플레이리스트입니다.")
        }
    }

}