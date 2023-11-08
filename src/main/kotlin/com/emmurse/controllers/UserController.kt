package com.emmurse.controllers


import com.emmurse.models.BusinessUserData
import com.emmurse.models.PersonalUserData
import com.emmurse.repos.BusinessUserDataRepository
import com.emmurse.repos.PersonalUserDataRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController// Controller for user-related operations
@RequestMapping("/api/users")
class UserController(@Autowired val userRepository: PersonalUserDataRepository,
                     @Autowired val businessUserRepository: BusinessUserDataRepository
) {

    @GetMapping("/subscription/{identifier}")  // Handle GET request to retrieve user subscription information and return their tier
    fun getUserSubscription(@PathVariable identifier: String): ResponseEntity<String> {
        return userRepository.findByUserName(identifier)
            .map { user -> ResponseEntity(user.subscriptionType, HttpStatus.OK) }
            .orElseGet {
                businessUserRepository.findByBusinessName(identifier)
                    .map { user -> ResponseEntity(user.subscriptionType, HttpStatus.OK) }
                    .orElse(ResponseEntity(HttpStatus.NOT_FOUND))
            }
    }



}
