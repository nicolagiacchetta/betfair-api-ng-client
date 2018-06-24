# Betfair Exchange API-NG Java Client
A Java Client to help the implementation of applications and bots that interact with the Betfair Exchange API-NG described [here](http://docs.developer.betfair.com/docs/).

## Getting started
### Building from Source
To generate and publish in the project directory the jar of the library, run the `jar` and `publish` gradle tasks.


```
./gradlew clean jar publish
```

A `build.sh` script is also provided.

### Build the BetfairClient
The `BetfairClient` class provides an inner `BetfairClient.Builder` that represents the only way to build an instance of the `BetfairClient`.

```Java
BetfairClient betfairClient = new BetfairClient.Builder().build();
```

## Supported APIs 
The project currently supports the following operations:

* Login & Session Management described [here](http://docs.developer.betfair.com/docs/pages/viewpage.action?pageId=3834909#Login&SessionManagement-Logout)


