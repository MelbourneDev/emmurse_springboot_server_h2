package com.emmurse.models

import javax.persistence.*


@Entity
data class PersonalUserData(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @Column(name = "USERNAME", unique = true)
    val userName: String,
    val firstName: String,
    val lastName: String,
    @Column(unique = true)
    val email: String,
    val password: String,
    val subscriptionType: String? = null,
)