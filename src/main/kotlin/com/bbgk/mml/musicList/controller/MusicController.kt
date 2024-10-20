package com.bbgk.mml.musicList.controller

import com.bbgk.mml.domain.data.ApiResponse
import com.bbgk.mml.musicList.dto.MusicDTO
import com.bbgk.mml.musicList.dto.MusicForm
import com.bbgk.mml.musicList.service.MusicService
import org.springframework.http.ResponseEntity
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
    fun createMusic(@RequestBody @Validated musicForm: MusicForm): ResponseEntity<Any> {
        musicService.saveMusic(musicForm)
        return ApiResponse.successCreate()
    }

    @PatchMapping("/{mid}")
    fun updateMusic(@PathVariable(name="mid", required=true) mid: Long, @RequestBody @Validated musicForm: MusicForm): ResponseEntity<Any> {
        musicService.updateMusic(mid, musicForm)
        return ApiResponse.successUpdate()
    }

    @DeleteMapping("/{mid}")
    fun deleteMusic(@PathVariable(name="mid", required=true) mid: Long): ResponseEntity<Any> {
        musicService.deleteMusic(mid)
        return ApiResponse.successDelete()
    }
}