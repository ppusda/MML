package com.bbgk.mml.musicList.controller

import com.bbgk.mml.domain.data.ApiResponse
import com.bbgk.mml.member.service.MemberService
import com.bbgk.mml.musicList.dto.PlaylistDTO
import com.bbgk.mml.musicList.dto.PlaylistForm
import com.bbgk.mml.musicList.service.PlaylistService
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/playlist")
class PlaylistController(
        val playlistService: PlaylistService,
        val memberService: MemberService
) {
    @GetMapping
    fun getPlaylists(): List<PlaylistDTO> {
        return playlistService.getPlaylists()
    }

    @PostMapping
    fun createPlaylist(@RequestBody @Validated playlistForm: PlaylistForm, @RequestParam uid: Long): ResponseEntity<Any> {
        val owner = memberService.findMemberById(uid)
        playlistService.savePlaylist(owner, playlistForm)
        return ApiResponse.successCreate()
    }

    @PatchMapping("/{pid}")
    fun updatePlaylist(@PathVariable(name="pid", required=true) pid: Long, @RequestBody @Validated playlistForm: PlaylistForm): ResponseEntity<Any> {
        playlistService.updatePlaylist(pid, playlistForm)
        return ApiResponse.successUpdate()
    }

    @DeleteMapping("/{pid}")
    fun deletePlaylist(@PathVariable(name="pid", required=true) pid: Long): ResponseEntity<Any> {
        playlistService.deletePlaylist(pid)
        return ApiResponse.successDelete()
    }

}