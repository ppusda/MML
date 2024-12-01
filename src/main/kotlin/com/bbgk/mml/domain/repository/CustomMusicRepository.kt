package com.bbgk.mml.domain.repository

import com.bbgk.mml.domain.entity.Music

interface CustomMusicRepository {
    fun findMusicsByKeyword(keyword: String): List<Music>
}