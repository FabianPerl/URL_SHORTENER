package io.twodigits.urlshortener.util;

import org.apache.commons.codec.digest.DigestUtils;

import java.time.LocalDateTime;

public class UtilImpl implements Util
{
    @Override
    public String urlToUUID(final String url) {
        //TODO: use another algorithm
        return DigestUtils.sha256Hex(url).substring(0, 10) + String.valueOf(LocalDateTime.now().getNano()).substring(0, 5);
    }
}
