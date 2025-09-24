package com.raid.tickets.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    USER_NOT_FOUND("USER_NOT_FOUND", "User not found with id/email %s", HttpStatus.NOT_FOUND),
    EVENT_NOT_FOUND("EVENT_NOT_FOUND", "Event with id %s does not exist", HttpStatus.BAD_REQUEST),
    EVENT_ID_UPDATE("EVENT_UPDATE_FAILED", "Cannot update the id of the event", HttpStatus.BAD_REQUEST),
    TICKET_TYPE_NOT_FOUND("TICKET_TYPE_NOT_FOUND", "Ticket type with id %s does not exist", HttpStatus.BAD_REQUEST),
    VALIDATION_FAILED("VALIDATION_FAILED", "Validation failed", HttpStatus.BAD_REQUEST),
    CONSTRAINT_VIOLATION("CONSTRAINT_VIOLATION", "Constraint violation occurred", HttpStatus.BAD_REQUEST),
    USERNAME_NOT_FOUND("USERNAME_NOT_FOUND", "Username not found", HttpStatus.NOT_FOUND),
    INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR", "An unexpected error occurred. Please try again later.", HttpStatus.INTERNAL_SERVER_ERROR);;

    private final String code;
    private final String defaultMessage;
    private final HttpStatus status;

    ErrorCode(String code, String defaultMessage, HttpStatus status) {
        this.code = code;
        this.defaultMessage = defaultMessage;
        this.status = status;
    }
}
