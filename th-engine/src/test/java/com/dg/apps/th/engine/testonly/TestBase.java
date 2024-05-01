package com.dg.apps.th.engine.testonly;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import com.dg.apps.th.model.config.SearchConfiguration;
import org.junit.Assert;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * Base for shared test resources.
 */
public abstract class TestBase {
    private static final String SEARCH_TARGET_DIRECTORY_PATH_STRING = "/search-target";
    private static final File searchTargetDirectory = loadSearchTargetDirectory();

    protected static final Supplier<SearchConfiguration.SearchConfigurationBuilder> CONFIG = () -> SearchConfiguration
            .builder()
            .withSearchString("")
            .withPathString(searchTargetDirectory.getAbsolutePath())
            .withFilterString("")

            .isSearchFileContent(true)
            .isSearchFileNames(true)
            .isRecursingSubdirectories(true)

            .isCaseSensitive(false)
            .isRegex(false)
            .isFilteredSearch(false)
            .isRegexFilter(false)

            .withThreadCount(1)
            .withThreadCompleteSleepTime(0);

    private MemoryAppender appender;

    /**
     * Get the search target directory.
     */
    private static File loadSearchTargetDirectory() {
        try {
            final URL url = TestBase.class.getResource(SEARCH_TARGET_DIRECTORY_PATH_STRING);
            return Paths.get(url.toURI()).toFile();
        } catch (URISyntaxException ex) {
            Assert.fail(ex.getClass().getSimpleName() + ": " + ex.getMessage());
            return null;
        }
    }

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
