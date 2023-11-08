package com.emmurse.controllers


import com.emmurse.exceptions.EmailAlreadyExistsException
import com.emmurse.exceptions.UsernameAlreadyExistsException
import com.emmurse.models.BusinessUserData
import com.emmurse.services.BusinessUserDataService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
// Controller for business registration
@RestController
@RequestMapping("/register")
class BusinessRegisterController(private val businessUserDataService: BusinessUserDataService) {

    @PostMapping("/business")  // Handle POST request for registering a business user
    fun registerPersonalUser(@RequestBody registrationRequest: BusinessUserData): ResponseEntity<String> {
        return try { // Register the business user and return a welcome message
            val savedUser = businessUserDataService.registerBusinessUser(registrationRequest)
            val welcomeMessage = "Welcome ${savedUser.firstName}!"
            ResponseEntity.ok(welcomeMessage)
            // Handle the case where the email already exists
        } catch (e: UsernameAlreadyExistsException) {
            val errorMessage = "Business name already exists. Please choose another."
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


