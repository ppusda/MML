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

    @ManyToMany(targetEntity = Music::class, fetch = FetchType.LAZY)
    var musics: MutableList<Music> = mutableListOf()
    // CascadeType 사용 불가, JoinColumn 사용 불가, 조회 편의성을 위해 추가

    fun addMusics(musics: MutableList<Music>?) {
        if (musics != null) {
            this.musics.addAll(musics)
        }
    }
}
