package com.emmurse.services


import com.emmurse.repos.BusinessUserDataRepository
import com.emmurse.repos.PersonalUserDataRepository
import org.springframework.stereotype.Service


@Service
class LoginUserDataService(private val businessUserDataRepository: BusinessUserDataRepository,
                           private val personalUserDataRepository: PersonalUserDataRepository) {

    fun loginUser(usernameOrEmail: String, password: String): Any? {
        // First, check the business user table using businessEmail
        val businessUser = businessUserDataRepository.findByBusinessNameAndPassword(usernameOrEmail, password)
        if (businessUser != null) {
            return businessUser
        }

        // If not found in business user table, check the personal user table using username
        val personalUser = personalUserDataRepository.findByUserNameAndPassword(usernameOrEmail, password)
        if (personalUser != null) {
            return personalUser
        }

        // If not found in both tables, return null
        return null
    }
}