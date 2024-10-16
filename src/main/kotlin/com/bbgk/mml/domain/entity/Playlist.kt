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

    @OneToMany(mappedBy = "playlist", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    val playlistMusics: MutableList<PlaylistMusic> = mutableListOf()
    // CascadeType 사용 불가, JoinColumn 사용 불가, 조회 편의성을 위해 추가

    fun addMusics(playlistMusics: MutableList<PlaylistMusic>?) {
        if (playlistMusics != null) {
            this.playlistMusics.addAll(playlistMusics)
        }
    }
}
