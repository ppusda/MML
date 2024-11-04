package com.bbgk.mml.musicList.service

import com.bbgk.mml.domain.entity.Member
import com.bbgk.mml.domain.entity.Music
import com.bbgk.mml.domain.entity.Playlist
import com.bbgk.mml.domain.entity.PlaylistMusic
import com.bbgk.mml.domain.exception.MmlBadRequestException
import com.bbgk.mml.musicList.repository.MusicListRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.any
import org.mockito.kotlin.doNothing
import org.mockito.kotlin.verify

@ExtendWith(MockitoExtension::class)
class PlaylistMusicServiceTest {

    @InjectMocks
    lateinit var playlistMusicService: PlaylistMusicService

    @Mock
    lateinit var musicListRepository: MusicListRepository

    val PLAYLIST_ID = 1L
    val MUSIC_ID = 1L
    val PLAYLIST_MUSIC_ID = 1L
    val MESSAGE = "존재하지 않는 플레이리스트 내 음악입니다."
    val OWNER = Member("testMember", "1234")

    @Test
    @DisplayName("재생목록 내 음악을 추가합니다.")
    fun testAddMusicInPlaylist() {
        // given
        val playlist = Playlist("name", OWNER)
        val music = Music("title", "artist", "url")

        `when`(musicListRepository.findPlayListById(any()))
                .thenReturn(playlist)

        `when`(musicListRepository.findMusicById(any()))
                .thenReturn(music)

        // when
        playlistMusicService.addMusicInPlaylist(PLAYLIST_ID, MUSIC_ID)

        // then
        verify(musicListRepository).findPlayListById(any())
        verify(musicListRepository).findMusicById(any())

        assertThat(playlist.playlistMusics.contains(PlaylistMusic(playlist, music)))
    }

    @Test
    @DisplayName("존재하지 않는 재생목록 내 음악을 추가할 때 에러가 발생합니다.")
    fun testAddMusicInNotExistPlaylist() {
        // given
        `when`(musicListRepository.findPlayListById(any()))
                .thenThrow(MmlBadRequestException("존재하지 않는 재생목록입니다."))

        // when
        assertThrows<MmlBadRequestException> {
            playlistMusicService.addMusicInPlaylist(PLAYLIST_ID, MUSIC_ID)
        }

        // then
        verify(musicListRepository).findPlayListById(any())
    }

    @Test
    @DisplayName("재생목록 내 존재하지 않는 음악을 추가할 때 에러가 발생합니다.")
    fun testAddNotExistMusicInPlaylist() {
        // given
        val playlist = Playlist("name", OWNER)

        `when`(musicListRepository.findPlayListById(any()))
                .thenReturn(playlist)
        `when`(musicListRepository.findMusicById(any()))
                .thenThrow(MmlBadRequestException("존재하지 않는 재생목록입니다."))

        // when
        assertThrows<MmlBadRequestException> {
            playlistMusicService.addMusicInPlaylist(PLAYLIST_ID, MUSIC_ID)
        }

        // then
        verify(musicListRepository).findPlayListById(any())
        verify(musicListRepository).findMusicById(any())
    }

    @Test
    @DisplayName("재생목록 내 음악을 제거합니다.")
    fun testDeleteMusicInPlaylist() {
        // given
        val playlist = Playlist("name", OWNER)
        val music = Music("title", "artist", "url")
        val playlistMusic = PlaylistMusic(playlist, music).apply { id = PLAYLIST_MUSIC_ID } // ID 설정

        `when`(musicListRepository.findByPlaylistIdAndMusicId(PLAYLIST_ID, MUSIC_ID, MESSAGE))
                .thenReturn(playlistMusic)
        doNothing().`when`(musicListRepository).deletePlaylistMusicById(PLAYLIST_MUSIC_ID)

        // when
        playlistMusicService.deleteMusicInPlaylist(PLAYLIST_ID, MUSIC_ID)

        // then
        verify(musicListRepository).deletePlaylistMusicById(PLAYLIST_MUSIC_ID)
    }



    @Test
    @DisplayName("존재하지 않는 재생목록 내 음악을 제거할 때 에러가 발생합니다.")
    fun testDeleteMusicInNotExistPlaylist() {
        // given
        val playlist = Playlist("name", OWNER)
        val music = Music("title", "artist", "url")
        val playlistMusic = PlaylistMusic(playlist, music).apply { id = PLAYLIST_MUSIC_ID } // ID 설정

        `when`(musicListRepository.findByPlaylistIdAndMusicId(PLAYLIST_ID, MUSIC_ID, MESSAGE))
                .thenReturn(playlistMusic)
        `when`(musicListRepository.deletePlaylistMusicById(PLAYLIST_MUSIC_ID))
                .thenThrow(MmlBadRequestException(MESSAGE))

        // when
        assertThrows<MmlBadRequestException> {
            playlistMusicService.deleteMusicInPlaylist(PLAYLIST_ID, MUSIC_ID)
        }

        // then
        verify(musicListRepository).deletePlaylistMusicById(PLAYLIST_MUSIC_ID)
    }

}