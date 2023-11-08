package com.emmurse.models


data class UserSettingsData(
    val userName: String?,
    val firstName: String,
    val lastName: String,
    val email: String?,
    val password: String,
    val businessName : String?,
    val businessEmail : String?
)
