package com.emmurse.repos

import com.emmurse.models.Event
import org.springframework.data.jpa.repository.JpaRepository

interface EventRepository : JpaRepository<Event, Long> {

    fun findEventsByUserName(userName: String): List<Event>
    fun deleteByEventId(eventId: Long);

}