package io.twodigits.urlshortener.exceptions;

public class URLShortenerException extends RuntimeException
{
    URLShortenerException (String error) {
        super(error);
    }
}
