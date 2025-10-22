package com.barriSenseBack.barrisense_feedback_api.controller;

import com.barriSenseBack.barrisense_feedback_api.logger.Loggable;

public enum ComplaintControllerLogEvent implements Loggable {

    REQUEST_GET_ALL("INFO", "Request entrante: GET /api/complaints"),
    REQUEST_GET_BY_ID("INFO", "Request entrante: GET /api/complaints/{}"),
    REQUEST_COUNT_BY_HOOD("INFO", "Request entrante: GET /api/complaints/count/by-neighborhood/{}"),
    REQUEST_GET_BY_HOOD("INFO", "Request entrante: GET /api/complaints/by-neighborhood/{}"),
    REQUEST_GET_ALL_COUNTS("INFO", "Request entrante: GET /api/complaints/count/by-neighborhood/all"),
    REQUEST_CREATE("INFO", "Request entrante: POST /api/complaints");

    private final LogLevel level;
    private final String messageTemplate;

    ComplaintControllerLogEvent(String level, String messageTemplate) {
        this.level = LogLevel.valueOf(level);
        this.messageTemplate = messageTemplate;
    }

    @Override
    public LogLevel getLevel() { return this.level; }

    @Override
    public String getMessageTemplate() { return this.messageTemplate; }
}