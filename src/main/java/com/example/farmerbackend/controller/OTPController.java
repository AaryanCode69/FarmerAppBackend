package com.example.farmerbackend.controller;

import com.example.farmerbackend.config.TwilioConfig;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OTPController {
    private final TwilioConfig twilioConfig;

    public OTPController(TwilioConfig twilioConfig) {
        this.twilioConfig = twilioConfig;
    }

    @GetMapping(value = "/verifyOTP")
    public ResponseEntity<String> sendSMS() {

        Twilio.init(twilioConfig.getAccountSid(), twilioConfig.getAuthToken());

        Message.creator(new PhoneNumber(twilioConfig.getUserPhone()),
                new PhoneNumber(twilioConfig.getPhoneNumber()), "Hello from Twilio 📞").create();

        return new ResponseEntity<String>("Message sent successfully", HttpStatus.OK);
    }
}
