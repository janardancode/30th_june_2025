package com.kodewala.kafka;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

public class ProducerApp {
	public static void main(String[] args) {
		// Step 1 - Set Kafka Configuration (Bootstrap server, Key & Value serializers)
		Properties prop = new Properties();
		prop.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		prop.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		prop.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		// step 2. Create Kafka Producer
		KafkaProducer<String, String> producer = new KafkaProducer<>(prop);
		// step 3. Create & Send Messages
		for   (int i = 0; i < 15; i++) {

			ProducerRecord<String, String> record = new ProducerRecord<>("order", "Ord" + i,
					"Hi janardan:your order has been delivered  " + i);
			producer.send(record);
		}

		// Step -4 close producer

		producer.close();
		System.out.println("Messages have been sent to kafka!");
	}

}
