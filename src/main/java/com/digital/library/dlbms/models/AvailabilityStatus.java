package com.digital.library.dlbms.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum AvailabilityStatus {

    AVAILABLE("Available"),
    CHECKEDOUT("CheckedOut");

    private final String value;

    AvailabilityStatus(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static AvailabilityStatus fromValue(String value) {
        for (AvailabilityStatus status : AvailabilityStatus.values()) {
            if (status.value.equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid availability status: " + value);
    }
}
