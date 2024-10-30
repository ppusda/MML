package com.bbgk.mml.member.controller

import com.bbgk.mml.member.dto.MemberDTO
import com.bbgk.mml.member.service.MemberService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.data.domain.Page
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

/**
 * 회원과 관련된 Rest Api를 제공하는 컨트롤러 입니다.
 *
 * @property memberService 회원 서비스
 */
@RestController
@RequestMapping("/v1/members")
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

}