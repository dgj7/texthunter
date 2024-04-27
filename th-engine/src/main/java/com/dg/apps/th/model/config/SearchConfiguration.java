package com.dg.apps.th.model.config;

import com.dg.apps.th.engine.util.BooleanUtility;
import com.dg.apps.th.model.Constants;
import lombok.Getter;
import lombok.Setter;

import java.util.regex.Pattern;

/**
 * Search configuration.
 */
public class SearchConfiguration {
    @Getter
    @Setter
    private volatile String searchString = "";
    @Getter
    @Setter
    private volatile boolean caseSensitive;
    @Getter
    @Setter
    private volatile boolean regex;
    @Getter
    @Setter
    private volatile boolean searchFileContent;
    @Getter
    @Setter
    private volatile boolean searchFileNames;
    @Getter
    @Setter
    private volatile String pathString = "";
    @Getter
    private volatile FilesystemEnumerationConfiguration recursingSubdirectories;
    @Getter
    @Setter
    private volatile boolean filteredSearch;
    @Getter
    @Setter
    private volatile boolean regexFilter;
    @Getter
    @Setter
    private volatile String filterString = "";

    /**
     * Create a new instance.
     */
    public SearchConfiguration() {
        searchString = Constants.SEARCH_STRING_VALUE_DEFAULT;
        regex = Constants.REGEX_SEARCH_VALUE_DEFAULT;
        caseSensitive = Constants.CASE_SENSITIVE_VALUE_DEFAULT;
        searchFileContent = Constants.SEARCH_FILE_CONTENT_DEFAULT;
        searchFileNames = Constants.SEARCH_FILE_NAMES_DEFAULT;
        pathString = Constants.PATH_STRING_VALUE_DEFAULT;
        recursingSubdirectories = Constants.RECURSE_SUBDIRECTORIES_VALUE_DEFAULT;
        filteredSearch = Constants.FILTERED_SEARCH_VALUE_DEFAULT;
        regexFilter = Constants.REGEX_FILTER_VALUE_DEFAULT;
        filterString = Constants.FILTER_STRING_VALUE_DEFAULT;
    }

    /**
     * Specialized setter.
     */
    public synchronized void setRecursingSubdirectories(boolean bool) {
        recursingSubdirectories = FilesystemEnumerationConfiguration.deriveConfiguration(bool);
    }

    /**
     * Get the default configuration.
     */
    public static SearchConfiguration getDefaultConfiguration() {
        return new SearchConfiguration();
    }

    /**
     * Get the default configuration if null is provided.
     */
    public static SearchConfiguration cleanse(final SearchConfiguration input) {
        if (input == null)
            return SearchConfiguration.getDefaultConfiguration();
        else
            return input;
    }

    /**
     * Generate the search string pattern.
     */
    public Pattern generateSearchStringPattern() {
        if (isRegex())
            return Pattern.compile(getSearchString());
        return null;
    }

    /**
     * Generate the file name pattern.
     */
    public Pattern generateFileNamePattern() {
        if (isFilteredSearch() && isRegexFilter())
            return Pattern.compile(getFilterString());
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public String toString() {
        return new StringBuilder()
                .append("searchString=[")
                .append(searchString)
                .append("], isCaseSensitive=[")
                .append(BooleanUtility.convertToString(caseSensitive))
                .append("], isRegex=[")
                .append(BooleanUtility.convertToString(regex))
                .append("], searchFileContents=[")
                .append(BooleanUtility.convertToString(searchFileContent))
                .append("], searchFileNames][")
                .append(BooleanUtility.convertToString(searchFileNames))
                .append("], pathString=[")
                .append(pathString)
                .append("], isRecursingSubdirectories=[")
                .append(recursingSubdirectories.name())
                .append("], isFilteredSearch=[")
                .append(BooleanUtility.convertToString(filteredSearch))
                .append("], isRegexFilter=[")
                .append(BooleanUtility.convertToString(regexFilter))
                .append("], filterString=[")
                .append(filterString)
                .append("]")
                .toString();
    }
}
