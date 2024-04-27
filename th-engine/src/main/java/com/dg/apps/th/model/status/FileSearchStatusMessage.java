package com.dg.apps.th.model.status;

import com.dg.apps.th.engine.threads.AbstractStatusMessage;
import com.dg.apps.th.engine.threads.ThreadStatus;
import lombok.Getter;
import lombok.Setter;

/**
 * Status of a thread's search progress.
 */
public class FileSearchStatusMessage extends AbstractStatusMessage {
    @Getter
    @Setter
    private String fileName;
    @Getter
    @Setter
    private Long linesFound;
    @Getter
    @Setter
    private Long filesSearched;
    @Getter
    @Setter
    private Long filesSkipped;
    @Getter
    @Setter
    private String threadName;
    @Getter
    @Setter
    private Long totalFiles;
    @Getter
    @Setter
    private ThreadStatus threadStatus;
}
