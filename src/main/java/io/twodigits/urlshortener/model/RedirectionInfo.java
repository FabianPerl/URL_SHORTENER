package io.twodigits.urlshortener.model;

import java.time.LocalDateTime;

public class RedirectionInfo
{
    private String useragent;
    private String host;
    private LocalDateTime localDateTime;

    public String getUseragent()
    {
        return useragent;
    }

    public void setUseragent(String useragent)
    {
        this.useragent = useragent;
    }

    public String getHost()
    {
        return host;
    }

    public void setHost(String host)
    {
        this.host = host;
    }

    public LocalDateTime getLocalDateTime()
    {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime)
    {
        this.localDateTime = localDateTime;
    }
}
