package io.twodigits.urlshortener.exceptions;

public class URLNotFoundException extends URLShortenerException
{
    public URLNotFoundException() {
        super("URL NOT FOUND");
    }
}
