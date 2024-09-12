package com.refcode;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Service
public class ConsumerService {
    private final BlockingQueue<String> queue = new LinkedBlockingQueue<>();

    @RabbitListener(queues = "refcode")
    public void receiveMessage(String message) {
        try {
            queue.put(message);
            System.out.println("Message received: " + message);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public String getMessageFromQueue() {
        return queue.poll();
    }
}
