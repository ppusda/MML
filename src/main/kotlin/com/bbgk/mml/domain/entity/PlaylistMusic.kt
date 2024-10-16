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

    @ManyToOne
    @JoinColumn(name = "playlist_id")
    var playlist: Playlist = playlist

    @ManyToOne
    @JoinColumn(name = "music_id")
    var music: Music = music
}
