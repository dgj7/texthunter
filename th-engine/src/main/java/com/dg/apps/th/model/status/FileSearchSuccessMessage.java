package com.dg.apps.th.model.status;

import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.util.Objects;

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
        this.file = Objects.requireNonNull(pFile);
        this.text = Objects.requireNonNull(pText);
        this.line = Objects.requireNonNull(pLine);
    }
}
