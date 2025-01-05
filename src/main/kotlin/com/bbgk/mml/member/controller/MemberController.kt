package com.bbgk.mml.member.controller

import com.bbgk.mml.domain.dto.MemberDTO
import com.bbgk.mml.member.dto.MemberForm
import com.bbgk.mml.member.dto.MemberLoginResponse
import com.bbgk.mml.member.service.MemberService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.data.domain.Page
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

/**
 * 회원과 관련된 REST API를 제공하는 컨트롤러 입니다.
 *
 * @property memberService 회원 관련 기능 제공 서비스
 */
@RestController
@RequestMapping("/v1/members")
@Tag(name = "Member", description = "회원과 관련된 REST API를 제공하는 컨트롤러 입니다.")
class MemberController(
        private val memberService: MemberService
) {

    /**
     * 회원 목록을 조회합니다.
     *
     * @param page 현재 페이지
     * @return 회원 목록을 담은 페이지
     */
    @GetMapping
    @Operation(summary = "회원 목록 조회", description = "회원 목록을 조회합니다.")
    fun getMembers(@RequestParam page: Int): Page<MemberDTO> {
        return memberService.getMembers(page)
    }

    /**
     * 재생목록 생성 시 정보를 받아오기 위한 로그인입니다.
     * 
     * @param form 입력된 회원 정보
     * @return 재생목록 생성 시 필요한 회원 정보
     */
    @PostMapping
    @Operation(summary = "회원 로그인", description = "재생목록 생성 시 필요한 회원 정보를 반환합니다.")
    fun loginMember(@RequestBody @Validated form: MemberForm): MemberLoginResponse {
        return memberService.loginMember(form)
    }
}