package com.bbgk.mml.musicList.controller

import com.bbgk.mml.domain.entity.Playlist
import com.bbgk.mml.member.service.MemberService
import com.bbgk.mml.musicList.dto.PlaylistDTO
import com.bbgk.mml.musicList.dto.PlaylistForm
import com.bbgk.mml.musicList.service.PlaylistService
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

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
    fun createPlaylist(@RequestBody @Validated playlistForm: PlaylistForm, @RequestParam uid: Long) {
        val owner = memberService.findMemberById(uid)
        playlistService.savePlaylist(owner, playlistForm)
    }

    @PutMapping("/{pid}")
    fun updatePlaylist(@PathVariable(name="pid", required=true) pid: Long, @RequestBody @Validated playlistForm: PlaylistForm) {
        playlistService.updatePlaylist(pid, playlistForm)
    }

    @DeleteMapping("/{pid}")
    fun deletePlaylist(@PathVariable(name="pid", required=true) pid: Long) {
        playlistService.deletePlaylist(pid)
    }

}