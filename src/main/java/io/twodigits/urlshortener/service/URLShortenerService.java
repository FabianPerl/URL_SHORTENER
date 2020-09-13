package io.twodigits.urlshortener.service;

import io.twodigits.urlshortener.exceptions.URLShortenerException;
import io.twodigits.urlshortener.model.Redirection;
import io.twodigits.urlshortener.model.RedirectionInfo;
import io.twodigits.urlshortener.model.URL;
import io.twodigits.urlshortener.model.URLRedirection;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

public interface URLShortenerService {
    /**
     * Get a list of all URLs that belong to a user.
     *
     * @param user
     * @return a collection of users
     */
    Iterable<URL> listURLs(String user);

    /**
     * Add a new URL to the collection of URLs for a user.
     *
     * @param user
     * @param urlRedirection
     * @return The URL object which has been created
     * @throws URLShortenerException
     */
    URL addURL(String user, URLRedirection urlRedirection) throws URLShortenerException;

    /**
     * Get a specific URL of a user by its ID.
     * @param user
     * @param id
     * @return The URL object
     */
    Optional<URL> getURL(String user, String id);

    /**
     * Return a specific URL by ID.
     *
     * @param shortUrl
     * @return The URL object
     */
    Optional<URL> getURL(String shortUrl);

    /**
     * Delete a specific URL which belongs to a user.
     * @param user
     * @param id
     */
    void deleteURL(String user, String id);

    /**
     * Searches for the original URL
     * @param shortUrl
     * @param redirectionInfo additional info
     * @return
     * @throws URLShortenerException
     */
    String getRedirectionUrl (String shortUrl, RedirectionInfo redirectionInfo) throws URLShortenerException;

    /**
     * Get statistics for URL
     * @param user
     * @param id
     * @return
     * @throws URLShortenerException
     */
    Iterable<Redirection> getStatistic (String user, String id) throws URLShortenerException;
}
