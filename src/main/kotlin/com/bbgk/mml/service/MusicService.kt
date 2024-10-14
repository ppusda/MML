package com.bbgk.mml.service

import com.bbgk.mml.domain.repository.MusicRepository
import com.bbgk.mml.dto.MusicDTO
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
}