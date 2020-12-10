package com.crud.tasks.domain;

import lombok.*;

@Builder
@Getter
public class Mail {
    private final String mailTo;
    private final String subject;
    private final String message;
    private final String toCC;
}
