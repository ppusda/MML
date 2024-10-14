package com.bbgk.mml.domain

import com.bbgk.mml.domain.entity.Music
import com.bbgk.mml.domain.entity.Playlist
import com.bbgk.mml.domain.entity.PlaylistMusic
import com.bbgk.mml.domain.repository.MusicRepository
import com.bbgk.mml.domain.repository.PlaylistMusicRepository
import com.bbgk.mml.domain.repository.PlaylistRepository
import jakarta.annotation.PostConstruct
import lombok.extern.slf4j.Slf4j
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Slf4j
@Component
class DataInitializer(
        private val musicRepository: MusicRepository,
        private val playlistRepository: PlaylistRepository,
        private val playlistMusicRepository: PlaylistMusicRepository
) {

    val log: Logger = LoggerFactory.getLogger(DataInitializer::class.java)
    @PostConstruct
    fun initializeData() {
        log.info("MML project start. initialize Data.")

        val musics = mutableListOf(
                Music("Gang Gang Schiele", "혁오", "https://www.youtube.com/watch?v=Xjk3w7NcZAU"),
                Music("멋진헛간 Remix", "혁오", "https://www.youtube.com/watch?v=3DpL4UcCdWk"),
                Music("Happy", "Day6", "https://www.youtube.com/watch?v=VXp2dCXYrvQ")
        )

        val savedMusics = musicRepository.saveAll(musics)

        val playlist1 = Playlist("혁오 노래 모음")
        playlist1.addMusics(mutableListOf(savedMusics[0], savedMusics[1]))

        val playlist2 = Playlist("내가 자주 듣는 노래")
        playlist2.addMusics(savedMusics)

        val savedPlaylists = playlistRepository.saveAll(mutableListOf(playlist1, playlist2))

        val playlistMusic = mutableListOf(
                PlaylistMusic(savedPlaylists[0], savedMusics[0]),
                PlaylistMusic(savedPlaylists[0], savedMusics[1]),
                PlaylistMusic(savedPlaylists[1], savedMusics[0]),
                PlaylistMusic(savedPlaylists[1], savedMusics[1]),
                PlaylistMusic(savedPlaylists[1], savedMusics[2])
        )

        playlistMusicRepository.saveAll(playlistMusic)
    }

}