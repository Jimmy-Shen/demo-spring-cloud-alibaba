package com.demo.service.util.extension;

/**
 * Http Extension Status
 *
 * @author shenhongjun
 * @since 2020/4/22
 */
public enum HttpStatusExtension {
    NOT_FOUND_RESOURCE(40401, "Not Found Resource");

    private final int value;

    private final String reasonPhrase;


    HttpStatusExtension(int value, String reasonPhrase) {
        this.value = value;
        this.reasonPhrase = reasonPhrase;
    }

    public int getValue() {
        return value;
    }

    public String getReasonPhrase() {
        return reasonPhrase;
    }
}
