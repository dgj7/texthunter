package com.dg.apps.th.ui.tools;

import javax.swing.*;

public class LookAndFeelSetter {
    public static void setLookAndFeel(LookAndFeel lnf) {
        try {
            UIManager.setLookAndFeel(lnf);
        } catch (UnsupportedLookAndFeelException ex) {
            // don't do anything - use the default look and feel
        }
    }

    public static void setLookAndFeel(String className) {
        try {
            UIManager.setLookAndFeel(className);
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException |
                 IllegalAccessException ex) {
            // don't do anything - use the default look and feel
        }
    }

    public static void setSystemLookAndFeel() {
        LookAndFeelSetter.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    }

    public static void setCrossPlatformLookAndFeel() {
        LookAndFeelSetter.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
    }
}
