package com.refcode;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Service
public class ConsumerService {

    @Autowired
    private RabbitTemplate rabbitTemplate;
//    private final BlockingQueue<String> queue = new LinkedBlockingQueue<>();
//    @RabbitListener(queues = "refcode")
//    public void receiveMessage(String message) {
//        try {
//            queue.put(message);
//            System.out.println("Message received: " + message);
//        } catch (Exception e) {
//            System.err.println(e.getMessage());
//        }
//    }
//
//    public String getMessageFromQueue() {
//        return queue.poll();
//    }

    public String getMessage() {
        Message message = rabbitTemplate.receive(Constant.REFCODE);
        if (message != null) {
            return Arrays.toString(message.getBody());
        }
        return null;
    }


}
