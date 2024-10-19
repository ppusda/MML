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

    var email: String = email

    var password: String = password

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    val playlists: MutableList<Playlist> = mutableListOf()

}