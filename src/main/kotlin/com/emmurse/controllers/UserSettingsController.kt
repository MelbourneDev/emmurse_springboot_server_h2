package com.emmurse.controllers


import com.emmurse.models.UserSettingsData
import com.emmurse.services.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController// Controller for user settings operations to return their settings to a form for them to change and POST back
@RequestMapping("/users")
class UserSettingsController(private val userService: UserService) {

    @GetMapping("/{username}") // Handle GET request to retrieve user by username
    fun getUserByUsername(@PathVariable username: String): ResponseEntity<Any> {
        return try {
            val user = userService.getUserByUsername(username)
            if (user != null) {
                println("User data retrieved: $user")  // Log the user data
                ResponseEntity.ok(user)
            } else {
                val errorMessage = "Username not found, please try again."
                ResponseEntity.badRequest().body(errorMessage)
            }
        } catch (e: Exception) {
            val errorMessage = "An error occurred: ${e.message}"
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage)
        }
    }

    @PostMapping("/update/{username}")// Handle POST request to update user settings
    fun updateUser(@PathVariable username: String, @RequestBody updatedUser: UserSettingsData): ResponseEntity<Any> {
        return try {
            val user = userService.updateUser(username, updatedUser)
            if (user != null) {
                ResponseEntity.ok(user)
            } else {
                val errorMessage = "Username not found, please try again."
                ResponseEntity.badRequest().body(errorMessage)
            }
        } catch (e: Exception) {
            val errorMessage = "An error occurred: ${e.message}"
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage)
        }
    }
}




