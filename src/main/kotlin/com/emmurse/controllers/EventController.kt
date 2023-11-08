package com.emmurse.controllers

import com.emmurse.models.Event
import com.emmurse.services.EventService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
// Controller for managing events
@RestController
@RequestMapping("/events")
class EventController(@Autowired val eventService: EventService) {

    companion object {
        private val logger = LoggerFactory.getLogger(EventController::class.java)
    }
    // Handle POST request to book an event
    @PostMapping("/book")
    fun bookEvent(@RequestBody event: Event): Event {
        println("Received event: $event")
        return eventService.bookEvent(event)
    }

    @GetMapping("/userEvents")// Handle GET request to retrieve user events
    fun getUserEvents(@RequestParam userName: String): ResponseEntity<List<Event>> {
        logger.info("Received GET request for user events. User: $userName")

        val events = eventService.findEventsByUserName(userName)

        if (events.isNullOrEmpty()) {  // Handle the case where no events are found for the user
            logger.warn("No events found for user: $userName")
            return ResponseEntity.notFound().build()
        }

        logger.info("Found ${events.size} events for user: $userName")

        return ResponseEntity.ok(events)
    }

    @DeleteMapping("/cancel/{eventId}")    // Handle DELETE request to cancel an event
    fun cancelEvent(@PathVariable eventId: Long): ResponseEntity<Void> {
        try {
            eventService.cancelEvent(eventId)
            logger.info("Event cancellation successful for event ID: $eventId")
            return ResponseEntity.ok().build()
        } catch (e: Exception) {
            logger.error("Event cancellation failed for event ID: $eventId", e)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
        }
    }


}
