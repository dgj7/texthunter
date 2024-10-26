package com.dg.apps.th.ui.view.frame;

import javax.swing.*;
import java.awt.*;

/**
 * An attempt at a better {@link JFrame}.
 */
public abstract class EnhancedFrame extends JFrame {
    private static final int DEFAULT_WIDTH = 100;
    private static final int DEFAULT_HEIGHT = 100;

    /**
     * Create a new instance.
     */
    protected EnhancedFrame() {
        this.construct("", DEFAULT_WIDTH, DEFAULT_HEIGHT, null);
    }

    /**
     * Create a new instance.
     */
    protected EnhancedFrame(final String title) {
        this.construct(title, DEFAULT_WIDTH, DEFAULT_HEIGHT, null);
    }

    /**
     * Create a new instance.
     */
    protected EnhancedFrame(final String title, final int width, final int height) {
        this.construct(title, width, height, null);
    }

    /**
     * Create a new instance.
     */
    protected EnhancedFrame(final String title, final int width, final int height, final LayoutManager layout) {
        this.construct(title, width, height, layout);
    }

    /**
     * Shared initializer.
     */
    private void construct(final String title, final int width, final int height, final LayoutManager layout) {
        if (title != null)
            this.setTitle(title);

        this.setSize(width, height);

        if (layout != null)
            this.setLayout(layout);

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }
}
