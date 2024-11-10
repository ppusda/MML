package com.bbgk.mml.musicList.service

import com.bbgk.mml.BaseServiceTest
import com.bbgk.mml.domain.entity.Playlist
import com.bbgk.mml.domain.exception.MmlBadRequestException
import com.bbgk.mml.musicList.dto.PlaylistForm
import com.bbgk.mml.musicList.repository.MusicListRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.kotlin.*
import org.springframework.data.domain.PageImpl


class PlaylistServiceTest: BaseServiceTest() {

    @InjectMocks
    lateinit var playlistService: PlaylistService

    @Mock
    lateinit var musicListRepository: MusicListRepository

    @Test
    @DisplayName("재생목록 목록을 조회합니다.")
    fun testGetPlaylists() {
        // given
        val playlists = mutableListOf<Playlist>()
        for (i in 1..DATA_SIZE) {
            val playlist = Playlist("$i", member)
            playlists.add(playlist)
        }

        val page = PageImpl(playlists, pageable, DATA_SIZE.toLong())
        `when`(musicListRepository.getPlaylistsForPage(any()))
                .thenReturn(page)

        // when
        val playlistDTOs = playlistService.getPlaylists(0)

        // then
        verify(musicListRepository).getPlaylistsForPage(pageable)
        assertThat(playlistDTOs).hasSize(DATA_SIZE)
    }

    @Test
    @DisplayName("재생목록을 아이디로 조회합니다.")
    fun testFindPlaylistById() {
        // given
        val playlist = Playlist("name", member)

        `when`(musicListRepository.findPlayListById(any()))
            .thenReturn(playlist)

        // when
        val playlistDTO = playlistService.findPlaylistById(PLAYLIST_ID)

        // then
        verify(musicListRepository).findPlayListById(PLAYLIST_ID)
        assertThat(playlistDTO.name).isEqualTo(playlist.name)
    }

    @Test
    @DisplayName("존재하지 않는 재생목록을 아이디로 조회할 때 에러가 발생합니다.")
    fun testFindNotExistPlaylistById() {
        // given
        `when`(musicListRepository.findPlayListById(any()))
                .thenThrow(MmlBadRequestException(MESSAGE_NOT_EXIST_PLAYLIST))

        // when
        val exception = assertThrows<MmlBadRequestException> {
            playlistService.findPlaylistById(PLAYLIST_ID)
        }

        // then
        assertEquals(MESSAGE_NOT_EXIST_PLAYLIST, exception.message)
        verify(musicListRepository).findPlayListById(PLAYLIST_ID)
    }

    @Test
    @DisplayName("재생목록 정보를 저장합니다.")
    fun testSavePlaylist() {
        // given
        val playlistForm = PlaylistForm("title", member)
        val playlist = playlistForm.toEntity(member)
        val argumentCaptor = argumentCaptor<Playlist>()

        doNothing().`when`(musicListRepository).savePlaylist(any())

        // when
        playlistService.savePlaylist(member, playlistForm)

        // then
        verify(musicListRepository).savePlaylist(argumentCaptor.capture())

        // 캡처한 객체 검증
        val savedPlaylist = argumentCaptor.allValues[0]
        assertThat(savedPlaylist.name).isEqualTo(playlist.name)
    }

    @Test
    @DisplayName("재생목록 정보를 수정합니다.")
    fun testUpdatePlaylist() {
        // given
        val playlistForm = PlaylistForm("updatedName", member)
        val existPlaylist = Playlist("name", member)

        `when`(musicListRepository.findPlayListById(any()))
                .thenReturn(existPlaylist)

        // when
        playlistService.updatePlaylist(PLAYLIST_ID, playlistForm)

        // then
        assertThat(existPlaylist.name).isEqualTo(playlistForm.name) // 변경 사항 검증
    }

    @Test
    @DisplayName("재생목록 정보를 수정할 때 name이 비어있을 경우 기존의 값을 유지합니다.")
    fun testDoNotUpdatePlaylist() {
        // given
        val playlistForm = PlaylistForm("", member)
        val existPlaylist = Playlist("name", member)

        `when`(musicListRepository.findPlayListById(any()))
                .thenReturn(existPlaylist)

        // when
        playlistService.updatePlaylist(PLAYLIST_ID, playlistForm)

        // then
        assertThat(existPlaylist.name).isEqualTo(existPlaylist.name)
    }

    @Test
    @DisplayName("존재하지 않는 재생목록을 수정하려고 할 때 예외가 발생합니다.")
    fun testUpdateNotExistPlaylist() {
        // given
        val playlistForm = PlaylistForm("updatedName", member)

        `when`(musicListRepository.findPlayListById(any()))
                .thenThrow(MmlBadRequestException(MESSAGE_NOT_EXIST_PLAYLIST))

        // when
        val exception = assertThrows<MmlBadRequestException> {
            playlistService.updatePlaylist(PLAYLIST_ID, playlistForm)
        }

        // then
        assertEquals(MESSAGE_NOT_EXIST_PLAYLIST, exception.message)
        verify(musicListRepository).findPlayListById(PLAYLIST_ID)
    }


    @Test
    @DisplayName("재생목록을 삭제합니다.")
    fun testDeletePlaylist() {
        // given
        val playlist = Playlist("name", member)

        `when`(musicListRepository.findPlayListById(any()))
                .thenReturn(playlist)
        doNothing().`when`(musicListRepository).deletePlaylistById(any())

        // when
        playlistService.deletePlaylist(PLAYLIST_ID)

        // then
        verify(musicListRepository).deletePlaylistById(PLAYLIST_ID)
    }

    @Test
    @DisplayName("존재하지 않는 재생목록을 삭제하려고 할 때 예외가 발생합니다.")
    fun testDeleteNotExistMusic() {
        // given
        `when`(musicListRepository.findPlayListById(any()))
                .thenThrow(MmlBadRequestException(MESSAGE_NOT_EXIST_PLAYLIST))

        // when
        val exception = assertThrows<MmlBadRequestException> {
            playlistService.deletePlaylist(PLAYLIST_ID)
        }

        // then
        assertEquals(MESSAGE_NOT_EXIST_PLAYLIST, exception.message)
        verify(musicListRepository).findPlayListById(PLAYLIST_ID)
        verify(musicListRepository, never()).deletePlaylistById(any())
    }
}