package io.twodigits.urlshortener.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "redirection")
public class Redirection
{
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name="urlsId")
    private URL url;

    private LocalDateTime redirectionDate;
    private String userAgent;
    private String sourceHost;

    public Redirection () {

    }

    public Redirection (URL url, RedirectionInfo redirectionInfo) {
        this.url = url;
        this.redirectionDate = redirectionInfo.getLocalDateTime();
        this.userAgent = redirectionInfo.getUseragent();
        this.sourceHost = redirectionInfo.getHost();
    }

    public Long getId()
    {
        return id;
    }

    public LocalDateTime getRedirectionDate()
    {
        return redirectionDate;
    }

    public String getUserAgent()
    {
        return userAgent;
    }

    public void setUserAgent(String userAgent)
    {
        this.userAgent = userAgent;
    }

    public String getSourceHost()
    {
        return sourceHost;
    }

    public void setSourceHost(String sourceHost)
    {
        this.sourceHost = sourceHost;
    }

    public void setUrl(URL url)
    {
        this.url = url;
    }

    public URL getUrl()
    {
        return url;
    }
}
