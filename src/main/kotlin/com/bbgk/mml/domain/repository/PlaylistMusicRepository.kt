package com.bbgk.mml.domain.repository

import com.bbgk.mml.domain.entity.PlaylistMusic
import org.springframework.data.jpa.repository.JpaRepository

interface PlaylistMusicRepository : JpaRepository<PlaylistMusic, Long> {
}