package com.study.employeelayoffs.audit_logs

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AuditLogsRepository: JpaRepository<AuditLogs, Int> {
    fun save(auditLog: AuditLogs?): AuditLogs?
}