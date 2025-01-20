package com.bbgk.mml.musicList.repository

import com.bbgk.mml.BaseServiceTest
import com.bbgk.mml.domain.entity.Music
import com.bbgk.mml.domain.entity.Playlist
import com.bbgk.mml.domain.entity.PlaylistMusic
import com.bbgk.mml.domain.exception.MmlBadRequestException
import com.bbgk.mml.domain.exception.MusicListExceptionMessage
import com.bbgk.mml.domain.exception.MusicListExceptionMessage.*
import com.bbgk.mml.domain.repository.MusicRepository
import com.bbgk.mml.domain.repository.PlaylistMusicRepository
import com.bbgk.mml.domain.repository.PlaylistRepository
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.kotlin.any
import org.mockito.kotlin.doNothing
import org.mockito.kotlin.verify
import org.springframework.data.domain.PageImpl
import java.util.*

class MusicListRepositoryTest: BaseServiceTest() {

    @InjectMocks
    lateinit var musicListRepository: MusicListRepository

    @Mock
    lateinit var musicRepository: MusicRepository

    @Mock
    lateinit var playlistRepository: PlaylistRepository

    @Mock
    lateinit var playlistMusicRepository: PlaylistMusicRepository

    @Test
    @DisplayName("음악 목록을 페이지로 조회한다.")
    fun testFindAllMusics() {
        // given
        val musics: List<Music> = mutableListOf(
                Music("title1", "artist1", "url1"),
                Music("title2", "artist2", "url2"),
                Music("title3", "artist3", "url3")
        )

        val size = musics.size
        val page = PageImpl(musics, pageable, size.toLong())

        `when`(musicRepository.findAll(pageable))
                .thenReturn(page)

        // when
        val musicPage = musicListRepository.getMusicsForPage(pageable)

        // then
        verify(musicRepository).findAll(pageable)
        Assertions.assertThat(musicPage).hasSize(size)
    }

    @Test
    @DisplayName("음악 정보를 저장합니다.")
    fun testSaveMusic() {
        // given
        val music = Music("title1", "artist1", "url1")

        `when`(musicRepository.save(any())).thenReturn(music)

        // when
        val savedMusic = musicListRepository.saveMusic(music)

        // then
        verify(musicRepository).save(any())

        Assertions.assertThat(savedMusic.title).isEqualTo(music.title)
        Assertions.assertThat(savedMusic.artist).isEqualTo(music.artist)
        Assertions.assertThat(savedMusic.url).isEqualTo(music.url)
    }

    @Test
    @DisplayName("음악을 삭제합니다.")
    fun testDeleteMusic() {
        // given
        doNothing().`when`(musicRepository).deleteById(any())

        // when
        musicListRepository.deleteMusicById(MUSIC_ID)

        // then
        verify(musicRepository).deleteById(MUSIC_ID)
    }

    @Test
    @DisplayName("음악을 아이디로 검색합니다.")
    fun testFindMusicById() {
        // given
        val music = Optional.of(Music("title1", "artist1", "url1"))

        `when`(musicRepository.findById(any()))
                .thenReturn(music)

        // when
        val findMusic = musicListRepository.findMusicById(MUSIC_ID)

        // then
        verify(musicRepository).findById(MUSIC_ID)


        Assertions.assertThat(findMusic.title).isEqualTo(music.get().title)
        Assertions.assertThat(findMusic.artist).isEqualTo(music.get().artist)
        Assertions.assertThat(findMusic.url).isEqualTo(music.get().url)
    }

    @Test
    @DisplayName("음악을 키워드로 검색합니다.")
    fun testFindMusicsByKeyword() {
        // given
        val title = "title"

        val musics = listOf(
            Music("title1", "artist1", "url1"),
            Music("title2", "artist2", "url2"),
            Music("title3", "artist3", "url3")
        )

        `when`(musicRepository.findMusicsByKeyword(any()))
            .thenReturn(musics)

        // when
        val findMusics = musicListRepository.searchMusics(title)

        // then
        verify(musicRepository).findMusicsByKeyword(any())

        for (findMusic in findMusics) {
            Assertions.assertThat(findMusic.title).contains(title)
        }
    }

    @Test
    @DisplayName("존재하지 않는 음악을 아이디로 검색하여 에러가 발생합니다.")
    fun testFindNotExistMusicById() {
        // given
        `when`(musicRepository.findById(MUSIC_ID))
                .thenReturn(Optional.empty())

        // when
        val exception = assertThrows<MmlBadRequestException> {
            musicListRepository.findMusicById(MUSIC_ID)
        }

        // then
        assertEquals(NOT_EXIST_MUSIC.message, exception.message)
        verify(musicRepository).findById(MUSIC_ID)
    }

    @Test
    @DisplayName("재생목록 목록을 페이지로 조회한다.")
    fun testFindAllPlaylists() {
        // given
        val playlists: List<Playlist> = mutableListOf(
                Playlist("name1", member),
                Playlist("name2", member),
                Playlist("name3", member)
        )

        val size = playlists.size
        val page = PageImpl(playlists, pageable, size.toLong())

        `when`(playlistRepository.findAll(pageable))
                .thenReturn(page)

        // when
        val playlistPage = musicListRepository.getPlaylistsForPage(pageable)

        // then
        verify(playlistRepository).findAll(pageable)
        Assertions.assertThat(playlistPage).hasSize(size)
    }

    @Test
    @DisplayName("재생목록 정보를 저장합니다.")
    fun testSavePlaylist() {
        // given
        val playlist = Playlist("name1", member)

        `when`(playlistRepository.save(any()))
            .thenReturn(playlist)

        // when
        val savedMusic = musicListRepository.savePlaylist(playlist)

        // then
        verify(playlistRepository).save(any())
        Assertions.assertThat(savedMusic.name).isEqualTo(playlist.name)
    }

    @Test
    @DisplayName("재생목록을 삭제합니다.")
    fun testDeletePlaylist() {
        // given
        doNothing().`when`(playlistRepository).deleteById(any())

        // when
        musicListRepository.deletePlaylistById(PLAYLIST_ID)

        // then
        verify(playlistRepository).deleteById(PLAYLIST_ID)
    }

    @Test
    @DisplayName("재생목록을 아이디로 검색합니다.")
    fun testFindPlaylistById() {
        // given
        val playlist = Optional.of(Playlist("name1", member))

        `when`(playlistRepository.findById(any()))
                .thenReturn(playlist)

        // when
        val findPlaylist = musicListRepository.findPlayListById(PLAYLIST_ID)

        // then
        verify(playlistRepository).findById(PLAYLIST_ID)


        Assertions.assertThat(findPlaylist.name).isEqualTo(playlist.get().name)
    }

    @Test
    @DisplayName("존재하지 않는 재생목록을 아이디로 검색하여 에러가 발생합니다.")
    fun testFindNotExistPlaylistById() {
        // given
        `when`(playlistRepository.findById(any()))
                .thenReturn(Optional.empty())

        // when
        val exception = assertThrows<MmlBadRequestException> {
            musicListRepository.findPlayListById(PLAYLIST_ID)
        }

        // then
        assertEquals(NOT_EXIST_PLAYLIST.message, exception.message)
        verify(playlistRepository).findById(PLAYLIST_ID)
    }

    @Test
    @DisplayName("재생목록 내 음악을 삭제합니다.")
    fun testDeletePlaylistMusic() {
        // given
        doNothing().`when`(playlistMusicRepository).deleteById(any())

        // when
        musicListRepository.deletePlaylistMusicById(PLAYLIST_MUSIC_ID)

        // then
        verify(playlistMusicRepository).deleteById(PLAYLIST_MUSIC_ID)
    }

    @Test
    @DisplayName("재생목록 내 음악을 아이디로 검색합니다.")
    fun testFindPlaylistMusicById() {
        // given
        val music = Music("title1", "artist1", "url1")
        val playlist = Playlist("name1", member)
        val playlistMusic = Optional.of(PlaylistMusic(playlist, music))

        `when`(playlistMusicRepository.findByPlaylistIdAndMusicId(any(), any()))
                .thenReturn(playlistMusic)

        // when
        val findPlaylistMusic = musicListRepository.findByPlaylistIdAndMusicId(PLAYLIST_ID, MUSIC_ID)

        // then
        verify(playlistMusicRepository).findByPlaylistIdAndMusicId(PLAYLIST_ID, MUSIC_ID)


        Assertions.assertThat(findPlaylistMusic.music).isEqualTo(playlistMusic.get().music)
        Assertions.assertThat(findPlaylistMusic.playlist).isEqualTo(playlistMusic.get().playlist)
    }

    @Test
    @DisplayName("재생목록 내 음악을 아이디로 검색했을 때 아무것도 검색되지 않습니다.")
    fun testFindNotExistPlaylistMusicById() {
        // given
        `when`(playlistMusicRepository.findByPlaylistIdAndMusicId(any(), any()))
                .thenReturn(Optional.empty())

        // when
        val exception = assertThrows<MmlBadRequestException> {
            musicListRepository.findByPlaylistIdAndMusicId(PLAYLIST_ID, MUSIC_ID)
        }

        // then
        assertEquals(NOT_EXIST_PLAYLIST_MUSIC.message, exception.message)
        verify(playlistMusicRepository).findByPlaylistIdAndMusicId(PLAYLIST_ID, MUSIC_ID)
    }

}

/**
 * // 진짜 DB에 접근하는 Repository를 테스트하는게 아니라면 사실상 퍼사드 패턴을 적용한 musicListRepository도 비즈니스 로직을 처리하기 위한 서비스에 가깝다는 것을 알게되었다.
 * // Repository(Interface)는 JaCoCo의 100% 대상이 아닐 뿐더러 Spring Data JPA가 제공하는 내장된 메서드들은 이미 테스트가 검증 된 메서드이기 때문에 테스트가 불필요하다.
 *
 * // 커스텀한 메서드를 추가한 경우에는 테스트가 필요하기에 PlaylistMusicRepository의 findByPlaylistIdAndMusicId는 DataJpaTest로 검증하였음.
 *
 * // 아래는 테스트 코드를 작성한 예제
 * // https://github.com/MangKyu/ATDD-Membership/blob/master/src/test/java/com/mang/atdd/membership/app/membership/controller/MembershipControllerTest.java
 */
