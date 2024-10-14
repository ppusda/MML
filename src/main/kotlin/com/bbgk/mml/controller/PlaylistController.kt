package com.bbgk.mml.controller

import com.bbgk.mml.dto.PlaylistDTO
import com.bbgk.mml.service.PlaylistService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/playlist")
class PlaylistController(
        val playlistService: PlaylistService
) {
    @GetMapping
    fun getIntroductions(): List<PlaylistDTO> {
        return playlistService.getPlaylists()
    }

}