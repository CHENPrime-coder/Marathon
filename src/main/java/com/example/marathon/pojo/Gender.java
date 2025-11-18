package com.example.marathon.pojo;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Shared gender enum matching database values.
 */
public enum Gender {
    MALE("Male"),
    FEMALE("Female");

    private final String value;

    Gender(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
