package com.bbgk.mml.domain.entity

import jakarta.persistence.*

@Entity
class User(
    email: String,
    password: String
) {

    @Id @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    var email: String = email

    var password: String = password

}