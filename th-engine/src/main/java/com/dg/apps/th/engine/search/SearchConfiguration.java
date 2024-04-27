package com.dg.apps.th.engine.search;

import com.dg.apps.th.engine.enumeration.FilesystemEnumerationConfiguration;
import com.dg.apps.th.engine.util.BooleanUtility;

import java.util.regex.Pattern;

public class SearchConfiguration {
    private volatile String _searchString = "";
    private volatile boolean _isCaseSensitive;
    private volatile boolean _isRegex;
    private volatile boolean _searchFileContent;
    private volatile boolean _searchFileNames;

    private volatile String _pathString = "";
    private volatile FilesystemEnumerationConfiguration _isRecursingSubdirectories;

    private volatile boolean _isFilteredSearch;
    private volatile boolean _isRegexFilter;
    private volatile String _filterString = "";

    public SearchConfiguration() {
        _searchString = SearchConstants.SEARCH_STRING_VALUE_DEFAULT;
        _isRegex = SearchConstants.REGEX_SEARCH_VALUE_DEFAULT;
        _isCaseSensitive = SearchConstants.CASE_SENSITIVE_VALUE_DEFAULT;
        _searchFileContent = SearchConstants.SEARCH_FILE_CONTENT_DEFAULT;
        _searchFileNames = SearchConstants.SEARCH_FILE_NAMES_DEFAULT;
        _pathString = SearchConstants.PATH_STRING_VALUE_DEFAULT;
        _isRecursingSubdirectories = SearchConstants.RECURSE_SUBDIRECTORIES_VALUE_DEFAULT;
        _isFilteredSearch = SearchConstants.FILTERED_SEARCH_VALUE_DEFAULT;
        _isRegexFilter = SearchConstants.REGEX_FILTER_VALUE_DEFAULT;
        _filterString = SearchConstants.FILTER_STRING_VALUE_DEFAULT;
    }

    public static SearchConfiguration getDefaultConfiguration() {
        SearchConfiguration config = new SearchConfiguration();

        return config;
    }

    public static SearchConfiguration cleanse(SearchConfiguration input) {
        if (input == null)
            return SearchConfiguration.getDefaultConfiguration();
        else
            return input;
    }

    public Pattern generateSearchStringPattern() {
        if (isRegex())
            return Pattern.compile(getSearchString());
        return null;
    }

    public Pattern generateFileNamePattern() {
        if (isFilteredSearch() && isRegexFilter())
            return Pattern.compile(getFilterString());
        return null;
    }

    public synchronized void setSearchString(String str) {
        _searchString = str;
    }

    public synchronized String getSearchString() {
        return _searchString;
    }

    public synchronized void setRegex(boolean bool) {
        _isRegex = bool;
    }

    public synchronized boolean isRegex() {
        return _isRegex;
    }

    public synchronized void setSearchFileContent(boolean bool) {
        _searchFileContent = bool;
    }

    public synchronized boolean isSearchFileContent() {
        return _searchFileContent;
    }

    public synchronized void setSearchFileNames(boolean bool) {
        _searchFileNames = bool;
    }

    public synchronized boolean isSearchFileNames() {
        return _searchFileNames;
    }

    public synchronized void setCaseSensitive(boolean bool) {
        _isCaseSensitive = bool;
    }

    public synchronized boolean isCaseSensitive() {
        return _isCaseSensitive;
    }

    public synchronized void setPathString(String str) {
        _pathString = str;
    }

    public synchronized String getPathString() {
        return _pathString;
    }

    public synchronized void setRecursingSubdirectories(boolean bool) {
        _isRecursingSubdirectories = FilesystemEnumerationConfiguration.deriveConfiguration(bool);
    }

    public synchronized FilesystemEnumerationConfiguration isRecursingSubdirectories() {
        return _isRecursingSubdirectories;
    }

    public synchronized void setFilteredSearch(boolean bool) {
        _isFilteredSearch = bool;
    }

    public synchronized boolean isFilteredSearch() {
        return _isFilteredSearch;
    }

    public synchronized void setRegexFilter(boolean bool) {
        _isRegexFilter = bool;
    }

    public synchronized boolean isRegexFilter() {
        return _isRegexFilter;
    }

    public synchronized void setFilterString(String str) {
        _filterString = str;
    }

    public synchronized String getFilterString() {
        return _filterString;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("searchString=[");
        builder.append(_searchString);
        builder.append("], isCaseSensitive=[");
        builder.append(BooleanUtility.convertToString(_isCaseSensitive));
        builder.append("], isRegex=[");
        builder.append(BooleanUtility.convertToString(_isRegex));
        builder.append("], searchFileContents=[");
        builder.append(BooleanUtility.convertToString(_searchFileContent));
        builder.append("], searchFileNames][");
        builder.append(BooleanUtility.convertToString(_searchFileNames));
        builder.append("], pathString=[");
        builder.append(_pathString);
        builder.append("], isRecursingSubdirectories=[");
        builder.append(_isRecursingSubdirectories.name());
        builder.append("], isFilteredSearch=[");
        builder.append(BooleanUtility.convertToString(_isFilteredSearch));
        builder.append("], isRegexFilter=[");
        builder.append(BooleanUtility.convertToString(_isRegexFilter));
        builder.append("], filterString=[");
        builder.append(_filterString);
        builder.append("]");

        return builder.toString();
    }
}
