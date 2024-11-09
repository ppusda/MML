package com.bbgk.mml.domain.entity

import jakarta.persistence.*

@Entity
class Member(
    email: String,
    password: String
) {

    @Id @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
        private set

    var email: String = email
        private set

    var password: String = password
        private set

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    val playlists: MutableList<Playlist> = mutableListOf()

}