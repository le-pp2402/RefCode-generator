package com.refcode;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;

@Service
public class ReferralCodeProducer {
    private final RabbitTemplate rabbitTemplate;
    private final Queue referralCodeQueue;

    @Autowired
    public ReferralCodeProducer(RabbitTemplate rabbitTemplate, Queue referralCodeQueue) {
        this.rabbitTemplate = rabbitTemplate;
        this.referralCodeQueue = referralCodeQueue;
    }

    public void sendRefCode(String filePath) {
        int maxLine = 10;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                rabbitTemplate.send(referralCodeQueue.getName(), new Message(line.getBytes()));
                maxLine--;
                if (maxLine == 0) break;
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
