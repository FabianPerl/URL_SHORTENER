package io.twodigits.urlshortener.repository;

import io.twodigits.urlshortener.model.Redirection;
import io.twodigits.urlshortener.model.URL;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RedirectionRepository extends CrudRepository<Redirection, String>
{
    public void deleteByUrl(URL url);
    public Iterable<Redirection> findByUrl(URL url);
}
