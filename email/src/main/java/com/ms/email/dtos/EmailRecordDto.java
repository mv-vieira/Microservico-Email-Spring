package com.ms.email.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
public record EmailRecordDto(UUID id,
                             String emailTo,
                             String subject,
                             String text) {
}
