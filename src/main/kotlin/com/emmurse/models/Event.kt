package com.emmurse.models

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer
import java.time.LocalDate
import java.time.LocalTime
import javax.persistence.*

@Entity
data class Event(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val eventId: Long? = null,
    val eventName: String,
    val userName: String? = null,
    @field:JsonDeserialize(using = LocalDateDeserializer::class)
    val date: LocalDate,
    val time: LocalTime,
    val capacity: Int
)
