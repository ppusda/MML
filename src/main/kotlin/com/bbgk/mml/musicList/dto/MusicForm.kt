package com.bbgk.mml.musicList.dto

import com.bbgk.mml.domain.entity.Music
import jakarta.validation.constraints.NotBlank

data class MusicForm(
        @field:NotBlank(message = "필수 값입니다.")
        val title: String,
        @field:NotBlank(message = "필수 값입니다.")
        val artist: String,
        val url: String
) {
    fun toEntity(): Music {
        return Music(
                title = this.title,
                artist = this.artist,
                url = this.url
        )
    }

    fun toEntity(id: Long): Music {
        val music = this.toEntity()
        music.id = id
        return music
    }
}