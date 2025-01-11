package com.bbgk.mml.musicList.service

import com.bbgk.mml.domain.exception.MmlBadRequestException
import com.bbgk.mml.domain.dto.MusicDTO
import com.bbgk.mml.musicList.dto.MusicForm
import com.bbgk.mml.musicList.repository.MusicListRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * 음악과 관련된 서비스를 제공하는 클래스입니다.
 *
 * @property musicListRepository 음악, 재생목록 관련 기능 제공 리포지토리
 */
@Service
class MusicService(
        val musicListRepository: MusicListRepository
) {

    /**
     * 음악 목록을 조회합니다.
     *
     * @param page 현재 페이지
     * @return 음악 목록을 담은 페이지
     */
    @Transactional(readOnly = true)
    fun getMusics(page: Int): Page<MusicDTO> {
        val pageable = PageRequest.of(page, 5);
        val musics = musicListRepository.getMusicsForPage(pageable)
        return musics.map { MusicDTO(it) }
    }

    /**
     * 음악을 검색합니다.
     * @param keyword 입력된 검색어
     * @return 검색어에 조회된 음악 목록
     */
    @Transactional(readOnly = true)
    fun searchMusics(keyword: String): List<MusicDTO> {
        val musics = musicListRepository.searchMusics(keyword)
        return musics.map { MusicDTO(it) }
    }

    /**
     * 입력받은 음악 정보를 저장합니다.
     *
     * @param form 저장할 음악 정보
     */
    @Transactional
    fun saveMusic(form: MusicForm): MusicDTO {
        val music = form.toEntity()
        return MusicDTO(musicListRepository.saveMusic(music))
    }

    /**
     * 아이디로 검색한 음악을 입력받은 음악 정보로 수정합니다.
     *
     * @param id 음악 아이디
     * @param form 수정할 음악 정보
     * @throws MmlBadRequestException 존재하지 않는 음악을 검색했을 때 발생
     */
    @Transactional
    fun updateMusic(id: Long, form: MusicForm) {
        val music = musicListRepository.findMusicById(id)
        music.update(
                title = form.title,
                artist = form.artist,
                url = form.url
        )
    }

    /**
     * 아이디로 검색한 음악을 삭제합니다.
     *
     * @param id 음악 아이디
     * @throws MmlBadRequestException 존재하지 않는 음악을 검색했을 때 발생
     */
    @Transactional
    fun deleteMusic(id: Long) {
        musicListRepository.findMusicById(id)
        musicListRepository.deleteMusicById(id)
    }

}