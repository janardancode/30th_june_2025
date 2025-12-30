package com.order.kodewala.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order.kodewala.entity.Order;
import com.order.kodewala.kafka.OrderKafkaProducer;
import com.order.kodewala.repository.OrderRepository;



@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderKafkaProducer kafkaProducer;

    public void placeOrder(Order order) {

        orderRepository.save(order);

        // Send Kafka notification
        kafkaProducer.sendMessage(
                "ORD-" + order.getOrderId(),
                "Hi Janardan, your order has been placed successfully"
        );
    }
}
