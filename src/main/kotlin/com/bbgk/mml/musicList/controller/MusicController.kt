package com.bbgk.mml.musicList.controller

import com.bbgk.mml.domain.data.ApiResponse
import com.bbgk.mml.musicList.dto.MusicDTO
import com.bbgk.mml.musicList.dto.MusicForm
import com.bbgk.mml.musicList.service.MusicService
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/musics")
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

    @PutMapping("/{musicId}")
    fun updateMusic(@PathVariable(name="musicId", required=true) musicId: Long, @RequestBody @Validated musicForm: MusicForm): ResponseEntity<Any> {
        musicService.updateMusic(musicId, musicForm)
        return ApiResponse.successUpdate()
    }

    @DeleteMapping("/{musicId}")
    fun deleteMusic(@PathVariable(name="musicId", required=true) musicId: Long): ResponseEntity<Any> {
        musicService.deleteMusic(musicId)
        return ApiResponse.successDelete()
    }
}