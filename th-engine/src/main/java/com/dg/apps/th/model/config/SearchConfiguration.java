package com.dg.apps.th.model.config;

import com.dg.apps.th.engine.util.BooleanUtility;
import com.dg.apps.th.model.Constants;
import com.dg.apps.th.model.pattern.build.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.regex.Pattern;

/**
 * Search configuration.
 */
public class SearchConfiguration {
    @Getter
    @Setter
    private volatile String searchString;
    @Getter
    @Setter
    private volatile String pathString;
    @Getter
    @Setter
    private volatile String filterString;

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
    private volatile boolean filteredSearch;
    @Getter
    @Setter
    private volatile boolean regexFilter;

    @Getter
    @Setter
    private volatile int threadCount;
    @Getter
    @Setter
    private volatile int threadsCompleteSleepTime;

    @Getter
    @Setter
    private volatile FilesystemEnumerationConfiguration recursingSubdirectories;

    /**
     * Create a new instance.
     */
    private SearchConfiguration() {
        // only allowed internally
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

    /**
     * Simple builder factory.
     */
    public static SearchConfigurationBuilder builder() {
        return new SearchConfigurationBuilder()
                .withSearchString(Constants.SEARCH_STRING_VALUE_DEFAULT)
                .withPathString(Constants.PATH_STRING_VALUE_DEFAULT)
                .withFilterString(Constants.FILTER_STRING_VALUE_DEFAULT)

                .isCaseSensitive(Constants.CASE_SENSITIVE_VALUE_DEFAULT)
                .isRegex(Constants.REGEX_SEARCH_VALUE_DEFAULT)
                .isSearchFileContent(Constants.SEARCH_FILE_CONTENT_DEFAULT)
                .isSearchFileNames(Constants.SEARCH_FILE_NAMES_DEFAULT)
                .isFilteredSearch(Constants.FILTERED_SEARCH_VALUE_DEFAULT)
                .isRegexFilter(Constants.REGEX_FILTER_VALUE_DEFAULT)

                .withThreadCount(Constants.SEARCH_THREAD_COUNT_DEFAULT)
                .withThreadCompleteSleepTime(Constants.SEARCH_THREAD_COMPLETE_SLEEP_TIME_MS)

                .isRecursingSubdirectories(Constants.RECURSE_SUBDIRECTORIES_VALUE_DEFAULT.isRecursive());
    }

    /**
     * Builder for search config.
     */
    public static class SearchConfigurationBuilder implements Builder<SearchConfiguration> {
        private String theSearchString;
        private String thePathString;
        private String theFilterString;
        private Boolean theCaseSensitive;
        private Boolean theRegex;
        private Boolean theSearchFileContent;
        private Boolean theSearchFileNames;
        private Boolean theFilteredSearch;
        private Boolean theRegexFilter;
        private Boolean theRecursingSubdirectories;
        private Integer theThreadCount;
        private Integer theThreadCompleteSleepTime;

        /**
         * Feed the builder.
         */
        public SearchConfigurationBuilder withSearchString(final String input) {
            this.theSearchString = input;
            return this;
        }

        /**
         * Feed the builder.
         */
        public SearchConfigurationBuilder withPathString(final String input) {
            this.thePathString = input;
            return this;
        }

        /**
         * Feed the builder.
         */
        public SearchConfigurationBuilder withFilterString(final String input) {
            this.theFilterString = input;
            return this;
        }

        /**
         * Feed the builder.
         */
        public SearchConfigurationBuilder isCaseSensitive(final boolean input) {
            this.theCaseSensitive = input;
            return this;
        }

        /**
         * Feed the builder.
         */
        public SearchConfigurationBuilder isRegex(final boolean input) {
            this.theRegex = input;
            return this;
        }

        /**
         * Feed the builder.
         */
        public SearchConfigurationBuilder isSearchFileContent(final boolean input) {
            this.theSearchFileContent = input;
            return this;
        }

        /**
         * Feed the builder.
         */
        public SearchConfigurationBuilder isSearchFileNames(final boolean input) {
            this.theSearchFileNames = input;
            return this;
        }

        /**
         * Feed the builder.
         */
        public SearchConfigurationBuilder isFilteredSearch(final boolean input) {
            this.theFilteredSearch = input;
            return this;
        }

        /**
         * Feed the builder.
         */
        public SearchConfigurationBuilder isRegexFilter(final boolean input) {
            this.theRegexFilter = input;
            return this;
        }

        /**
         * Feed the builder.
         */
        public SearchConfigurationBuilder withThreadCount(final int input) {
            this.theThreadCount = input;
            return this;
        }

        /**
         * Feed the builder.
         */
        public SearchConfigurationBuilder withThreadCompleteSleepTime(final int input) {
            this.theThreadCompleteSleepTime = input;
            return this;
        }

        /**
         * Feed the builder.
         */
        public SearchConfigurationBuilder isRecursingSubdirectories(final boolean input) {
            this.theRecursingSubdirectories = input;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public SearchConfiguration build() {
            final SearchConfiguration sc = new SearchConfiguration();

            sc.setSearchString(theSearchString != null ? theSearchString : Constants.SEARCH_STRING_VALUE_DEFAULT);
            sc.setPathString(thePathString != null ? thePathString : Constants.PATH_STRING_VALUE_DEFAULT);
            sc.setFilterString(theFilterString != null ? theFilterString : Constants.FILTER_STRING_VALUE_DEFAULT);

            sc.setCaseSensitive(theCaseSensitive != null ? theCaseSensitive : Constants.CASE_SENSITIVE_VALUE_DEFAULT);
            sc.setRegex(theRegex != null ? theRegex : Constants.REGEX_SEARCH_VALUE_DEFAULT);
            sc.setSearchFileContent(theSearchFileContent != null ? theSearchFileContent : Constants.SEARCH_FILE_CONTENT_DEFAULT);
            sc.setSearchFileNames(theSearchFileNames != null ? theSearchFileNames : Constants.SEARCH_FILE_NAMES_DEFAULT);
            sc.setFilteredSearch(theFilteredSearch != null ? theFilteredSearch : Constants.FILTERED_SEARCH_VALUE_DEFAULT);
            sc.setRegexFilter(theRegexFilter != null ? theRegexFilter : Constants.REGEX_FILTER_VALUE_DEFAULT);

            sc.setThreadCount(theThreadCount != null ? theThreadCount : Constants.SEARCH_THREAD_COUNT_DEFAULT);
            sc.setThreadsCompleteSleepTime(theThreadCompleteSleepTime != null ? theThreadCompleteSleepTime : Constants.SEARCH_THREAD_COMPLETE_SLEEP_TIME_MS);

            sc.setRecursingSubdirectories(theRecursingSubdirectories != null ? FilesystemEnumerationConfiguration.deriveConfiguration(theRecursingSubdirectories) : Constants.RECURSE_SUBDIRECTORIES_VALUE_DEFAULT);

            return sc;
        }
    }
}
