package io.twodigits.urlshortener.controller;

import io.twodigits.urlshortener.exceptions.RedirectionException;
import io.twodigits.urlshortener.exceptions.URLNotFoundException;
import io.twodigits.urlshortener.model.Redirection;
import io.twodigits.urlshortener.model.RedirectionInfo;
import io.twodigits.urlshortener.model.URL;
import io.twodigits.urlshortener.model.URLRedirection;
import io.twodigits.urlshortener.service.URLShortenerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

@RestController
public class URLShortenerRestControllerImpl {

    private final static Logger LOG = LoggerFactory.getLogger(URLShortenerRestControllerImpl.class);
    private final URLShortenerService urlShortenerService;

    @Autowired
    public URLShortenerRestControllerImpl(URLShortenerService urlShortenerService){
        this.urlShortenerService = urlShortenerService;
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public String home() {
        return "Home";
    }

    @PostMapping(path = "{user}/url", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public URL addUrl(@PathVariable String user, @RequestBody URLRedirection urlRedirection)
    {
        LOG.info("Add URL " + urlRedirection.getLongUrl() + " to user " + user);
        return urlShortenerService.addURL(user, urlRedirection);
    }

    @PutMapping(path = "{user}/changeUrl/{id}")
    @ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
    public void changeUrl (@PathVariable String user, @PathVariable String id, @RequestBody String newUrl) {
        LOG.info("Change url for user " + user + " with id " + id);
    }

    @GetMapping(path = "url/{shortUrl}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public URL getUrl (@PathVariable String shortUrl) {
        LOG.info("Get url for shortUrl " + shortUrl);
        Optional<URL> optionalURL = urlShortenerService.getURL(shortUrl);
        if(optionalURL.isEmpty()) throw new URLNotFoundException();

        return optionalURL.get();
    }

    @DeleteMapping(path = "{user}/url/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUrl(@PathVariable String user, @PathVariable String id, HttpServletResponse httpServletResponse) {
       LOG.info("Delete ID " + id + " from user " + user);
       urlShortenerService.deleteURL(user, id);
    }

    @GetMapping(path = "{shortUrl}")
    public void redirect(@PathVariable String shortUrl, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        LOG.info("Redirect " + shortUrl);
        try {
            RedirectionInfo redirectionInfo = new RedirectionInfo();
            redirectionInfo.setHost(httpServletRequest.getHeader("Host"));
            redirectionInfo.setUseragent(httpServletRequest.getHeader("User-Agent"));
            redirectionInfo.setLocalDateTime(LocalDateTime.now());

            httpServletResponse.sendRedirect(urlShortenerService.getRedirectionUrl(shortUrl, redirectionInfo));
        } catch (IOException e) {
            throw new RedirectionException();
        }
    }

    @GetMapping(path = "{user}/listUrls", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public List<URL> getUrls (@PathVariable String user) {
       LOG.info("Get urls for user " + user);
       Iterable<URL> urls = urlShortenerService.listURLs(user);
       List<URL> urlList = new ArrayList<>();
       urls.forEach(urlList::add);

       return urlList;
    }

    @GetMapping(value = "{user}/statistic/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public List<Redirection> getStatistic(@PathVariable String user, @PathVariable String id) {
        LOG.info("Get statistic for id " + id + " and user " + user);
        Iterable<Redirection> redirection = urlShortenerService.getStatistic(user, id);
        List<Redirection> redirectionList = new ArrayList<>();
        redirection.forEach(redirectionList::add);
        return redirectionList;
    }
}
