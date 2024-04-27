package com.dg.apps.th.model;

import com.dg.apps.th.model.config.FilesystemEnumerationConfiguration;

/**
 * Various constant values.
 */
public class Constants {
    /* error constants */
    public static final String DO_NOT_INSTANTIATE = "do not instantiate";

    /* search config constants */
    public static final String SEARCH_STRING_VALUE_DEFAULT = "";
    public static final boolean CASE_SENSITIVE_VALUE_DEFAULT = false;
    public static final boolean REGEX_SEARCH_VALUE_DEFAULT = false;
    public static final String PATH_STRING_VALUE_DEFAULT = "";
    public static final FilesystemEnumerationConfiguration RECURSE_SUBDIRECTORIES_VALUE_DEFAULT = FilesystemEnumerationConfiguration.Recursive;
    public static final boolean FILTERED_SEARCH_VALUE_DEFAULT = false;
    public static final boolean REGEX_FILTER_VALUE_DEFAULT = false;
    public static final String FILTER_STRING_VALUE_DEFAULT = "";
    public static final boolean SEARCH_FILE_CONTENT_DEFAULT = true;
    public static final boolean SEARCH_FILE_NAMES_DEFAULT = true;
}
