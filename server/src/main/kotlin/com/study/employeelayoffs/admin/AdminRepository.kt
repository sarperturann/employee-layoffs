package com.study.employeelayoffs.admin

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AdminRepository: JpaRepository<Admin, Int> {
    fun findByEmail(email: String): Admin?
}