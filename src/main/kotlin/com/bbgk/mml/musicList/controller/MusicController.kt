package com.bbgk.mml.musicList.controller

import com.bbgk.mml.musicList.dto.MusicDTO
import com.bbgk.mml.musicList.service.MusicService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/music")
class MusicController(
        val musicService: MusicService
) {
    @GetMapping
    fun getMusics(): List<MusicDTO> {
        return musicService.getMusics()
    }
}