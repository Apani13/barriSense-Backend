package com.barriSenseBack.barrisense_feedback_api.logger;

import org.slf4j.Logger;

/**
 * A contract for creating structured, enum-based logging events.
 * <p>
 * This interface centralizes the logging logic into a default {@code log} method.
 * It leverages SLF4J's native parameterized message substitution ("{}") for optimal
 * performance and readability. Enums implementing this interface are responsible only
 * for defining the log events, their severity levels, and their message templates.
 * <p>
 * This approach avoids manual performance checks (e.g., {@code logger.isInfoEnabled()})
 * and the use of {@code MessageFormat}, delegating the formatting and guard logic
 * directly to the SLF4J engine.
 */
public interface Loggable {

    /**
     * Defines the severity level of a log event, mapping directly to SLF4J levels.
     */
    enum LogLevel {
        DEBUG, INFO, WARN, ERROR
    }

    /**
     * Returns the severity level of the specific log event.
     *
     * @return The {@link LogLevel} of the event.
     */
    LogLevel getLevel();

    /**
     * Returns the raw, unformatted message template for the log event,
     * using SLF4J's "{}" placeholder syntax.
     *
     * @return The message string with "{}" placeholders.
     */
    String getMessageTemplate();

    /**
     * Writes this event to the provided logger using SLF4J's native substitution.
     * <p>
     * This default method contains the core logging logic. It delegates directly
     * to the appropriate SLF4J logging method (e.g., {@code logger.debug}).
     * The SLF4J implementation will internally perform the performance check
     * and only format the message if the corresponding log level is enabled.
     *
     * @param logger The SLF4J logger instance from the calling class.
     * @param params The contextual data to be inserted into the message template's "{}" placeholders.
     */
    default void log(Logger logger, Object... params) {
        switch (this.getLevel()) {
            case DEBUG -> logger.debug(this.getMessageTemplate(), params);
            case INFO  -> logger.info(this.getMessageTemplate(), params);
            case WARN  -> logger.warn(this.getMessageTemplate(), params);
            case ERROR -> logger.error(this.getMessageTemplate(), params);
        }
    }
}