package com.bbgk.mml.service

import com.bbgk.mml.domain.entity.Member
import com.bbgk.mml.domain.repository.MemberRepository
import com.bbgk.mml.domain.repository.MusicRepository
import com.bbgk.mml.member.service.MemberService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension


@ExtendWith(MockitoExtension::class)
class MemberServiceTest {

    @InjectMocks
    lateinit var memberService: MemberService

    @Mock
    lateinit var memberRepository: MemberRepository

    val DATA_SIZE = 5

    @Test
    fun testGetMembers() {
        // given
        val members = mutableListOf<Member>()
        for (i in 1..DATA_SIZE) {
            val member = Member("test_${i}", "$i")
            members.add(member)
        }

        Mockito.`when`(memberRepository.findAll())
                .thenReturn(members)

        // when
        val memberDTOs = memberService.getMembers()

        // then
        assertThat(memberDTOs).hasSize(DATA_SIZE)
    }
}