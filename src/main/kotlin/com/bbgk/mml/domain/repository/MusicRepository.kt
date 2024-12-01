package com.bbgk.mml.domain.repository

import com.bbgk.mml.domain.entity.Music
import org.springframework.data.jpa.repository.JpaRepository

interface MusicRepository : JpaRepository<Music, Long>, CustomMusicRepository {
}