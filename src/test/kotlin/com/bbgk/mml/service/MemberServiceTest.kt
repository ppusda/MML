package com.bbgk.mml.service

import com.bbgk.mml.domain.entity.Member
import com.bbgk.mml.domain.repository.MemberRepository
import com.bbgk.mml.member.service.MemberService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable


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
    }
}