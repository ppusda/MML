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

    /**
     * 전반적으로 코드를 작게 잘 쪼갰고 재사용성이 높습니다(findPlayListById())
     * 복잡한 서비스라면 DB 관련 간단한 기능을 래핑한 메소드는 별도의 클래스로 분리해도 좋습니다.
     * 일종의 Facade 패턴입니다.
     */

    @Transactional(readOnly = true)
    fun getPlaylists(): List<PlaylistDTO> {
        /**
         * 리스트 조회는 일반적으로 페이지네이션이 필요합니다.
         * val pageable: PageRequest = PageRequest.of(0, playlists.size)
         * val page: Page<Playlist> = playlistRepository.findAll(pageable)
         */
        val playlists = playlistRepository.findAll()

        /**
         * DTO가 엔티티에 의존하도록 신경을 잘 썼습니다.
         */
        return playlists.map { PlaylistDTO(it) }
    }

    @Transactional(readOnly = true)
    fun getPlaylist(id: Long): PlaylistDTO {
        /**
         * find니깐 Optional이나 Nullable 할 것으로 예상됩니다.
         */
        val playlist = getPlayListById(id)
        return PlaylistDTO(playlist)
    }

    @Transactional
    fun savePlaylist(owner: Member, form: PlaylistForm) {
        val music = form.toEntity(owner)
        playlistRepository.save(music)
    }

    @Transactional
    fun updatePlaylist(id: Long, form: PlaylistForm) {
        val playlist = getPlayListById(id)

        /**
         * 업데이트할 필드가 많아지면 어떻게 할 수 있을까요?
         * 1. 파라미터 추가할 수 있습니다. 코틀린의 장점입니다.
         * 2. 개별 업데이트 메소드 추가할 수 있습니다. 자바라면 이 방식이 안전할 것입니다.
         */
        playlist.update(name = form.name)
    }

    @Transactional
    fun deletePlaylist(id: Long) {
        /**
         * 검증이 필요할까요?
         * delete에서 삭제할 id가 없으면 예외를 던지진 않을까?
         */
        getPlayListById(id)
        playlistRepository.deleteById(id) // 들어가봅시다
    }

    @Transactional(readOnly = true)
    fun getPlayListById(id: Long): Playlist  {
        return playlistRepository.findById(id).orElseThrow {
            throw MmlInternalServerErrorException("존재하지 않는 플레이리스트입니다.") // 서버의 책임일까요?
        }
    }

}