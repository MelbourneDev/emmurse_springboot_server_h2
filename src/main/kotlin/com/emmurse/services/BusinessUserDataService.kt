package com.emmurse.services

import com.emmurse.exceptions.EmailAlreadyExistsException
import com.emmurse.exceptions.UsernameAlreadyExistsException
import com.emmurse.models.BusinessUserData
import com.emmurse.models.PersonalUserData
import com.emmurse.repos.BusinessUserDataRepository
import org.springframework.stereotype.Service

@Service
class BusinessUserDataService(private val repository: BusinessUserDataRepository) {

    fun registerBusinessUser(data: BusinessUserData): BusinessUserData {
        validateBusinessName(data.businessName)
        validateBusinessEmail(data.businessEmail)
        return repository.save(data)
    }

    private fun validateBusinessName(businessName: String) {
        repository.findByBusinessName(businessName).ifPresent {
            throw UsernameAlreadyExistsException("Business name already exists. Please choose another.")
        }
    }

    private fun validateBusinessEmail(email: String) {
        repository.findByBusinessEmail(email).ifPresent {
            throw EmailAlreadyExistsException("This business email is already in our database. Retrieve account?")
        }
    }
}
