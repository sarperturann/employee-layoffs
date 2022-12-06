package com.study.employeelayoffs.dao

import com.study.employeelayoffs.domain.Admin
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AdminRepository: JpaRepository<Admin, Int> {
    fun findByEmail(email: String): Admin?
}