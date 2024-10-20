package com.bbgk.mml.musicList.service

import com.bbgk.mml.domain.entity.Music
import com.bbgk.mml.domain.repository.MusicRepository
import com.bbgk.mml.musicList.dto.MusicDTO
import com.bbgk.mml.musicList.dto.MusicForm
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MusicService(
        val musicRepository: MusicRepository
) {

    @Transactional(readOnly = true)
    fun getMusics(): List<MusicDTO> {
        val musics = musicRepository.findAll()
        return musics.map { MusicDTO(it) }
    }

    @Transactional
    fun saveMusic(form: MusicForm) {
        val music = form.toEntity()
        musicRepository.save(music)
    }

    @Transactional
    fun updateMusic(id: Long, form: MusicForm) {
        val music = form.toEntity(id)
        musicRepository.save(music)
    }

    @Transactional
    fun deleteMusic(id: Long) {
        musicRepository.deleteById(id)
    }

    @Transactional(readOnly = true)
    fun findMusicById(id: Long): Music {
        return musicRepository.findById(id).orElseThrow{
            throw RuntimeException("존재하지 않는 음악입니다.")
        }
    }

}