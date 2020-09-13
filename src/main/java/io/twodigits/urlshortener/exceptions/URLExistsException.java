package io.twodigits.urlshortener.exceptions;

public class URLExistsException extends URLShortenerException
{
    public URLExistsException() {
        super("URL EXISTS ALREADY");
    }
}
