package com.dg.apps.th.model.status;

import lombok.Getter;
import lombok.Setter;

import java.io.File;

/**
 * File search success message.
 */
public class FileSearchSuccessMessage {
    @Getter
    @Setter
    private File file;
    @Getter
    @Setter
    private String text;
    @Getter
    @Setter
    private Long line;

    /**
     * Create a new instance.
     */
    public FileSearchSuccessMessage(final File pFile, final String pText, final Long pLine) {
        this.file = pFile;
        this.text = pText;
        this.line = pLine;
    }
}
