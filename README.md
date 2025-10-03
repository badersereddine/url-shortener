# URL Shortener API

A normal URL Shortener API developed with  **Spring Boot**.

## Start proect
Clone the repository and run the application:
```bash
./mvnw spring-boot:run

##Healthcheck
GET /health
Response with OK.

##Hello (test endpoint)
GET /hello

##Create short URL
POST /api/v1/links
Content-Type: application/json

{
  "url": "https://www.google.com"
}

Response:
{
  "code": "Ab3x9KQ",
  "shortUrl": "http://localhost:8080/Ab3x9KQ",
  "originalUrl": "https://www.google.com"
}

##Retrieve details
GET /api/v1/links/{code}

##Redirect
GET /r/{code}



