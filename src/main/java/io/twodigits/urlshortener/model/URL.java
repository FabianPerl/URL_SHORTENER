package io.twodigits.urlshortener.model;

import javax.persistence.*;

@Entity
@Table(name = "urls", uniqueConstraints = {@UniqueConstraint(columnNames = {"shortUrl"})})
public class URL {
    /**
     * The unique ID of an URL
     */
    @Id
    private String id;

    /**
     * The URL for which a short URL is provided
     */
    private String url;

    private Long counter = 0L;

    @Column(name="shortUrl")
    private String shortUrl;

    /**
     * The ID of a user to which this URL belongs
     */
    private String user;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getURL() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return this.user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setShortUrl (String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public String getShortUrl () {
        return this.shortUrl;
    }

    public Long getCounter()
    {
        return this.counter;
    }

    public void setCounter(Long counter)
    {
        this.counter = counter;
    }
}
