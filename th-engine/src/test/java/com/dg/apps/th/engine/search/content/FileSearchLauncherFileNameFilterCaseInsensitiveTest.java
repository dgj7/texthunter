package com.dg.apps.th.engine.search.content;

import com.dg.apps.th.engine.testonly.TestBase;
import com.dg.apps.th.engine.threads.IStatusReporter;
import com.dg.apps.th.model.config.SearchConfiguration;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Test {@link FileSearchLauncher}.
 */
public class FileSearchLauncherFileNameFilterCaseInsensitiveTest extends TestBase {
    private IStatusReporter mockStatusReporter;

    @Before
    public void setUp() {
        mockStatusReporter = Mockito.mock(IStatusReporter.class);
        initializeLogger();
    }

    @Test
    public final void testMiss() {
        final SearchConfiguration config = CONFIG.get()
                .withSearchString("north")
                .isCaseSensitive(false)
                .isFilteredSearch(true)
                .withFilterString("failure")
                .build();
        final FileSearchLauncher objectUnderTest = new FileSearchLauncher(config, mockStatusReporter);

        objectUnderTest.run();

        Mockito.verify(mockStatusReporter, Mockito.times(3)).reportStatus(Mockito.any());
        Mockito.verify(mockStatusReporter, Mockito.times(0)).reportSuccess(Mockito.any());
        Mockito.verify(mockStatusReporter, Mockito.times(1)).reportCompletion();
        Mockito.verify(mockStatusReporter, Mockito.times(0)).reportCancellation();

        Assert.assertEquals(10, getLogAppender().count());
        Assert.assertTrue(getLogAppender().getMessages().get(0).startsWith("begin FileSearchLauncher c'tor "));
        Assert.assertEquals("end FileSearchLauncher c'tor", getLogAppender().getMessages().get(1));
        Assert.assertTrue(getLogAppender().getMessages().get(2).startsWith("launching search with: "));
        Assert.assertEquals("found 1 files to search", getLogAppender().getMessages().get(3));
        Assert.assertTrue(getLogAppender().getMessages().get(4).startsWith("beginning search: "));
        Assert.assertEquals("beginning batch search of files", getLogAppender().getMessages().get(5));
        Assert.assertEquals("checking if states-including-northern-states.txt passes filename filter...", getLogAppender().getMessages().get(6));
        Assert.assertEquals("done with batch search of files", getLogAppender().getMessages().get(7));
        Assert.assertTrue(getLogAppender().getMessages().get(8).startsWith("completed search: "));
        Assert.assertTrue(getLogAppender().getMessages().get(9).startsWith("search completed ("));
    }

    @Test
    public final void testHit() {
        final SearchConfiguration config = CONFIG.get()
                .withSearchString("north")
                .isCaseSensitive(false)
                .isFilteredSearch(true)
                .withFilterString("north")
                .build();
        final FileSearchLauncher objectUnderTest = new FileSearchLauncher(config, mockStatusReporter);

        objectUnderTest.run();

        Mockito.verify(mockStatusReporter, Mockito.times(6)).reportStatus(Mockito.any());
        Mockito.verify(mockStatusReporter, Mockito.times(3)).reportSuccess(Mockito.any());
        Mockito.verify(mockStatusReporter, Mockito.times(1)).reportCompletion();
        Mockito.verify(mockStatusReporter, Mockito.times(0)).reportCancellation();

        Assert.assertEquals(12, getLogAppender().count());
        Assert.assertTrue(getLogAppender().getMessages().get(0).startsWith("begin FileSearchLauncher c'tor "));
        Assert.assertEquals("end FileSearchLauncher c'tor", getLogAppender().getMessages().get(1));
        Assert.assertTrue(getLogAppender().getMessages().get(2).startsWith("launching search with: "));
        Assert.assertEquals("found 1 files to search", getLogAppender().getMessages().get(3));
        Assert.assertTrue(getLogAppender().getMessages().get(4).startsWith("beginning search: "));
        Assert.assertEquals("beginning batch search of files", getLogAppender().getMessages().get(5));
        Assert.assertEquals("checking if states-including-northern-states.txt passes filename filter...", getLogAppender().getMessages().get(6));
        Assert.assertTrue(getLogAppender().getMessages().get(7).startsWith("opening "));
        Assert.assertTrue(getLogAppender().getMessages().get(8).startsWith("done with "));
        Assert.assertEquals("done with batch search of files", getLogAppender().getMessages().get(9));
        Assert.assertTrue(getLogAppender().getMessages().get(10).startsWith("completed search: "));
        Assert.assertTrue(getLogAppender().getMessages().get(11).startsWith("search completed ("));
    }
}
