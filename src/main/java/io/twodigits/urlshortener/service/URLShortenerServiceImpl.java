package io.twodigits.urlshortener.service;

import io.twodigits.urlshortener.exceptions.URLExistsException;
import io.twodigits.urlshortener.exceptions.URLNotFoundException;
import io.twodigits.urlshortener.exceptions.URLNotValidException;
import io.twodigits.urlshortener.exceptions.URLShortenerException;
import io.twodigits.urlshortener.model.Redirection;
import io.twodigits.urlshortener.model.RedirectionInfo;
import io.twodigits.urlshortener.model.URL;
import io.twodigits.urlshortener.model.URLRedirection;
import io.twodigits.urlshortener.repository.RedirectionRepository;
import io.twodigits.urlshortener.repository.URLRepository;
import io.twodigits.urlshortener.util.UtilImpl;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class URLShortenerServiceImpl implements URLShortenerService {

    private URLRepository urlRepository;
    private RedirectionRepository redirectionRepository;
    private UtilImpl util = new UtilImpl();

    @Autowired
    public URLShortenerServiceImpl (URLRepository urlRepository, RedirectionRepository redirectionRepository) {
        this.urlRepository = urlRepository;
        this.redirectionRepository = redirectionRepository;
    }

    @Override
    public Iterable<URL> listURLs(String user) {
        return urlRepository.findByUser(user);
    }

    @Override
    public URL addURL(String user, URLRedirection urlRedirection) throws URLShortenerException
    {
        UrlValidator urlValidator = new UrlValidator();
        URL urlModel = new URL();

        if (urlRedirection.getLongUrl() != null && !urlValidator.isValid(urlRedirection.getLongUrl()))
        {
            throw new URLNotValidException();
        }

        if (this.getURL(urlRedirection.getShortUrl()).isPresent()) {
            throw new URLExistsException();
        }

        urlModel.setUser(user);
        urlModel.setUrl(urlRedirection.getLongUrl());
        String uuid = util.urlToUUID(urlRedirection.getLongUrl());

        urlModel.setId(uuid);

        if (urlRedirection.getShortUrl() != null && !urlRedirection.getShortUrl().isEmpty()) {
            urlModel.setShortUrl(urlRedirection.getShortUrl());
        } else {
            urlModel.setShortUrl(uuid);
        }

        urlRepository.save(urlModel);
        return urlModel;
    }

    @Override
    public Optional<URL> getURL(String user, String id) {
        Optional<URL> url = urlRepository.findByIdAndUser(id, user);
        if (url.isEmpty()) {
            url = urlRepository.findByShortUrlAndUser(id, user);
        }

        return url;
    }

    @Override
    public Optional<URL> getURL(String shortUrl) {
        return urlRepository.findByShortUrl(shortUrl);
    }

    @Override
    public void deleteURL(String user, String id) throws URLShortenerException {
        Optional<URL> url = this.getURL(user, id);

        if (url.isPresent()) {
           URL presentUrl = url.get();
           redirectionRepository.deleteByUrl(presentUrl);
           urlRepository.delete(presentUrl);
        } else {
            throw new URLNotFoundException();
        }
    }

    @Override
    public String getRedirectionUrl (final String shortUrl, RedirectionInfo requestInfos) throws URLShortenerException {
        Optional<URL> url = this.getURL(shortUrl);

        if (url.isEmpty()) {
            throw new URLNotFoundException();
        }

        URL presentUrl = url.get();
        presentUrl.setCounter(presentUrl.getCounter() + 1);

        Redirection redirection = new Redirection(presentUrl, requestInfos);
        redirectionRepository.save(redirection);

        return presentUrl.getURL();
    }

    @Override
    public Iterable<Redirection> getStatistic (String user, String id) throws URLShortenerException {
        Optional<URL> urlOptional = this.getURL(user, id);
        Iterable<Redirection> redirection = null;

        if (urlOptional.isPresent()) {
            redirection = redirectionRepository.findByUrl(urlOptional.get());
        } else {
            throw new URLNotFoundException();
        }

        return redirection;
    }
}
