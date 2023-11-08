package com.emmurse

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class EmmurseApplication

fun main(args: Array<String>) {
	runApplication<EmmurseApplication>(*args)
}
