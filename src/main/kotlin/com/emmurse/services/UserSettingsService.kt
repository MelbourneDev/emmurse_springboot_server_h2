package com.emmurse.services


import com.emmurse.models.BusinessUserData
import com.emmurse.models.PersonalUserData
import com.emmurse.models.UserSettingsData
import com.emmurse.models.toUserSettingsData
import com.emmurse.repos.BusinessUserDataRepository
import com.emmurse.repos.PersonalUserDataRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class UserService(
    private val businessUserDataRepository: BusinessUserDataRepository,
    private val personalUserDataRepository: PersonalUserDataRepository
) {

    private val log = LoggerFactory.getLogger(UserService::class.java)

    fun getUserByUsername(username: String): UserSettingsData? {
        try {
            val businessUser = businessUserDataRepository.findByBusinessName(username)?.orElse(null)
            if (businessUser != null) {
                return businessUser.toUserSettingsData()
            }

            val personalUser = personalUserDataRepository.findByUserName(username)?.orElse(null)
            if (personalUser != null) {
                return personalUser.toUserSettingsData()
            }

            log.info("No user found for username: $username")
            return null
        } catch (e: Exception) {
            log.error("Exception in getUserByUsername: ${e.message}", e)
            throw e  // Or handle the exception accordingly
        }
    }

    fun updateUser(username: String, updatedUser: UserSettingsData): Any? {
        try {
            if (updatedUser.businessName != null && updatedUser.businessEmail != null) {
                // Assume it's a BusinessUserData object
                val businessUser = BusinessUserData(
                    businessName = updatedUser.businessName,
                    firstName = updatedUser.firstName,
                    lastName = updatedUser.lastName,
                    businessEmail = updatedUser.businessEmail,
                    password = updatedUser.password,

                )
                return businessUserDataRepository.save(businessUser)
            } else if (updatedUser.userName != null && updatedUser.email != null) {
                // Assume it's a PersonalUserData object
                val personalUser = PersonalUserData(
                    userName = updatedUser.userName,
                    firstName = updatedUser.firstName,
                    lastName = updatedUser.lastName,
                    email = updatedUser.email,
                    password = updatedUser.password
                )
                return personalUserDataRepository.save(personalUser)
            } else {
                log.info("Updated user data doesn't match known user types: $updatedUser")
                return null
            }
        } catch (e: Exception) {
            log.error("Exception in updateUser: ${e.message}", e)
            throw e  // Or handle the exception accordingly
        }
    }


}
