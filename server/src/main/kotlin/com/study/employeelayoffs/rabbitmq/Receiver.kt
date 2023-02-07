package com.study.employeelayoffs.rabbitmq

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Service

@Service
class Receiver {

    val LOGGER: Logger = LoggerFactory.getLogger(Receiver::class.java)

    companion object {
        const val QUEUE_NAME = "queue"
    }

    @RabbitListener(queues = [QUEUE_NAME])
    fun consume(message: String) {
        LOGGER.info(String.format("Received message -> %s", message));
    }
}