package com.bbgk.mml.musicList.controller

import com.bbgk.mml.musicList.dto.MusicDTO
import com.bbgk.mml.musicList.dto.MusicForm
import com.bbgk.mml.musicList.service.MusicService
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/music")
class MusicController(
        val musicService: MusicService
) {
    @GetMapping
    fun getMusics(): List<MusicDTO> {
        return musicService.getMusics()
    }

    @PostMapping
    fun createMusic(@RequestBody @Validated musicForm: MusicForm) {
        musicService.saveMusic(musicForm)
    }

    @PatchMapping("/{mid}")
    fun updateMusic(@PathVariable(name="mid", required=true) mid: Long, @RequestBody @Validated musicForm: MusicForm) {
        musicService.updateMusic(mid, musicForm)
    }

    @DeleteMapping("/{mid}")
    fun deleteMusic(@PathVariable(name="mid", required=true) mid: Long) {
        musicService.deleteMusic(mid)
    }
}