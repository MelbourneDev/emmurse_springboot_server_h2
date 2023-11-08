package com.emmurse.models
import com.emmurse.models.UserSettingsData


fun BusinessUserData.toUserSettingsData() = UserSettingsData(
    userName = null,
    businessName = this.businessName,
    firstName = this.firstName,
    lastName = this.lastName,
    email = null,
    businessEmail = this.businessEmail,
    password = this.password,

)

fun PersonalUserData.toUserSettingsData() = UserSettingsData(
    userName = this.userName,
    businessName = null,
    firstName = this.firstName,
    lastName = this.lastName,
    email = this.email,
    businessEmail = null,
    password = this.password
)

