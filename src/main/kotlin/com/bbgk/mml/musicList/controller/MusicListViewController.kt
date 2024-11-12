package com.bbgk.mml.musicList.controller

import com.bbgk.mml.musicList.service.MusicService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class MusicListViewController(
        private val musicService: MusicService
) {

    @GetMapping("/")
    fun index(model: Model): String {
        val musics = musicService.getMusics(0)
        model.addAttribute("musics", musics)

        return "index"
    }
}