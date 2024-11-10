package com.bbgk.mml.domain.entity

import jakarta.persistence.*

@Entity
class Playlist(
    name: String,
    member: Member
) {

    @Id
    @Column(name = "playlist_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
        private set

    var name: String = name
        private set

    @ManyToOne(targetEntity = Member::class, fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    val member: Member = member

    @OneToMany(mappedBy = "playlist", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    val playlistMusics: MutableList<PlaylistMusic> = mutableListOf()

    fun addMusics(playlistMusics: MutableList<PlaylistMusic>?) {
        if (playlistMusics != null) {
            this.playlistMusics.addAll(playlistMusics)
        }
    }

    fun update(name: String) {
        if (name.isNotBlank()) {
            this.name = name
        }
    }
}
