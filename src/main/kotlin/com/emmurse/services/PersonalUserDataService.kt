package com.emmurse.services

import com.emmurse.exceptions.EmailAlreadyExistsException
import com.emmurse.exceptions.UsernameAlreadyExistsException
import com.emmurse.models.PersonalUserData
import com.emmurse.repos.PersonalUserDataRepository
import org.springframework.stereotype.Service

@Service
class PersonalUserDataService(private val repository: PersonalUserDataRepository) {

    fun registerUser(data: PersonalUserData): PersonalUserData {
        validateUserName(data.userName)
        validateEmail(data.email)
        return repository.save(data)
    }

   private fun validateUserName(userName: String) {
        repository.findByUserName(userName).ifPresent {
            throw UsernameAlreadyExistsException("Username already exists. Please choose another.")
        }
    }

   private fun validateEmail(email: String) {
        repository.findByEmail(email).ifPresent {
            throw EmailAlreadyExistsException("This email is already in our database. Retrieve account?")
        }
    }
}

