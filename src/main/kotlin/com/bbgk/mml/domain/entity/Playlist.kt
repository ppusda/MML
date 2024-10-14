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

    var name: String = name

    @OneToMany(targetEntity = Music::class,
            fetch = FetchType.LAZY,
            cascade = [CascadeType.ALL])
    @JoinColumn(name = "music_id")
    var musics: MutableList<Music> = mutableListOf()

    fun addMusics(musics: MutableList<Music>?) {
        if (musics != null) {
            this.musics.addAll(musics)
        }
    }
}
