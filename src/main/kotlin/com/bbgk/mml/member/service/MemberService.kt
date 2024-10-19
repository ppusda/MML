package com.bbgk.mml.member.service

import com.bbgk.mml.domain.repository.MemberRepository
import com.bbgk.mml.member.dto.MemberDTO
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MemberService(
        private val memberRepository: MemberRepository
) {

    @Transactional(readOnly = true)
    fun getMembers(): List<MemberDTO> {
        val members = memberRepository.findAll()
        return members.map { MemberDTO(it) }
    }
}