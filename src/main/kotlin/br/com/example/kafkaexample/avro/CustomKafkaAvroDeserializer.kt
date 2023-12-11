package br.com.example.kafkaexample.avro

import org.apache.avro.Schema

//class CustomKafkaAvroDeserializer : KafkaAvroDeserializer {
//    override fun deserialize(topic: String, bytes: ByteArray): Any {
//        this.schemaRegistry = getMockClient(AutorizadorCreditoValue.`SCHEMA$`)
//        return super.deserialize(topic, bytes)
//    }
//
//    private fun getMockClient(schema: Schema): SchemaRegistryClient {
//        return object : MockSchemaRegistryClient() {
//            @Synchronized
//            override fun getById(id: Int): Schema {
//                return schema
//            }
//        }
//    }
//}