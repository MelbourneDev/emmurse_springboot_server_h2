package com.emmurse.repos

import com.emmurse.models.PersonalUserData
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface PersonalUserDataRepository : JpaRepository<PersonalUserData, Long> {

        fun findByUserName(userName: String): Optional<PersonalUserData>
        fun findByEmail(email: String): Optional<PersonalUserData>
        fun findByUserNameAndPassword(userName: String, password: String): PersonalUserData?
}
