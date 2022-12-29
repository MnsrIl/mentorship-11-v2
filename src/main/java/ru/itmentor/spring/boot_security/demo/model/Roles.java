package ru.itmentor.spring.boot_security.demo.model;

public enum Roles {
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    private final String value;

    private Roles(String value) { this.value = value; }

    @Override
    public String toString() {
        return value;
    }
}
