package com.emmurse.controllers

import com.emmurse.models.BusinessUserData
import com.emmurse.models.LoginUserData
import com.emmurse.models.PersonalUserData
import com.emmurse.services.LoginUserDataService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController// Controller for user login
@RequestMapping("/login")
class LoginController(private val loginUserDataService: LoginUserDataService) {

    @PostMapping("/user")  // Handle POST request for user login
    fun loginUser(@RequestBody loginRequest: LoginUserData): ResponseEntity<String> {
        return try {// Attempt to log in the user and generate a welcome message
            val user = loginUserDataService.loginUser(loginRequest.userName, loginRequest.password)
            if (user != null) { // Determine the user type and create a welcome message
                                // Return a successful response with the welcome message
                val welcomeMessage = when (user) {
                    is BusinessUserData -> "Welcome ${user.businessName}!"
                    is PersonalUserData -> "Welcome ${user.firstName}!"
                    else -> throw RuntimeException("Unknown user type")
                }
                ResponseEntity.ok(welcomeMessage)
            } else { // Handle the case where the username is not found
                val errorMessage = "Username not found, please try again."
                ResponseEntity.badRequest().body(errorMessage)
            }
        } catch (e: Exception) {
            val errorMessage = "An error occurred: ${e.message}"
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage)
        }
    }
}


