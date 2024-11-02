package com.bbgk.mml.service

import com.bbgk.mml.domain.entity.Member
import com.bbgk.mml.domain.repository.MemberRepository
import com.bbgk.mml.member.dto.MemberForm
import com.bbgk.mml.member.service.MemberService
import org.aspectj.lang.annotation.Before
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentCaptor
import org.mockito.ArgumentMatchers.any
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
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
        Mockito.`when`(memberRepository.findAll(pageable)).thenReturn(page)

        // when
        val memberDTOs = memberService.getMembers(PAGE_NUMBER)

        // then
        assertThat(memberDTOs).hasSize(DATA_SIZE)
        Mockito.verify(memberRepository).findAll(pageable)
    }

    @Test
    @DisplayName("회원을 아이디로 조회합니다.")
    fun testFindMemberById() {
        // given
        val memberId = 1L
        val member = Member("test_1", "1")

        Mockito.`when`(memberRepository.findById(memberId))
                .thenReturn(Optional.of(member))

        // when
        val findMember = memberService.findMemberById(memberId)

        // then
        Mockito.verify(memberRepository).findById(memberId)
        assertThat(findMember.email).isEqualTo(member.email)
        assertThat(findMember.id).isEqualTo(member.id)
    }

    @Test
    @DisplayName("회원 정보를 저장합니다.")
    fun testAddMember() {
        // given
        val memberForm = MemberForm("test_1", "1")
        val member = memberForm.toEntity()
        Mockito.`when`(memberRepository.save(any(Member::class.java))).thenReturn(member)

        // when
        memberService.saveMember(memberForm)

        // then
        val argumentCaptor = ArgumentCaptor.forClass(Member::class.java)
        Mockito.verify(memberRepository).save(argumentCaptor.capture()) // 실제로 저장된 객체를 캡처

        // 캡처한 객체 검증
        val savedMember = argumentCaptor.value
        assertThat(savedMember.email).isEqualTo(member.email)
        assertThat(savedMember.id).isEqualTo(member.id)
    }


    @Test
    @DisplayName("회원 정보를 삭제합니다.")
    fun testDeleteMember() {
        // given
        val memberId = 1L
        val member = Member("test_1", "1")
        Mockito.`when`(memberRepository.findById(memberId))
                .thenReturn(Optional.of(member)) // findById로 검증을 진행하기에 Mock 설정
        Mockito.doNothing().`when`(memberRepository).deleteById(memberId)

        // when
        memberService.deleteMember(memberId)

        // then
        Mockito.verify(memberRepository).deleteById(memberId) // deleteById 메서드 호출 확인
    }

}