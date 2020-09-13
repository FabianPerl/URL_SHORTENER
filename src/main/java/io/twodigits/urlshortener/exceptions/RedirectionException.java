package io.twodigits.urlshortener.exceptions;

public class RedirectionException extends URLShortenerException
{
    public RedirectionException () {
       super("REDIRECTION FAILED");
    }
}
