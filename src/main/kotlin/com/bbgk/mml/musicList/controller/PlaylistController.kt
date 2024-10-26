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
@RequestMapping("/playlist")    // 자원은 복수형으로 표현합니다. 버저닝도 필요합니다.
class PlaylistController(
    val playlistService: PlaylistService,
    val memberService: MemberService
) {

    /**
     * find와 get의 차이 https://softwareengineering.stackexchange.com/questions/182113/how-and-why-to-decide-between-naming-methods-with-get-and-find-prefixes
     * 플레이리스트의 '리스트'는 빈 리스트일지라도 '반드시' 가져옵니다.
     * 코드를 이해할 때 어감은 중요합니다.
     * 주석이 필요없도록 코드를 짜라 = 코드로 '읽히는' 글을 써라(영어 사용자 기준)
     */
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

    /**
     * PUT과 PATCH의 차이
     * 이 기능은 PUT이 맞을까요? PATCH가 맞을까요?
     */
    @PatchMapping("/{pid}")  //playlists/{p-id}
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