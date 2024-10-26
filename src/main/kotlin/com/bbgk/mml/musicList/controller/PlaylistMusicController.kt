package com.bbgk.mml.musicList.controller

import com.bbgk.mml.musicList.dto.PlaylistDTO
import com.bbgk.mml.musicList.service.PlaylistMusicService
import com.bbgk.mml.musicList.service.PlaylistService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/playlist")
class PlaylistMusicController(
    val playlistService: PlaylistService,
    val playlistMusicService: PlaylistMusicService
) {

    /**
     * /playlists/{pid}
     */
    @GetMapping("/{pid}/music")
    fun getPlaylist(@PathVariable(name="pid", required=true) pid: Long): PlaylistDTO {
        return playlistService.getPlaylist(pid)
    }

    /**
     * /playlists/{pid}/musics/{mid}
     */
    @PostMapping("/{pid}/music")
    fun addMusicInPlaylist(@PathVariable(name="pid", required=true) pid: Long, @RequestParam mid: Long) {
        playlistMusicService.addMusicInPlaylist(pid, mid)
    }

    @DeleteMapping("/{pid}/music")
    fun deleteMusicInPlaylist(@PathVariable(name="pid", required=true) pid: Long, @RequestParam mid: Long) {
        playlistMusicService.deleteMusicInPlaylist(pid, mid)
    }

}