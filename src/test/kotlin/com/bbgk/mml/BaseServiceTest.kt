package com.bbgk.mml

import com.bbgk.mml.domain.entity.Member
import com.bbgk.mml.member.dto.MemberForm
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable

@ExtendWith(MockitoExtension::class)
abstract class BaseServiceTest {

    // Id config
    protected val MEMBER_ID = 1L
    protected val PLAYLIST_ID = 1L
    protected val MUSIC_ID = 1L
    protected val PLAYLIST_MUSIC_ID = 1L

    // Page Config
    protected val PAGE_NUMBER = 0
    private val PAGE_SIZE = 4
    protected val DATA_SIZE = 4
    protected val pageable: Pageable = PageRequest.of(PAGE_NUMBER, PAGE_SIZE)

    // Message Config
    protected val MESSAGE_NOT_EXIST_MEMBER = "존재하지 않는 사용자입니다."
    protected val MESSAGE_NOT_EXIST_PLAYLIST_MUSIC = "존재하지 않는 재생목록 내 음악입니다."
    protected val MESSAGE_NOT_EXIST_MUSIC = "존재하지 않는 음악입니다."
    protected val MESSAGE_NOT_EXIST_PLAYLIST = "존재하지 않는 플레이리스트입니다."
    protected val MESSAGE_NOT_VALIDATE_PASSWORD = "비밀번호가 일치하지 않습니다."

    // Member Config
    protected val EMAIL = "test@test.com"
    protected val PASSWORD = "test"

    protected val member = Member(EMAIL, PASSWORD)
    protected val memberForm = MemberForm(EMAIL, PASSWORD)
}