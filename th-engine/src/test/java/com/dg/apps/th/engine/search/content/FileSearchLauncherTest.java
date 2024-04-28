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
public class FileSearchLauncherTest extends TestBase {
    private IStatusReporter mockStatusReporter;

    @Before
    public void setUp() {
        mockStatusReporter = Mockito.mock(IStatusReporter.class);
        initializeLogger();
    }

    @Test
    public final void testHappyPath() {
        final SearchConfiguration config = CONFIG.get()
                .withSearchString("north")
                .build();
        final FileSearchLauncher objectUnderTest = new FileSearchLauncher(config, mockStatusReporter);

        objectUnderTest.run();

        Mockito.verify(mockStatusReporter, Mockito.times(4)).reportStatus(Mockito.any());
        Mockito.verify(mockStatusReporter, Mockito.times(2)).reportSuccess(Mockito.any());
        Mockito.verify(mockStatusReporter, Mockito.times(1)).reportCompletion();
        Mockito.verify(mockStatusReporter, Mockito.times(0)).reportCancellation();

        Assert.assertEquals(10, getLogAppender().count());
        Assert.assertEquals("begin FileSearchLauncher c'tor - searchString=[north], isCaseSensitive=[false], isRegex=[false], searchFileContents=[true], searchFileNames][true], pathString=[F:\\_data\\code\\java\\texthunter\\th-engine\\target\\test-classes\\search-target], isRecursingSubdirectories=[Recursive], isFilteredSearch=[false], isRegexFilter=[false], filterString=[]", getLogAppender().getMessages().get(0));
        Assert.assertEquals("end FileSearchLauncher c'tor", getLogAppender().getMessages().get(1));
        Assert.assertEquals("launching search with: searchString=[north], isCaseSensitive=[false], isRegex=[false], searchFileContents=[true], searchFileNames][true], pathString=[F:\\_data\\code\\java\\texthunter\\th-engine\\target\\test-classes\\search-target], isRecursingSubdirectories=[Recursive], isFilteredSearch=[false], isRegexFilter=[false], filterString=[]", getLogAppender().getMessages().get(2));
        Assert.assertEquals("found 1 files to search", getLogAppender().getMessages().get(3));
        Assert.assertTrue(getLogAppender().getMessages().get(4).startsWith("beginning search: Thread"));
        Assert.assertEquals("beginning batch search of files", getLogAppender().getMessages().get(5));
        Assert.assertEquals("checking if states-including-northern-states.txt passes filename filter...", getLogAppender().getMessages().get(6));
        Assert.assertTrue(getLogAppender().getMessages().get(7).startsWith("opening "));
        Assert.assertTrue(getLogAppender().getMessages().get(8).startsWith("done with "));
        Assert.assertTrue(getLogAppender().getMessages().get(9).startsWith("search completed ("));
    }
}
