package com.ecommerce.mail.services;

import java.util.Map;

/**
 * Email service interface
 */
public interface EmailService {

    /**
     * Send an email with a html template
     *
     * @param to            recipient email address
     * @param subject       email subject
     * @param templateName  html template name
     * @param templateModel template model
     */
    void send(String to, String subject, String templateName, Map<String, Object> templateModel);

    /**
     * Send an email with a text
     *
     * @param to      recipient email address
     * @param subject email subject
     * @param text    email text
     */
    void send(String to, String subject, String text);
}
