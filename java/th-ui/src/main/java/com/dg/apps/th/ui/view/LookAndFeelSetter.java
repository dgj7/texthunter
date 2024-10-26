package com.dg.apps.th.ui.view;

import com.dg.apps.th.ui.exc.TextHunterUserInterfaceException;
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
            throw new TextHunterUserInterfaceException(ex);
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
            throw new TextHunterUserInterfaceException(ex);
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
