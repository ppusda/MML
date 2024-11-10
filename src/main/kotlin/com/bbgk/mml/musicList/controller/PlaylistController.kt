package com.bbgk.mml.musicList.controller

import com.bbgk.mml.domain.data.ApiResponse
import com.bbgk.mml.domain.exception.MmlBadRequestException
import com.bbgk.mml.member.service.MemberService
import com.bbgk.mml.domain.dto.PlaylistDTO
import com.bbgk.mml.musicList.dto.PlaylistForm
import com.bbgk.mml.musicList.service.PlaylistService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

/**
 * 재생목록과 관련된 REST API를 제공하는 컨트롤러 입니다.
 *
 * @property playlistService 재생목록 서비스
 * @property memberService 회원 서비스
 */
@RestController
@RequestMapping("/v1/playlists")
class PlaylistController(
    val playlistService: PlaylistService,
    val memberService: MemberService
) {

    /**
     * 재생목록 목록을 조회합니다.
     *
     * @param page 현재 페이지
     * @return 재생목록 목록을 담은 페이지
     */
    @GetMapping
    @Operation(summary = "재생목록 목록 조회", description = "재생목록 목록을 조회합니다.")
    fun getPlaylists(page: Int): Page<PlaylistDTO> {
        return playlistService.getPlaylists(page)
    }

    /**
     * 입력받은 재생목록 정보로 재생목록을 생성합니다.
     *
     * @param playlistForm 재생목록 정보 폼
     * @return 200: 데이터가 저장되었습니다.
     * @throws MmlBadRequestException 존재하지 않는 사용자로 접근했을 때 발생
     */
    @PostMapping
    @Operation(summary = "재생목록 생성", description = "입력받은 재생목록 정보로 재생목록을 생성합니다.")
    fun createPlaylist(@RequestBody @Validated playlistForm: PlaylistForm, @RequestParam uid: Long): ResponseEntity<Any> {
        val owner = memberService.findMemberById(uid)
        playlistService.savePlaylist(owner, playlistForm)
        return ApiResponse.successCreate()
    }

    /**
     * 아이디에 해당하는 재생목록을 입력받은 재생목록 정보로 수정합니다.
     *
     * @param playlistId 재생목록 아이디
     * @param playlistForm 재생목록 정보 폼
     * @return 200: 데이터가 수정되었습니다.
     */
    @PatchMapping("/{playlistId}")
    @Operation(summary = "재생목록 수정", description = "아이디에 해당하는 재생목록을 입력받은 재생목록 정보로 수정합니다.")
    fun updatePlaylist(@PathVariable(name="playlistId", required=true) playlistId: Long, @RequestBody @Validated playlistForm: PlaylistForm): ResponseEntity<Any> {
        playlistService.updatePlaylist(playlistId, playlistForm)
        return ApiResponse.successUpdate()
    }

    /**
     * 아이디에 해당하는 재생목록을 삭제합니다.
     *
     * @param playlistId 재생목록 아이디
     * @return 200: 데이터가 삭제되었습니다.
     */
    @DeleteMapping("/{playlistId}")
    @Operation(summary = "재생목록 삭제", description = "아이디에 해당하는 재생목록을 삭제합니다.")
    fun deletePlaylist(@PathVariable(name="playlistId", required=true) playlistId: Long): ResponseEntity<Any> {
        playlistService.deletePlaylist(playlistId)
        return ApiResponse.successDelete()
    }

}