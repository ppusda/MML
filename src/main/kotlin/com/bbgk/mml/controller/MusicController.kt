package com.bbgk.mml.controller

import com.bbgk.mml.dto.MusicDTO
import com.bbgk.mml.service.MusicService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/music")
class MusicController(
        val musicService: MusicService
) {
    @GetMapping
    fun getIntroductions(): List<MusicDTO> {
        return musicService.getMusics()
    }
}