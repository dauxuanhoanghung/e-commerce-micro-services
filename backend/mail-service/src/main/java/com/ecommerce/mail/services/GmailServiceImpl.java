package com.ecommerce.mail.services;

import com.ecommerce.mail.utilities.CssInline;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Map;

/**
 * Email service implementation
 */
@Slf4j
@Service("gmailService")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GmailServiceImpl implements EmailService {

    JavaMailSender javaMailSender;
    TemplateEngine templateEngine;
    CssInline cssInline;

    @Override
    @Async
    public void send(String to, String subject, String templateName, Map<String, Object> templateModel) {
        if (!templateName.endsWith(".html")) {
            templateName += ".html";
        }

        Context context = new Context();
        context.setVariables(templateModel);
        String htmlContent = templateEngine.process(templateName, context);
        htmlContent = cssInline.inline(htmlContent);
        final MimeMessage message = javaMailSender.createMimeMessage();
        final MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");
        try {
//            helper.setFrom("ecommerce-micro@gmail.com");
//            helper.setTo("david@yopmail.com");
            helper.setTo("tdph1168@gmail.com");
            helper.setSubject(subject);
            helper.setText(htmlContent, true);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            log.error("Error occurred while processing email template: {}", e.getMessage());
        }
    }

    @Override
    @Async
    public void send(String to, String subject, String text) {
        this.send(to, subject, text, null);
    }
}
