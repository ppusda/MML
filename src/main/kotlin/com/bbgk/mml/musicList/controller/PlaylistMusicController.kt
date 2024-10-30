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
@RequestMapping("/playlists")
class PlaylistMusicController(
    val playlistService: PlaylistService,
    val playlistMusicService: PlaylistMusicService
) {


    @GetMapping("/{playlistId}/musics")
    fun getPlaylist(@PathVariable(name="playlistId", required=true) playlistId: Long): PlaylistDTO {
        return playlistService.findPlaylistById(playlistId)
    }

    @PostMapping("/{playlistId}/musics")
    fun addMusicInPlaylist(@PathVariable(name="playlistId", required=true) playlistId: Long, @RequestParam musicId: Long) {
        playlistMusicService.addMusicInPlaylist(playlistId, musicId)
    }

    @DeleteMapping("/{playlistId}/musics")
    fun deleteMusicInPlaylist(@PathVariable(name="playlistId", required=true) playlistId: Long, @RequestParam musicId: Long) {
        playlistMusicService.deleteMusicInPlaylist(playlistId, musicId)
    }

}