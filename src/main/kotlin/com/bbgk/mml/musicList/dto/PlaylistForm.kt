package com.bbgk.mml.musicList.dto

import com.bbgk.mml.domain.entity.Member
import com.bbgk.mml.domain.entity.Playlist
import jakarta.validation.constraints.NotBlank

data class PlaylistForm(
        @field:NotBlank(message = "필수 값입니다.")
        val name: String
)