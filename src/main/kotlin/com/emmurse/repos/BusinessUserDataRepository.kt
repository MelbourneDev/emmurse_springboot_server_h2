package com.emmurse.repos

import com.emmurse.models.BusinessUserData
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface BusinessUserDataRepository : JpaRepository<BusinessUserData, Long>
{
    fun findByBusinessName(userName: String): Optional<BusinessUserData>
    fun findByBusinessEmail(email: String): Optional<BusinessUserData>
    fun findByBusinessNameAndPassword(userName: String, password: String): BusinessUserData?
}
