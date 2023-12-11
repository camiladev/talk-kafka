package br.com.example.kafkaexample.client

import org.apache.kafka.clients.consumer.ConsumerConfig.*
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.common.serialization.LongDeserializer
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.kafka.common.serialization.StringSerializer
import java.time.Duration.ofMillis
import java.util.*

/**
 * NA PASTA DOCKER ESTÁ O DOCKER-COMPOSE PARA RODAR OS SERVIÇOS LOCAIS;
 * CONSUMIDORES COM O MESMO "GROUP_ID_CONFIG" NÃO CONSOMEM MENSAGENS REPETIDAS;
 * CADA PARTIÇÃO DO TÓPICO SÓ PODE SER CONSUMIDO POR UM CONSUMIDOR, MAS UM CONSUMIDOR PODE CONSUMIR VÁRIAS PARTIÇÕES;
 *
 * REFERÊNCIA: https://github.com/confluentinc/examples/tree/6.2.0-post/clients/cloud/kotlin
 */

private val TOPIC = "topic-talk" // Informar o nome do tópico que deseja ler as mensagens
private val BOOTSTRAP_SERVERS = "localhost:9091"
private val GROUP_ID = "KafkaExampleConsumer2"

private fun createConsumer(): KafkaConsumer<Long, String> {

    val props = Properties()
    props[BOOTSTRAP_SERVERS_CONFIG] = BOOTSTRAP_SERVERS
    props[GROUP_ID_CONFIG] = GROUP_ID
    props[AUTO_OFFSET_RESET_CONFIG] = "earliest"
    props[KEY_DESERIALIZER_CLASS_CONFIG] = LongDeserializer::class.java
    props[VALUE_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java

    return KafkaConsumer<Long, String>(props).apply {
        subscribe(listOf(TOPIC))
    }
}

private fun runConsumer() {
    var totalCount = 0L
    val consumer = createConsumer()
    consumer.use {
        while (true) {
            totalCount = consumer
                .poll(ofMillis(100))
                .fold(totalCount) { accumulator, record ->
                    val newCount = accumulator + 1
                    println("Consumed record with key ${record.key()} and value ${record.value()}, and updated total count to $newCount")
                    newCount
                }
        }
    }

}

fun main() {
    runConsumer()
}


