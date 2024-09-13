package com.refcode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReferralCodeController {

    @Autowired
    private ReferralCodeProducer referralCodeProducer;

    @Autowired
    private ConsumerService consumerService;

    @GetMapping("/send-referral-codes")
    public String sendReferralCodes() {
        String filePath = "/home/o/IdeaProjects/RefcodeGenerator/src/REFCODE.txt";
        referralCodeProducer.sendRefCode(filePath);
        return "Referral codes are being sent to the queue!";
    }

    @GetMapping("/get-referral-code")
    public ResponseEntity<?> getRefferralCode() {
        try {
            return ResponseEntity.ok(consumerService.getMessage());
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return ResponseEntity.ok("error");
        }
    }
}
