package com.ecommerce.mail.kafka.listeners;

import com.ecommerce.mail.services.EmailService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserListener {
    EmailService emailService;

    @KafkaListener(topics = "user-registration", groupId = "mail-group")
    public void sendEmail(String email) {
        // Code to send email to the user
        System.out.println("Sending email to: " + email);
    }
}
