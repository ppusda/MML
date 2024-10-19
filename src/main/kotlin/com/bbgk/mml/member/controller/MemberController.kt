package com.bbgk.mml.member.controller

import com.bbgk.mml.member.dto.MemberDTO
import com.bbgk.mml.member.service.MemberService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/member")
class MemberController(
        private val memberService: MemberService
) {

    @GetMapping
    fun getMembers(): List<MemberDTO> {
        return memberService.getMembers()
    }

}