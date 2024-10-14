package com.bbgk.mml.domain.repository

import com.bbgk.mml.domain.entity.Playlist
import org.springframework.data.jpa.repository.JpaRepository

interface PlaylistRepository : JpaRepository<Playlist, Long> {
}