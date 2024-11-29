package com.bbgk.mml.domain.repository

import com.bbgk.mml.domain.entity.Music
import com.linecorp.kotlinjdsl.support.spring.data.jpa.repository.KotlinJdslJpqlExecutor
import org.springframework.data.jpa.repository.JpaRepository

interface MusicRepository : JpaRepository<Music, Long>, KotlinJdslJpqlExecutor {
}