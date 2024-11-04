package com.bbgk.mml.member.service

import com.bbgk.mml.domain.entity.Member
import com.bbgk.mml.domain.exception.MmlBadRequestException
import com.bbgk.mml.domain.repository.MemberRepository
import com.bbgk.mml.member.dto.MemberForm
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentCaptor
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.any
import org.mockito.kotlin.doNothing
import org.mockito.kotlin.never
import org.mockito.kotlin.verify
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import java.util.*


@ExtendWith(MockitoExtension::class)
class MemberServiceTest {

    @InjectMocks
    lateinit var memberService: MemberService

    @Mock
    lateinit var memberRepository: MemberRepository

    val MEMBER_ID = 1L
    val DATA_SIZE = 5

    val PAGE_NUMBER = 0
    val PAGE_SIZE = 5

    val pageable: Pageable = PageRequest.of(PAGE_NUMBER, PAGE_SIZE)

    @Test
    @DisplayName("회원 목록을 조회합니다.")
    fun testGetMembers() {
        // given
        val members = mutableListOf<Member>()
        for (i in 1..DATA_SIZE) {
            val member = Member("test_${i}", "$i")
            members.add(member)
        }

        val page = PageImpl(members, pageable, DATA_SIZE.toLong())
        `when`(memberRepository.findAll(pageable)).thenReturn(page)

        // when
        val memberDTOs = memberService.getMembers(PAGE_NUMBER)

        // then
        assertThat(memberDTOs).hasSize(DATA_SIZE)
        verify(memberRepository).findAll(pageable)
    }

    @Test
    @DisplayName("회원을 아이디로 조회합니다.")
    fun testFindMemberById() {
        // given
        val member = Member("test_1", "1")

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
        assertThrows<MmlBadRequestException> {
            memberService.findMemberById(MEMBER_ID)
        }

        // then
        verify(memberRepository).findById(MEMBER_ID)
    }

    @Test
    @DisplayName("회원 정보를 저장합니다.")
    fun testAddMember() {
        // given
        val memberForm = MemberForm("test_1", "1")
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
        val member = Member("test_1", "1")
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
        assertThrows<MmlBadRequestException> {
            memberService.deleteMember(MEMBER_ID)
        }

        // then
        verify(memberRepository).findById(MEMBER_ID) // 호출되었는지 확인
        verify(memberRepository, never()).deleteById(MEMBER_ID) // 오류로 인해 호출되지 않아야 함
    }

}