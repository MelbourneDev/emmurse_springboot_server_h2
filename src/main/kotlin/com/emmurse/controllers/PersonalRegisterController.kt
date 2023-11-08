package com.emmurse.controllers

import com.emmurse.models.PersonalUserData
import com.emmurse.services.PersonalUserDataService
import com.emmurse.exceptions.UsernameAlreadyExistsException
import com.emmurse.exceptions.EmailAlreadyExistsException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController// Controller for personal user registration
@RequestMapping("/register")
class PersonalRegisterController(private val personalUserDataService: PersonalUserDataService) {

    @PostMapping("/personal")  // Handle POST request for registering a personal user
    fun registerPersonalUser(@RequestBody registrationRequest: PersonalUserData): ResponseEntity<String> {
        return try {// Register the personal user and return a welcome message
            val savedUser = personalUserDataService.registerUser(registrationRequest)
            val welcomeMessage = "Welcome ${savedUser.firstName}!"
            ResponseEntity.ok(welcomeMessage)
        } catch (e: UsernameAlreadyExistsException) {
            val errorMessage = "Username already exists. Please choose another."
            // Log the error message for debugging
            println("Error message: $errorMessage")
            ResponseEntity.badRequest().body(errorMessage)
        } catch (e: EmailAlreadyExistsException) {
            val errorMessage = "This email is already in our database."
            // Log the error message for debugging
            println("Error message: $errorMessage")
            ResponseEntity.badRequest().body(errorMessage)
        }

    }


}
