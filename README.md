
# GDS Client

This is a java client for the ECC GDS and ECC GeoView. 

## Run tests and examples

To run the junit tests and examples, please start with a `URL_PREFIX` environment variable or java property like the following. Switch out username, password host and context with proper values for the user and service to test with.

```
URL_PREFIX="https://username:password@host/context" mvn test
``` 