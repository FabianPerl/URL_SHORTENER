package io.twodigits.urlshortener.exceptions;

public class URLNotValidException extends URLShortenerException
{
    public URLNotValidException() {
        super("URL IS INVALID");
    }
}
