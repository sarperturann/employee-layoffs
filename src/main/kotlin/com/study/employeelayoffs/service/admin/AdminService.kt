package com.study.employeelayoffs.service.admin

import com.study.employeelayoffs.dao.AdminRepository
import com.study.employeelayoffs.domain.Admin
import org.springframework.stereotype.Service

@Service
class AdminService(
    private val adminRepository: AdminRepository
) {

    fun save(admin: Admin): Admin {
        return this.adminRepository.save(admin)
    }

    fun findByEmail(email: String): Admin? {
        return this.adminRepository.findByEmail(email)
    }

    fun getById(id: Int): Admin {
        return this.adminRepository.getById(id)
    }
}