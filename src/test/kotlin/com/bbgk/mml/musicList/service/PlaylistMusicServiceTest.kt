package com.bbgk.mml.musicList.service

import com.bbgk.mml.BaseServiceTest
import com.bbgk.mml.domain.entity.Music
import com.bbgk.mml.domain.entity.Playlist
import com.bbgk.mml.domain.entity.PlaylistMusic
import com.bbgk.mml.domain.exception.MmlBadRequestException
import com.bbgk.mml.domain.exception.MusicListExceptionMessage
import com.bbgk.mml.domain.exception.MusicListExceptionMessage.*
import com.bbgk.mml.musicList.repository.MusicListRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.kotlin.any
import org.mockito.kotlin.doNothing
import org.mockito.kotlin.never
import org.mockito.kotlin.verify

class PlaylistMusicServiceTest: BaseServiceTest() {

    @InjectMocks
    lateinit var playlistMusicService: PlaylistMusicService

    @Mock
    lateinit var musicListRepository: MusicListRepository

    @Test
    @DisplayName("재생목록 내 음악을 추가합니다.")
    fun testAddMusicInPlaylist() {
        // given
        val playlist = Playlist("name", member)
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
                .thenThrow(MmlBadRequestException(NOT_EXIST_PLAYLIST.message))

        // when
        val exception = assertThrows<MmlBadRequestException> {
            playlistMusicService.addMusicInPlaylist(PLAYLIST_ID, MUSIC_ID)
        }

        // then
        assertEquals(NOT_EXIST_PLAYLIST.message, exception.message)
        verify(musicListRepository).findPlayListById(any())
    }

    @Test
    @DisplayName("재생목록 내에 존재하지 않는 음악을 추가할 때 에러가 발생합니다.")
    fun testAddNotExistMusicInPlaylist() {
        // given
        val playlist = Playlist("name", member)

        `when`(musicListRepository.findPlayListById(any()))
                .thenReturn(playlist)
        `when`(musicListRepository.findMusicById(any()))
                .thenThrow(MmlBadRequestException(NOT_EXIST_MUSIC.message))

        // when
        val exception = assertThrows<MmlBadRequestException> {
            playlistMusicService.addMusicInPlaylist(PLAYLIST_ID, MUSIC_ID)
        }

        // then
        assertEquals(NOT_EXIST_MUSIC.message, exception.message)
        verify(musicListRepository).findPlayListById(any())
        verify(musicListRepository).findMusicById(any())
    }

    @Test
    @DisplayName("재생목록 내에 이미 존재하는 음악을 추가할 때 에러가 발생합니다.")
    fun testAddAlreadyExistMusicInPlaylist() {
        // given
        val playlist = Playlist("name", member)
        val music = Music("title", "artist", "url")
        val existingPlaylistMusic = PlaylistMusic(playlist, music)
        playlist.addMusics(mutableListOf(existingPlaylistMusic))

        `when`(musicListRepository.findPlayListById(PLAYLIST_ID)).thenReturn(playlist)
        `when`(musicListRepository.findMusicById(MUSIC_ID)).thenReturn(music)

        // when & then
        assertThrows<MmlBadRequestException> {
            playlistMusicService.addMusicInPlaylist(PLAYLIST_ID, MUSIC_ID)
        }.also { exception ->
            assertEquals(ALREADY_EXIST_PLAYLIST_MUSIC.message, exception.message)
        }
    }

    @Test
    @DisplayName("재생목록 내 음악을 제거합니다.")
    fun testDeleteMusicInPlaylist() {
        // given
        val playlist = Playlist("name", member)
        val music = Music("title", "artist", "url")
        val playlistMusic = PlaylistMusic(playlist, music)

        // 리플렉션을 사용해 id 필드에 값을 설정
        val idField = PlaylistMusic::class.java.getDeclaredField("id")
        idField.isAccessible = true
        idField.set(playlistMusic, PLAYLIST_MUSIC_ID)

        `when`(musicListRepository.findByPlaylistIdAndMusicId(any(), any()))
                .thenReturn(playlistMusic)
        doNothing().`when`(musicListRepository).deletePlaylistMusicById(any())

        // when
        playlistMusicService.deleteMusicInPlaylist(PLAYLIST_ID, MUSIC_ID)

        // then
        verify(musicListRepository).deletePlaylistMusicById(PLAYLIST_MUSIC_ID)
    }



    @Test
    @DisplayName("존재하지 않는 재생목록 내 음악을 제거할 때 에러가 발생합니다.")
    fun testDeleteMusicInNotExistPlaylist() {
        // given
        `when`(musicListRepository.findByPlaylistIdAndMusicId(any(), any()))
                .thenThrow(MmlBadRequestException(NOT_EXIST_PLAYLIST_MUSIC.message))

        // when
        val exception = assertThrows<MmlBadRequestException> {
            playlistMusicService.deleteMusicInPlaylist(PLAYLIST_ID, MUSIC_ID)
        }

        // then
        assertEquals(NOT_EXIST_PLAYLIST_MUSIC.message, exception.message)
        verify(musicListRepository, never()).deletePlaylistMusicById(PLAYLIST_MUSIC_ID)
    }

}