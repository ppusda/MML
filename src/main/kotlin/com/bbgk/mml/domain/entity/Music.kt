package com.bbgk.mml.domain.entity

import jakarta.persistence.*

@Entity
class Music(
    title: String,
    artist: String,
    url: String
) {

    @Id @Column(name = "music_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    var title: String = title

    var artist: String = artist

    var url: String = url
}