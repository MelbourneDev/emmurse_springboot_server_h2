package com.emmurse.models

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id


@Entity
data class BusinessUserData(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var businessName: String,
    var firstName: String,
    var lastName: String,
    var businessEmail: String,
    var password: String,
    var subscriptionType: String? = null,
)