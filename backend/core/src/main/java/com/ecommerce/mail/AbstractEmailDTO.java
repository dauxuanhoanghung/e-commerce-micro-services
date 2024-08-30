package com.ecommerce.mail;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Abstract email DTO.
 */
@Getter
@Builder
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AbstractEmailDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Email address of the sender.
     */
    String from;

    /**
     * List of recipient email addresses to send the email to.
     */
    List<String> to;

    /**
     * List of recipient email addresses to send the email to.
     */
    List<String> cc;

    /**
     * List of recipient email addresses to send the email to.
     */
    List<String> bcc;

    /**
     * Email subject.
     */
    String subject;

    /**
     * Email text content.
     */
    String text;

    /**
     * Email HTML content.
     */
    String html;

    /**
     * Email template ID.
     */
    String templateId;

    /**
     * Email template variables which is used in template.
     */
    Map<String, Object> templateVariables;
    List<AttachmentDTO> attachments;

    /**
     * Email priority. When sending multiple emails, the priority can be used to determine
     */
    @Builder.Default
    Priority priority = Priority.NORMAL;

    /**
     * Email priority levels.
     */
    public enum Priority {
        LOW, NORMAL, HIGH, HIGHEST
    }
}
