package com.barriSenseBack.barrisense_feedback_api.service;

import com.barriSenseBack.barrisense_feedback_api.logger.Loggable;

public enum ComplaintServiceLogEvent implements Loggable {

    FETCHING_ALL("INFO", "Buscando todas las quejas."),
    FOUND_BY_ID("DEBUG", "Queja encontrada con ID: {}."),
    NOT_FOUND_BY_ID("WARN", "No se encontró ninguna queja con el ID: {}."),
    COUNTING_BY_HOOD_ID("INFO", "Contando quejas para el barrio con ID: {}."),
    FETCHING_BY_HOOD_ID("INFO", "Buscando quejas para el barrio con ID: {}."),
    COMPLAINT_SAVED("INFO", "Nueva queja guardada con éxito. ID: {}.");

    private final LogLevel level;
    private final String messageTemplate;

    ComplaintServiceLogEvent(String level, String messageTemplate) {
        this.level = LogLevel.valueOf(level);
        this.messageTemplate = messageTemplate;
    }

    @Override
    public LogLevel getLevel() { return this.level; }

    @Override
    public String getMessageTemplate() { return this.messageTemplate; }
}