package com.bbgk.mml.member.service

import com.bbgk.mml.domain.entity.Member
import com.bbgk.mml.domain.exception.MmlBadRequestException
import com.bbgk.mml.domain.repository.MemberRepository
import com.bbgk.mml.member.dto.MemberDTO
import com.bbgk.mml.member.dto.MemberForm
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MemberService(
        private val memberRepository: MemberRepository
) {


    /**
     * 회원 목록을 조회합니다.
     *
     * @param page 현재 페이지
     * @return 회원 목록을 담은 페이지
     */
    @Transactional(readOnly = true)
    fun getMembers(page: Int): Page<MemberDTO> {
        val pageable = PageRequest.of(page, 5)
        val members = memberRepository.findAll(pageable)
        return members.map { MemberDTO(it) }
    }

    /**
     * 아이디로 회원을 검색합니다.
     *
     * @param id 검색할 회원 아이디
     * @return 검색된 회원
     * @throws MmlBadRequestException 존재하지 않는 사용자를 검색했을 때 발생
     */
    @Transactional(readOnly = true)
    fun findMemberById(id: Long): Member {
        return memberRepository.findById(id).orElseThrow{
            throw MmlBadRequestException("존재하지 않는 사용자입니다.")
        }
    }

    /**
     * 입력받은 회원 정보를 저장합니다.
     *
     * @param form 저장할 회원 정보
     */
    @Transactional
    fun saveMember(form: MemberForm) {
        val member = form.toEntity()
        memberRepository.save(member)
    }

    /**
     * 아이디로 회원을 삭제합니다.
     *
     * @param id 검색할 회원 아이디
     * @throws MmlBadRequestException 존재하지 않는 사용자를 검색했을 때 발생
     */
    @Transactional
    fun deleteMember(id: Long) {
        findMemberById(id)
        memberRepository.deleteById(id)
    }

}