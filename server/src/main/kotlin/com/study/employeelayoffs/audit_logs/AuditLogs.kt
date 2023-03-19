package com.study.employeelayoffs.audit_logs

import java.time.LocalDateTime
import javax.persistence.*

@Entity
class AuditLogs (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int = 0,
    @Column
    var message: String = "",
    var createdAt: LocalDateTime = LocalDateTime.now()
)