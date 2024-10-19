package com.bbgk.mml.musicList.controller

import com.bbgk.mml.musicList.dto.PlaylistDTO
import com.bbgk.mml.musicList.service.PlaylistService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/playlist")
class PlaylistController(
        val playlistService: PlaylistService
) {
    @GetMapping
    fun getPlaylists(): List<PlaylistDTO> {
        return playlistService.getPlaylists()
    }

    @GetMapping("/{pid}/music")
    fun getPlaylist(@PathVariable(name="pid", required=true) pid: Long): PlaylistDTO {
        return playlistService.getPlaylist(pid)
    }

}