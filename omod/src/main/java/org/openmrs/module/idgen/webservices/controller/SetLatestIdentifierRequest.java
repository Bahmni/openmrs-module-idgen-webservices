package org.openmrs.module.idgen.webservices.controller;

public class SetLatestIdentifierRequest {
    private String sourceName;
    private Long identifier;

    public SetLatestIdentifierRequest() {
    }

    public SetLatestIdentifierRequest(String sourceName, Long identifier) {
        this.sourceName = sourceName;
        this.identifier = identifier;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public Long getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Long identifier) {
        this.identifier = identifier;
    }
}
