package com.bbgk.mml.musicList.controller

import com.bbgk.mml.musicList.service.PlaylistService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class MusicListViewController(
        private val playlistService: PlaylistService
) {

    @GetMapping("/")
    fun index(model: Model): String {
        val playlists = playlistService.getPlaylists(0)
        model.addAttribute("playlists", playlists)

        return "index"
    }
}