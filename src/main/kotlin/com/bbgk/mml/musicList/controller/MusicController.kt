package com.bbgk.mml.musicList.controller

import com.bbgk.mml.domain.data.ApiResponse
import com.bbgk.mml.domain.dto.MusicDTO
import com.bbgk.mml.musicList.dto.MusicForm
import com.bbgk.mml.musicList.service.MusicService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

/**
 * 음악과 관련된 REST API를 제공하는 컨트롤러 입니다.
 *
 * @property musicService 음악 서비스
 */
@RestController
@RequestMapping("/v1/musics")
class MusicController(
        val musicService: MusicService
) {

    /**
     * 음악 목록을 조회합니다.
     *
     * @param page 현재 페이지
     * @return 음악 목록을 담은 페이지
     */
    @GetMapping
    @Operation(summary = "음악 목록 조회", description = "음악 목록을 조회합니다.")
    fun getMusics(@RequestParam page: Int): Page<MusicDTO> {
        return musicService.getMusics(page)
    }

    /**
     * 입력받은 음악 정보를 등록합니다.
     *
     * @param musicForm 음악 정보 폼
     * @return 200: 데이터가 저장되었습니다.
     */
    @PostMapping
    @Operation(summary = "음악 등록", description = "입력받은 음악 정보를 등록합니다.")
    fun saveMusic(@RequestBody @Validated musicForm: MusicForm): ResponseEntity<Any> {
        musicService.saveMusic(musicForm)
        return ApiResponse.successCreate()
    }

    /**
     * 아이디에 해당하는 음악을 입력받은 음악 정보로 수정합니다.
     *
     * @param musicId 음악 아이디
     * @param musicForm 음악 정보 폼
     * @return 200: 데이터가 수정되었습니다.
     */
    @PutMapping("/{musicId}")
    @Operation(summary = "음악 수정", description = "아이디에 해당하는 음악을 입력받은 음악 정보로 수정합니다.")
    fun updateMusic(@PathVariable(name="musicId", required=true) musicId: Long, @RequestBody @Validated musicForm: MusicForm): ResponseEntity<Any> {
        musicService.updateMusic(musicId, musicForm)
        return ApiResponse.successUpdate()
    }

    /**
     * 아이디에 해당하는 음악을 삭제합니다.
     *
     * @param musicId 음악 아이디
     * @return 200: 데이터가 삭제되었습니다.
     */
    @DeleteMapping("/{musicId}")
    @Operation(summary = "음악 삭제", description = "아이디에 해당하는 음악을 삭제합니다.")
    fun deleteMusic(@PathVariable(name="musicId", required=true) musicId: Long): ResponseEntity<Any> {
        musicService.deleteMusic(musicId)
        return ApiResponse.successDelete()
    }
}