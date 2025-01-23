package com.bbgk.mml

import com.bbgk.mml.domain.entity.Member
import com.bbgk.mml.domain.entity.Music
import com.bbgk.mml.domain.entity.Playlist
import com.bbgk.mml.domain.util.PageUtils
import com.bbgk.mml.member.dto.MemberForm
import com.bbgk.mml.musicList.dto.MusicForm
import com.bbgk.mml.musicList.dto.PlaylistForm
import org.springframework.data.domain.Pageable

abstract class TestConstants {
    companion object {
        // IDs
        const val MEMBER_ID = 1L
        const val PLAYLIST_ID = 1L
        const val MUSIC_ID = 1L
        const val PLAYLIST_MUSIC_ID = 1L

        // Member
        const val EMAIL = "test"
        const val PASSWORD = "test"

        // Page Config
        const val PAGE = 0
        const val DATA_SIZE = 3
        val pageable: Pageable = PageUtils.getDefaultPageable(PAGE)

        // Test Data
        val member = createMember()
        val memberList = createMembers()
        val memberForm = createMemberForm()

        val music = createMusic()
        val musicList = createMusics()
        val musicForm = createMusicForm()

        val playlist = createPlaylist()
        val playlistList = createPlaylists()
        val playlistForm = createPlaylistForm()

        private fun createMember(
            email: String = EMAIL,
            password: String = PASSWORD
        ) = Member(email, password)

        private fun createMembers() = listOf(
            Member("test1", "test1"),
            Member("test2", "test2"),
            Member("test3", "test3")
        )

        private fun createMemberForm(
            email: String = EMAIL,
            password: String = PASSWORD
        ) = MemberForm(email, password)

        private fun createMusic() = Music("title", "artist", "url")

        private fun createMusics() = listOf(
            Music("title1", "artist1", "url1"),
            Music("title2", "artist2", "url2"),
            Music("title3", "artist3", "url3")
        )

        private fun createMusicForm(
            title: String = "music",
            artist: String = "artist",
            url: String = "url"
        ) = MusicForm(title, artist, url)

        private fun createPlaylist(
            name: String = "name",
            member: Member = createMember()
        ) = Playlist(name, member)

        private fun createPlaylists() = listOf(
            Playlist("name1", member),
            Playlist("name2", member),
            Playlist("name3", member)
        )

        private fun createPlaylistForm() = PlaylistForm("name")

    }
}