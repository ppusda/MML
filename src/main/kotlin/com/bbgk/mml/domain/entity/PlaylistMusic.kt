package com.bbgk.mml.domain.entity

import jakarta.persistence.*

@Entity
class PlaylistMusic {

    @Id
    @Column(name = "playlist_music_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @ManyToOne
    @JoinColumn(name = "playlist_id")
    val playlist: Playlist? = null

    @ManyToOne
    @JoinColumn(name = "album_id")
    val music: Music? = null
}
