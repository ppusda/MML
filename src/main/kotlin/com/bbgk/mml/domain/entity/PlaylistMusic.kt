package com.bbgk.mml.domain.entity

import jakarta.persistence.*

@Entity
class PlaylistMusic(
        playlist: Playlist,
        music: Music
) {

    @Id
    @Column(name = "playlist_music_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @ManyToOne(targetEntity = Playlist::class, fetch = FetchType.LAZY)
    @JoinColumn(name = "playlist_id")
    var playlist: Playlist = playlist

    @ManyToOne(targetEntity = Music::class, fetch = FetchType.LAZY)
    @JoinColumn(name = "music_id")
    var music: Music = music
}
