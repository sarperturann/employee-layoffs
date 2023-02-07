package com.study.employeelayoffs.rabbitmq

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Service

@Service
class Producer(
    val rabbitTemplate: RabbitTemplate
) {
    companion object {
        const val EXCHANGE_NAME = "exchange"
        const val ROUTING_KEY = "routing_key"
    }

    val LOGGER: Logger = LoggerFactory.getLogger(this.javaClass)

    fun sendMessage(message: String) {
        LOGGER.info(String.format("Message sent -> %s", message));
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, ROUTING_KEY, message)
    }

}