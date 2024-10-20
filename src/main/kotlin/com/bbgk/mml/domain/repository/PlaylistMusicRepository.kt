package com.bbgk.mml.domain.repository

import com.bbgk.mml.domain.entity.PlaylistMusic
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface PlaylistMusicRepository : JpaRepository<PlaylistMusic, Long> {
    fun findByPlaylistIdAndMusicId(pid: Long, mid: Long): Optional<PlaylistMusic>
}