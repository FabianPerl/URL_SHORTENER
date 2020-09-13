package io.twodigits.urlshortener.util;

public interface Util
{
   /**
    * Generates a unique id for a url
    * @param url
    * @return
    */
   public String urlToUUID (final String url);
}
