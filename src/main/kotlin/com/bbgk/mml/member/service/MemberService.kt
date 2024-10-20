package com.bbgk.mml.member.service

import com.bbgk.mml.domain.entity.Member
import com.bbgk.mml.domain.repository.MemberRepository
import com.bbgk.mml.member.dto.MemberDTO
import com.bbgk.mml.member.dto.MemberForm
import com.bbgk.mml.domain.exception.MmlInternalServerErrorException
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

    @Transactional(readOnly = true)
    fun findMemberById(id: Long): Member {
        return memberRepository.findById(id).orElseThrow{
            throw MmlInternalServerErrorException("존재하지 않는 사용자입니다.")
        }
    }

    @Transactional
    fun saveMember(form: MemberForm) {
        val member = form.toEntity()
        memberRepository.save(member)
    }

    @Transactional
    fun deleteMember(id: Long) {
        findMemberById(id)
        memberRepository.deleteById(id)
    }

}