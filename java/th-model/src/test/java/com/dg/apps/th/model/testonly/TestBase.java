package com.dg.apps.th.model.testonly;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import org.junit.Assert;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Objects;

public class TestBase {
    private MemoryAppender appender;

    /**
     * Access the appender.
     */
    protected MemoryAppender getLogAppender() {
        return Objects.requireNonNull(appender, "not initialized; call initializeLogger() first");
    }

    /**
     * Initialize the logger.
     *
     * @see <a href="https://www.baeldung.com/junit-asserting-logs">baeldung</a>
     */
    protected void initializeLogger() {
        final Logger logger = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
        appender = new MemoryAppender();
        appender.setContext((LoggerContext) LoggerFactory.getILoggerFactory());
        logger.setLevel(Level.TRACE);
        logger.addAppender(appender);
        appender.start();
    }

    /**
     * Test-only log appender.
     *
     * @see <a href="https://www.baeldung.com/junit-asserting-logs">baeldung</a>
     */
    protected static class MemoryAppender extends ListAppender<ILoggingEvent> {
        public int count() {
            return list.size();
        }

        public List<String> getMessages() {
            return list.stream()
                    .map(ILoggingEvent::getFormattedMessage)
                    .toList();
        }
    }

    /**
     * Util method to print the asserts.
     */
    protected void printLogAsserts() {
        System.out.println("Assert.assertEquals(" + getLogAppender().count() + ", getLogAppender().count());");
        for (int c = 0; c < getLogAppender().count(); c++) {
            System.out.println("Assert.assertEquals(\"" + getLogAppender().getMessages().get(c) + "\", getLogAppender().getMessages().get(" + c + "));");
        }
        Assert.fail("remove call to printLogAsserts() before proceeding");
    }
}
