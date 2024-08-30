package com.ecommerce.mail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttachmentReference implements Serializable {
    private String fileId;
    private String filename;
    private String contentType;
}