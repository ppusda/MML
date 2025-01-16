package com.bbgk.mml.member.service

import com.bbgk.mml.BaseServiceTest
import com.bbgk.mml.domain.entity.Member
import com.bbgk.mml.domain.exception.MemberExceptionMessage.NOT_EXIST_MEMBER
import com.bbgk.mml.domain.exception.MemberExceptionMessage.NOT_VALIDATE_PASSWORD
import com.bbgk.mml.domain.exception.MmlBadRequestException
import com.bbgk.mml.domain.repository.MemberRepository
import com.bbgk.mml.domain.util.PageUtils.Companion.PAGE_SIZE
import com.bbgk.mml.member.dto.MemberForm
import com.bbgk.mml.member.dto.MemberLoginResponse
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.ArgumentCaptor
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.kotlin.any
import org.mockito.kotlin.doNothing
import org.mockito.kotlin.never
import org.mockito.kotlin.verify
import org.springframework.data.domain.PageImpl
import java.util.*
import kotlin.test.assertEquals


class MemberServiceTest: BaseServiceTest() {

    @InjectMocks
    lateinit var memberService: MemberService

    @Mock
    lateinit var memberRepository: MemberRepository

    @Test
    @DisplayName("회원 목록을 조회합니다.")
    fun testGetMembers() {
        // given
        val members = mutableListOf<Member>()
        for (i in 1..DATA_SIZE) {
            val member = Member("test_${i}", "$i")
            members.add(member)
        }

        val page = PageImpl(members, pageable, PAGE_SIZE.toLong())
        `when`(memberRepository.findAll(pageable)).thenReturn(page)

        // when
        val memberDTOs = memberService.getMembers(PAGE)

        // then
        assertThat(memberDTOs).hasSize(DATA_SIZE)
        verify(memberRepository).findAll(pageable)
    }

    @Test
    @DisplayName("회원을 아이디로 조회합니다.")
    fun testFindMemberById() {
        // given
        `when`(memberRepository.findById(any()))
                .thenReturn(Optional.of(member))

        // when
        val findMember = memberService.findMemberById(MEMBER_ID)

        // then
        verify(memberRepository).findById(MEMBER_ID)
        assertThat(findMember.email).isEqualTo(member.email)
        assertThat(findMember.id).isEqualTo(member.id)
    }

    @Test
    @DisplayName("존재하지 않는 회원을 아이디로 조회했을 때 예외가 발생합니다.")
    fun testFindNotExistMemberById() {
        // given
        `when`(memberRepository.findById(any()))
                .thenReturn(Optional.empty())

        // when
        val exception = assertThrows<MmlBadRequestException> {
            memberService.findMemberById(MEMBER_ID)
        }

        // then
        assertEquals(NOT_EXIST_MEMBER.message, exception.message)
        verify(memberRepository).findById(MEMBER_ID)
    }

    @Test
    @DisplayName("회원 정보를 저장합니다.")
    fun testAddMember() {
        // given
        val memberForm = MemberForm("email1", "password1")
        val member = memberForm.toEntity()
        `when`(memberRepository.save(any())).thenReturn(member)

        // when
        memberService.saveMember(memberForm)

        // then
        val argumentCaptor = ArgumentCaptor.forClass(Member::class.java)
        verify(memberRepository).save(argumentCaptor.capture()) // 실제로 저장된 객체를 캡처

        // 캡처한 객체 검증
        val savedMember = argumentCaptor.value
        assertThat(savedMember.email).isEqualTo(member.email)
        assertThat(savedMember.id).isEqualTo(member.id)
    }


    @Test
    @DisplayName("회원 정보를 삭제합니다.")
    fun testDeleteMember() {
        // given
        `when`(memberRepository.findById(any()))
                .thenReturn(Optional.of(member)) // findById로 검증을 진행하기에 Mock 설정
        doNothing().`when`(memberRepository).deleteById(MEMBER_ID)

        // when
        memberService.deleteMember(MEMBER_ID)

        // then
        verify(memberRepository).deleteById(MEMBER_ID) // deleteById 메서드 호출 확인
    }

    @Test
    @DisplayName("존재하지 않는 회원을 삭제하려고 할 때 예외가 발생합니다.")
    fun testDeleteNotExistMember() {
        // given
        `when`(memberRepository.findById(any()))
                .thenReturn(Optional.empty()) // findById로 검증을 진행하기에 Mock 설정

        // when
        val exception = assertThrows<MmlBadRequestException> {
            memberService.deleteMember(MEMBER_ID)
        }

        // then
        assertEquals(NOT_EXIST_MEMBER.message, exception.message)
        verify(memberRepository).findById(MEMBER_ID) // 호출되었는지 확인
        verify(memberRepository, never()).deleteById(MEMBER_ID) // 오류로 인해 호출되지 않아야 함
    }

    @Test
    fun `회원 정보를 이메일로 검색합니다`() {
        // given
        `when`(memberRepository.findByEmail(any()))
            .thenReturn(Optional.of(member))

        // when
        memberService.findMemberByEmail(EMAIL)

        // then
        verify(memberRepository).findByEmail(any())
        assertThat(memberForm.email).isEqualTo(member.email)
    }

    @Test
    fun `존재하지 않는 회원 정보를 이메일로 검색할 때 예외가 발생합니다`() {
        // given
        `when`(memberRepository.findByEmail(any()))
            .thenReturn(Optional.empty())

        // when
        val exception = assertThrows<MmlBadRequestException> {
            memberService.findMemberByEmail("wrong email")
        }

        // then
        assertEquals(NOT_EXIST_MEMBER.message, exception.message)
    }

    @Test
    fun `회원 정보를 얻기 위해 로그인 합니다`() {
        // given
        `when`(memberRepository.findByEmail(any()))
            .thenReturn(Optional.of(member))

        // when
        val loginResponse: MemberLoginResponse = memberService.loginMember(memberForm)

        // then
        verify(memberRepository).findByEmail(any())
        assertThat(loginResponse.email).isEqualTo(EMAIL)
    }

    @Test
    fun `회원 정보를 얻기 위해 로그인 하지만 회원이 없어서 새로 생성됩니다`() {
        // given
        `when`(memberRepository.findByEmail(any()))
            .thenReturn(Optional.empty()) // 회원 정보가 없는 상태를 모의

        `when`(memberRepository.save(any()))
            .thenReturn(member) // 새로 저장되는 회원을 모의

        // when
        val loginResponse: MemberLoginResponse = memberService.loginMember(MemberForm(EMAIL, PASSWORD))

        // then
        verify(memberRepository).findByEmail(any()) // 이메일 조회 호출 검증
        verify(memberRepository).save(any()) // 새 회원 저장 호출 검증
        assertThat(loginResponse.email).isEqualTo(EMAIL) // 반환된 회원 정보 확인
    }

    @Test
    fun `회원 정보를 얻기 위해 로그인 하지만 비밀번호가 일치하지 않습니다`() {
        // given
        `when`(memberRepository.findByEmail(any()))
            .thenReturn(Optional.of(member))

        // when
        val exception = assertThrows<MmlBadRequestException> {
            memberService.loginMember(MemberForm(EMAIL, "WRONG PASSWORD"))
        }

        // then
        assertEquals(NOT_VALIDATE_PASSWORD.message, exception.message)
    }

}