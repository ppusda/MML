package com.bbgk.mml.musicList.controller

import com.bbgk.mml.domain.data.ApiResponse
import com.bbgk.mml.domain.exception.MmlBadRequestException
import com.bbgk.mml.musicList.dto.PlaylistDTO
import com.bbgk.mml.musicList.service.PlaylistMusicService
import com.bbgk.mml.musicList.service.PlaylistService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

/**
 * 재생목록 내 음악과 관련된 REST API를 제공하는 컨트롤러 입니다.
 *
 * @property playlistService 재생목록 서비스
 * @property playlistMusicService 재생목록 내 음악 서비스
 */
@RestController
@RequestMapping("/v1/playlists")
class PlaylistMusicController(
    val playlistService: PlaylistService,
    val playlistMusicService: PlaylistMusicService
) {

    /**
     * 재생목록을 조회합니다.
     *
     * @param playlistId 재생목록 아이디
     * @return 재생목록 정보
     */
    @GetMapping("/{playlistId}/musics")
    @Operation(summary = "재생목록 조회", description = "재생목록을 조회합니다.")
    fun getPlaylist(@PathVariable(name="playlistId", required=true) playlistId: Long): PlaylistDTO {
        return playlistService.findPlaylistById(playlistId)
    }

    /**
     * 재생목록 내 음악을 추가합니다.
     *
     * @param playlistId 재생목록 아이디
     * @param musicId 음악 아이디
     * @return 200: 데이터가 저장되었습니다.
     */
    @PostMapping("/{playlistId}/musics")
    @Operation(summary = "재생목록 내 음악 추가", description = "재생목록 내 음악을 추가합니다.")
    fun addMusicInPlaylist(@PathVariable(name="playlistId", required=true) playlistId: Long, @RequestParam musicId: Long): ResponseEntity<Any> {
        playlistMusicService.addMusicInPlaylist(playlistId, musicId)
        return ApiResponse.successCreate()
    }

    /**
     * 아이디에 해당하는 재생목록 내 음악을 삭제합니다.
     *
     * @param playlistId 재생목록 아이디
     * @param musicId 음악 아이디
     * @return 200: 데이터가 삭제되었습니다.
     */
    @DeleteMapping("/{playlistId}/musics")
    @Operation(summary = "재생목록 내 음악 삭제", description = "아이디에 해당하는 재생목록 내 음악을 삭제합니다.")
    fun deleteMusicInPlaylist(@PathVariable(name="playlistId", required=true) playlistId: Long, @RequestParam musicId: Long): ResponseEntity<Any> {
        playlistMusicService.deleteMusicInPlaylist(playlistId, musicId)
        return ApiResponse.successDelete()
    }

}