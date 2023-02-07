package com.study.employeelayoffs.rabbitmq


import com.rabbitmq.client.ConnectionFactory
import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.Queue
import org.springframework.amqp.core.TopicExchange
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

@Configuration
class RabbitMqConfig {

    companion object {
        const val QUEUE_NAME = "queue"
        const val EXCHANGE_NAME = "exchange"
        const val ROUTING_KEY = "routing_key"
    }

    @Bean
    fun queue(): Queue = Queue(QUEUE_NAME, false)

    @Bean
    fun exchange(): TopicExchange = TopicExchange(EXCHANGE_NAME)

    @Bean
    fun binding(queue: Queue, exchange: TopicExchange): Binding =
        BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY)

}