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
        private set

    var title: String = title
        private set

    var artist: String = artist
        private set

    var url: String = url
        private set

    fun update(title: String, artist: String, url: String) {
        this.title = title
        this.artist = artist
        this.url = url
    }
}