package io.twodigits.urlshortener.model;

public class URLRedirection
{
    private String longUrl;
    private String shortUrl;

    public String getLongUrl()
    {
        return longUrl;
    }

    public void setLongUrl(String longUrl)
    {
        this.longUrl = longUrl;
    }

    public String getShortUrl()
    {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl)
    {
        this.shortUrl = shortUrl;
    }
}
