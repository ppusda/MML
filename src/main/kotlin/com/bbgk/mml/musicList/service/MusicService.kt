package com.bbgk.mml.musicList.service

import com.bbgk.mml.domain.entity.Music
import com.bbgk.mml.domain.exception.MmlBadRequestException
import com.bbgk.mml.domain.repository.MusicRepository
import com.bbgk.mml.musicList.dto.MusicDTO
import com.bbgk.mml.musicList.dto.MusicForm
import com.bbgk.mml.domain.exception.MmlInternalServerErrorException
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * 음악과 관련된 서비스를 제공하는 클래스입니다.
 *
 * @property musicRepository 음악 리포지토리
 */
@Service
class MusicService(
        val musicRepository: MusicRepository
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
        val musics = musicRepository.findAll(pageable)
        return musics.map { MusicDTO(it) }
    }

    /**
     * 입력받은 음악 정보를 저장합니다.
     *
     * @param form 저장할 음악 정보
     */
    @Transactional
    fun saveMusic(form: MusicForm) {
        val music = form.toEntity()
        musicRepository.save(music)
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
        findMusicById(id)
        val music = form.toEntity(id)
        musicRepository.save(music)
    }

    /**
     * 아이디로 검색한 음악을 삭제합니다.
     *
     * @param id 음악 아이디
     * @throws MmlBadRequestException 존재하지 않는 음악을 검색했을 때 발생
     */
    @Transactional
    fun deleteMusic(id: Long) {
        findMusicById(id)
        musicRepository.deleteById(id)
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

}