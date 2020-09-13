# URL Shortener Service
This project contains a simple URL Shortener service which exposes its functionality via a Web Service API.

## Assignment
The implementation of this service is not quite complete. Implement the following features:

- A user can add a website URL for which a short URL is created and stored in the database. The short URL must have a unique ID
  consisting of alphanumeric characters.
- The service exposes a Web Service API for providing appropriate CRUD operations to consumers.
- *Optional*: The Web Service API accepts and provides data in multiple formats, e.g. JSON, XML, etc.
- When opening a short URL in a Web Browser the website of the original URL shows up.
- Whenever someone opens a short URL a set of statistics is stored (number of calls, date and time, user agent, referrer, etc.).
- These statistics can be retrieved via the Web Service API.

These features are sorted by priority and therefore should be implemented in that order. It's not necessary to implement
all of these features. However, you should spend at least 3 - 4 hours on them. Please submit the assignment 3 days after
it has been send to you by pushing the code to a private repository on GitHub and [add the collaborators](https://docs.github.com/en/github/setting-up-and-managing-your-github-user-account/inviting-collaborators-to-a-personal-repository)
which were specified separately.

Thank you for taking your time.

**Happy coding!**

## TODO 
- [ ] Create PUT Method to change short URL by the creator
- [ ] Validation of user input
- [ ] Appropriate UUID-Algorithm
- [ ] User Authentication

## Notice
The program was changed at the beginning. Now a Unique ID is used to store the links, so that a short URL can be changed anytime by the creator

## Work with the Project

### Build
This project uses [Maven](https://maven.apache.org) as its build tool. In order to build, test and package the application
run the following command:

```bash
mvn clean package
```

## Run
Once the application has been build and packaged successfully, you'll find a JAR file in the `target` folder. The file
is called `url-shortener.jar`. Use the following command to run the application:

```bash
java -jar target/url-shortener.jar
```

After a few seconds the application should have started successfully. You can access the Web Service endpoints via
[http://localhost:8080](http://localhost:8080) now.

## Commands

### POST: Add url (shortUrl is optional)

`http://localhost:8080/{user}/url`
```json
{
  "longUrl": "https://www.convertjson.com/json-to-xml.htm",
  "shortUrl": "Test2"
}
```
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<root>
  <longUrl>https://www.convertjson.com/json-to-xml.htm</longUrl>
  <shortUrl>Test2</shortUrl>
</root>
```

### GET: ListUrls

`http://localhost:8080/{user}/listUrls`

### DELETE: Delete url

`http://localhost:8080/{user}/url/{id}`

### GET: Show Statistic

`http://localhost:8080/{user}/statistic/{id}`

### GET: Get Url

`http://localhost:8080/url/{id}`
