package com.bbgk.mml.domain.repository

import com.bbgk.mml.domain.entity.Music
import com.linecorp.kotlinjdsl.support.spring.data.jpa.repository.KotlinJdslJpqlExecutor

class CustomMusicRepositoryImpl(
    private val kotlinJdslJpqlExecutor: KotlinJdslJpqlExecutor
) : CustomMusicRepository {

    override fun findMusicsByKeyword(keyword: String): List<Music> {
        return kotlinJdslJpqlExecutor.findAll{
            select(entity(Music::class))
                .from(entity(Music::class))
                .whereOr(
                    path(Music::title).like("%${keyword}%"),
                    path(Music::artist).like("%${keyword}%"),
                )
        }.filterNotNull()
    }

}