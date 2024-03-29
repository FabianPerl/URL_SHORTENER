package io.twodigits.urlshortener.repository;

import io.twodigits.urlshortener.model.URL;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface URLRepository extends CrudRepository<URL, String> {

    /**
     * Find a URL by the identifier of the user.
     *
     * @param user the ID of a user
     * @return a collection of URL objects
     */
    Iterable<URL> findByUser(String user);

    /**
     * Find a URL by the identifier of the user as well as its ID.
     *
     * @param id the unique ID of an URL
     * @param user the ID of a user
     * @return a url
     */
    Optional<URL> findByIdAndUser(String id, String user);

    /**
     * Find a URL by the short url and user
     * @param shortUrl
     * @param user
     * @return
     */
    Optional<URL> findByShortUrlAndUser(String shortUrl, String user);

    /**
     * Find a URL by the short url
     * @param shortUrl
     * @return
     */
    Optional<URL> findByShortUrl(String shortUrl);
}
