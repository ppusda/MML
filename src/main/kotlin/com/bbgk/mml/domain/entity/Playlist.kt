package com.bbgk.mml.domain.entity

import jakarta.persistence.*

@Entity
class Playlist(
    name: String,
) {

    @Id
    @Column(name = "playlist_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    var title: String = name
}
