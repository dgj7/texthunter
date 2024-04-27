package com.dg.apps.th.ui;

import java.awt.*;

/**
 * UI constants.
 */
public class TextHunterConstants {
    public static final String LOGGER_FONT_NAME = "Courier New";
    public static final int LOGGER_FONT_STYLE = Font.PLAIN;
    public static final int LOGGER_FONT_SIZE = 12;
    public static final Font LOGGER_FONT = new Font(LOGGER_FONT_NAME, LOGGER_FONT_STYLE, LOGGER_FONT_SIZE);
    public static final String APP_TITLE = "text hunter (alpha)";
    public static final int APP_WIDTH = 800;
    public static final int APP_HEIGHT = 600;
    public static final String NEW_TAB_BUTTON_STRING = "new tab";
    public static final String SEARCHES_TAB_TEXT = "searches";
    public static final String LOG_TAB_TEXT = "log";
    public static final String OPTIONS_TAB_TEXT = "options";
    public static final String SEARCH_STRING_LABEL = "search:";
    public static final String REGEX_CHECKBOX = "regex";
    public static final String SEARCH_FILE_CONTENT_CHECKBOX = "content";
    public static final String SEARCH_FILE_NAMES_CHECKBOX = "names";
    public static final String CASE_SENSITIVE_CHECKBOX = "case-sensitive";
    public static final String BEGIN_SEARCH_BUTTON = "launch";
    public static final String CANCEL_SEARCH_BUTTON = "cancel";
    public static final boolean MAIN_TOOL_BARS_FLOATABLE = false;
    public static final boolean INTERNAL_TOOL_BARS_FLOATABLE = false;
    public static final String INTERNAL_CANCEL_SEARCH_BUTTON = "cancel";
    public static final String INTERNAL_EXPORT_SEARCH_BUTTON = "export";
    public static final boolean INTERNAL_EXPORT_SEARCH_BUTTON_ENABLED_BEFORE_SEARCH = false;
    public static final boolean INTERNAL_EXPORT_SEARCH_BUTTON_ENABLED_AFTER_SEARCH = true;
    public static final boolean INTERNAL_CANCEL_SEARCH_BUTTON_ENABLED_AFTER_SEARCH = false;
    public static final String PATH_LABEL = "path:";
    public static final String SUBDIR_CHECKBOX = "subdirs";
    public static final String PATH_BROWSE_BUTTON = "browse...";
    public static final String FILTER_LABEL = "filter:";
    public static final String FILTERED_SEARCH_CHECKBOX = "filtered search";
    public static final String REGEX_FILTER_CHECKBOX = "regex filter";
    public static final String DEFAULT_PATH_SYSTEM_PROPERTY = "user.home";
    public static final String COLUMN_ONE_TITLE = "filename";
    public static final String COLUMN_TWO_TITLE = "path";
    public static final String COLUMN_THREE_TITLE = "line";
    public static final String COLUMN_FOUR_TITLE = "text";
    public static final String[] COLUMN_NAMES = {COLUMN_ONE_TITLE, COLUMN_TWO_TITLE, COLUMN_THREE_TITLE, COLUMN_FOUR_TITLE};
    public static final int COLUMN_ONE_WIDTH = 200;
    public static final int COLUMN_TWO_WIDTH = 50;
    public static final int COLUMN_THREE_WIDTH = 50;
    public static final int COLUMN_FOUR_WIDTH = 1200;
    public static final String PATH_SEARCH_DIALOG_TITLE = "select parent folder for search";
    public static final String EXPORT_DIALOG_TITLE = "select filename for export";
    public static final String THREAD_STATUS_NOT_STARTED = "not yet started";
    public static final String THREAD_STATUS_COMPLETED = "<font color=\"blue\">completed</font>";
    public static final String THREAD_STATUS_IN_PROGRESS = "<font color=\"green\">running</font>";
    public static final String THREAD_STATUS_CANCELLED = "<font color=\"red\">cancelled</font>";
    public static final String THREAD_STATUS_CANCELLING = "<font color=\"orange\">cancelling</font>";
    public static final String THREAD_STATUS_UNKNOWN = "<font color=\"black\">unknown</font>";
    public static final int INTERNAL_FRAME_WIDTH = 300;
    public static final int INTERNAL_FRAME_HEIGHT = 300;
    public static final int INTERNAL_FRAME_X_OFFSET = 30;
    public static final int INTERNAL_FRAME_Y_OFFSET = 30;
    public static final String DEFAULT_SEARCH_LABEL_TEXT = "<html><head></head><body><table><th width=\"100%\">initializing search...</th></table></body></html>";
}
