package com.dg.apps.th.ui.tools;

import javax.swing.JFrame;
import java.awt.LayoutManager;

public class EnhancedFrame extends JFrame {
    private int defaultWidth = 100;
    private int defaultHeight = 100;

    public EnhancedFrame() {
        this.construct("", defaultWidth, defaultHeight, null);
    }

    public EnhancedFrame(String title) {
        this.construct(title, defaultWidth, defaultHeight, null);
    }

    public EnhancedFrame(String title, int width, int height) {
        this.construct(title, width, height, null);
    }

    public EnhancedFrame(String title, int width, int height, LayoutManager layout) {
        this.construct(title, width, height, layout);
    }

    private void construct(String title, int width, int height, LayoutManager layout) {
        if (title != null)
            this.setTitle(title);

        this.setSize(width, height);

        if (layout != null)
            this.setLayout(layout);

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }
}
