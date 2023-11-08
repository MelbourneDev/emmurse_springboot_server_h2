package com.emmurse.services

import com.emmurse.models.Event
import com.emmurse.repos.EventRepository
import org.slf4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class EventService(@Autowired val eventRepository: EventRepository) {

    private val logger: Logger = org.slf4j.LoggerFactory.getLogger(EventService::class.java)


    fun bookEvent(event: Event): Event {
        return eventRepository.save(event)
    }

    fun findEventsByUserName(userName: String): List<Event> {
        return eventRepository.findEventsByUserName(userName)
    }

    @Transactional
    fun cancelEvent(eventId: Long) {
        try {
            logger.info("Received request to cancel event with ID: $eventId")
            eventRepository.deleteByEventId(eventId)
            logger.info("Event cancellation successful for event ID: $eventId")
        } catch (e: Exception) {
            logger.error("Exception while canceling event ID: $eventId", e)
        }
    }

}
