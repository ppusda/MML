package com.bbgk.mml

import com.bbgk.mml.domain.entity.Member
import com.bbgk.mml.domain.entity.Music
import com.bbgk.mml.domain.entity.Playlist
import com.bbgk.mml.domain.util.PageUtils
import com.bbgk.mml.member.dto.MemberForm
import com.bbgk.mml.musicList.dto.MusicForm
import com.bbgk.mml.musicList.dto.PlaylistForm
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.data.domain.Pageable

@ExtendWith(MockitoExtension::class)
abstract class BaseServiceTest {

    // Data config
    protected val MEMBER_ID = 1L
    protected val EMAIL = "test"
    protected val PASSWORD = "test"

    protected val member = Member(EMAIL, PASSWORD)
    protected val memberForm = MemberForm(EMAIL, PASSWORD)

    protected val PLAYLIST_ID = 1L
    val playlistForm = PlaylistForm("title")
    val playlist = Playlist("name", member)

    protected val MUSIC_ID = 1L
    protected val musicForm = MusicForm("title", "artist", "url")
    protected val music = musicForm.toEntity()

    protected val PLAYLIST_MUSIC_ID = 1L

    // Page Config
    protected val DATA_SIZE = 3
    protected val PAGE = 0
    protected val pageable: Pageable = PageUtils.getDefaultPageable(PAGE)
}