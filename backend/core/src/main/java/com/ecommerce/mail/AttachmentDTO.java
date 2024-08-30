package com.ecommerce.mail;

import java.io.Serial;
import java.io.Serializable;

public class AttachmentDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private String name;

    /**
     * Attachment data.
     */
    private byte[] data;

    /**
     * Attachment MIME type.
     */
    private String mimeType;
}
