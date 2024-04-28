package com.dg.apps.th.ui.view;

import lombok.extern.slf4j.Slf4j;

import javax.swing.*;

/**
 * Util for sending the look-and-feel.
 */
@Slf4j
public class LookAndFeelSetter {
    /**
     * Set look-and-feel by Class.
     */
    public static void setLookAndFeel(final LookAndFeel lnf) {
        try {
            UIManager.setLookAndFeel(lnf);
        } catch (UnsupportedLookAndFeelException ex) {
            log.error("{}: {}", ex.getClass().getSimpleName(), ex.getMessage());
        }
    }

    /**
     * Set look-and-feel by class name (String).
     */
    public static void setLookAndFeel(final String className) {
        try {
            UIManager.setLookAndFeel(className);
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException |
                 IllegalAccessException ex) {
            log.error("{}: {}", ex.getClass().getSimpleName(), ex.getMessage());
        }
    }

    /**
     * Set look-and-feel to system.
     */
    public static void setSystemLookAndFeel() {
        LookAndFeelSetter.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    }

    /**
     * Set look-and-feel to platform.
     */
    public static void setCrossPlatformLookAndFeel() {
        LookAndFeelSetter.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
    }
}
