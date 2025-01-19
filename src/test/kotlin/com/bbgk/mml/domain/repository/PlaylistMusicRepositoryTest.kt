package com.bbgk.mml.domain.repository

import com.bbgk.mml.BaseRepositoryTest
import com.bbgk.mml.domain.entity.Music
import com.bbgk.mml.domain.entity.Playlist
import com.bbgk.mml.domain.entity.PlaylistMusic
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class PlaylistMusicRepositoryTest(
        @Autowired val musicRepository: MusicRepository,
        @Autowired val playlistRepository: PlaylistRepository,
        @Autowired val playlistMusicRepository: PlaylistMusicRepository,
        @Autowired val memberRepository: MemberRepository
): BaseRepositoryTest() {

    @BeforeAll
    fun beforeAll() {
        val savedMusics = musicRepository.saveAll(musics)
        val savedUser = memberRepository.save(member)

        val playlist1 = Playlist("playlist1", savedUser)
        val playlist2 = Playlist("playlist2", savedUser)
        val savedPlaylists = playlistRepository.saveAll(mutableListOf(playlist1, playlist2))

        playlist1.addMusics(mutableListOf(
                PlaylistMusic(savedPlaylists[0], savedMusics[0]),
                PlaylistMusic(savedPlaylists[0], savedMusics[1])
        ))
        playlist2.addMusics(mutableListOf(
                PlaylistMusic(savedPlaylists[1], savedMusics[0]),
        ))

        val playlistMusic = mutableListOf(
                PlaylistMusic(savedPlaylists[0], savedMusics[0]),
                PlaylistMusic(savedPlaylists[0], savedMusics[1]),
                PlaylistMusic(savedPlaylists[1], savedMusics[0]),
        )

        playlistMusicRepository.saveAll(playlistMusic)
    }

    @Test
    @DisplayName("재생목록 아이디와 음악 아이디로 연관관계 테이블 내 아이디를 검색한다.")
    fun testFindByPlaylistIdAndMusicId() {
        // given - beforeAll
        // when
        val playlistMusic = playlistMusicRepository.findByPlaylistIdAndMusicId(PLAYLIST_ID, MUSIC_ID)

        // then
        Assertions.assertThat(playlistMusic).isPresent
        Assertions.assertThat(playlistMusic.get().id).isEqualTo(PLAYLIST_MUSIC_ID)
        Assertions.assertThat(playlistMusic.get().playlist.id).isEqualTo(PLAYLIST_ID)
        Assertions.assertThat(playlistMusic.get().music.id).isEqualTo(MUSIC_ID)
    }

}