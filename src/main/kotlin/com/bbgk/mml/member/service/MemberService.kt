package com.bbgk.mml.member.service

import com.bbgk.mml.domain.dto.MemberDTO
import com.bbgk.mml.domain.entity.Member
import com.bbgk.mml.domain.exception.MmlBadRequestException
import com.bbgk.mml.domain.repository.MemberRepository
import com.bbgk.mml.member.dto.MemberForm
import com.bbgk.mml.member.dto.MemberLoginResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * 회원과 관련된 서비스를 제공하는 클래스입니다.
 *
 * @property memberRepository 회원 리포지토리
 */
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
     * 이메일로 회원을 검색합니다.
     *
     * @param email 검색할 회원의 이메일
     * @return 검색된 회원
     * @throws MmlBadRequestException 존재하지 않는 사용자를 검색했을 때 발생
     */
    @Transactional(readOnly = true)
    fun findMemberByEmail(email: String): Member {
        return memberRepository.findByEmail(email).orElseThrow {
            throw MmlBadRequestException("존재하지 않는 사용자입니다.")
        }
    }

    /**
     * 입력받은 회원 정보를 저장합니다.
     *
     * @param form 저장할 회원 정보
     */
    @Transactional
    fun saveMember(form: MemberForm): Member {
        val member = form.toEntity()
        return memberRepository.save(member)
    }

    /**
     * 아이디로 검색한 회원을 삭제합니다.
     *
     * @param id 검색할 회원 아이디
     * @throws MmlBadRequestException 존재하지 않는 사용자를 검색했을 때 발생
     */
    @Transactional
    fun deleteMember(id: Long) {
        findMemberById(id)
        memberRepository.deleteById(id)
    }

    /**
     * 재생목록 생성 시 필요한 회원 정보를 받아오기 위한 로그인 메서드
     *
     * @param memberForm 로그인 시 입력 된 회원정보
     * @return 재생목록 생성 시 필요한 회원 정보
     */
    @Transactional
    fun loginMember(memberForm: MemberForm): MemberLoginResponse {
        val member: Member = try {
            findMemberByEmail(memberForm.email)
        } catch (e: MmlBadRequestException) {
            saveMember(memberForm)
        }

        validatePassword(member, memberForm.password)

        return MemberLoginResponse(member)
    }

    /**
     * 로그인 시 존재하는 회원이라면 패스워드가 일치하는 지 검사하는 메서드
     *
     * @param member 검색 된 회원 정보
     * @param password 입력된 회원의 비밀번호
     * @throws MmlBadRequestException 비밀번호가 일치하지 않을 시 발생
     */
    private fun validatePassword(member: Member, password: String) {
        if (member.password != password) throw MmlBadRequestException("비밀번호가 일치하지 않습니다.")
    }

}