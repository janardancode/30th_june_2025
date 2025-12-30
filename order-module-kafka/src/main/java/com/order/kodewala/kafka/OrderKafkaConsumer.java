package com.order.kodewala.kafka;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.event.EventListener;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.stereotype.Component;

@Component
public class OrderKafkaConsumer {

	@EventListener(ApplicationReadyEvent.class)
	public void startConsumer() {

		Properties prop = new Properties();
		prop.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		prop.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "my-group-1");
		prop.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		prop.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

		KafkaConsumer<String, String> consumer = new KafkaConsumer<>(prop);
		consumer.subscribe(Collections.singletonList("order"));

		System.out.println("Kafka Consumer started...");

		int count = 0;
		int max = 5;
		while (count < max) {

			ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));

			for (ConsumerRecord<String, String> record : records) {
				System.out.println("offset     : " + record.offset());
				System.out.println("key        : " + record.key());
				System.out.println("value      : " + record.value());
				System.out.println("partition  : " + record.partition());
				System.out.println("topic      : " + record.topic());
				System.out.println("---------------------------");
				count++;
			}
		}

		consumer.close();
		System.out.println("Kafka Consumer stopped after " + max + " messages");
	}
}
