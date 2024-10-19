package com.bbgk.mml.domain.entity

import jakarta.persistence.*

@Entity
class Playlist(
        name: String,
        user: User
) {

    @Id
    @Column(name = "playlist_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    var name: String = name

    @ManyToOne(targetEntity = User::class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: User = user

    @OneToMany(mappedBy = "playlist", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    val playlistMusics: MutableList<PlaylistMusic> = mutableListOf()

    fun addMusics(playlistMusics: MutableList<PlaylistMusic>?) {
        if (playlistMusics != null) {
            this.playlistMusics.addAll(playlistMusics)
        }
    }
}
