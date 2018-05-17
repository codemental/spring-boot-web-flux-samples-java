## Description
This repository contains samples of various prototype projects built with Spring Boot 2 and Spring WebFlux.

## Projects

### http-adapter
Sample of an HTTP integration service between a client using one API and a backend using a different API.
##### Setup
`cd http-adapter`

`mvn clean install`

### jooq-notx - work in progress
Sample service that uses jOOQ to query a postgres database. Database access is non-transactional (for now).

##### Setup
`cd jooq-notx`

`./jooq-notx-service/src/sql/setup/setup-db.sh`

`mvn clean install`
