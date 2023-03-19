package com.study.employeelayoffs.rabbitmq

import com.study.employeelayoffs.audit_logs.AuditLogs
import com.study.employeelayoffs.audit_logs.AuditLogsRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class Receiver {

    companion object {
        const val QUEUE_NAME = "queue"
        val LOGGER: Logger = LoggerFactory.getLogger(Receiver::class.java)
    }
    @Autowired
    lateinit var auditLogRepository: AuditLogsRepository

    @RabbitListener(queues = [QUEUE_NAME])
    fun consume(message: String) {
        LOGGER.info(String.format("Received message -> %s", message));
        val auditLog = AuditLogs(message = message)
        auditLogRepository.save(auditLog)
    }
}