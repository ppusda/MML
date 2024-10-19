package com.bbgk.mml.domain.repository

import com.bbgk.mml.domain.entity.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<Member, Long> {
}