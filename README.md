# Betfair API-NG Java Client
A Java Client to help the implementation of applications and bot that interact with the Betfair Exchange API-NG described [here](http://docs.developer.betfair.com/docs/).

## Getting started
To generate and publish in the project directory the jar of the library run the `jar` and `publish` gradle tasks.


```
./gradlew clean jar publish

```

A `build.sh` script is also provided.

## Build the BetfairClient
The `BetfairClient` class provides an inner `BetfairClient.Builder` that represent the only provided way to build an instance of the `BetfairClient`.

```Java
BetfairClient betfairClient = new BetfairClient.Builder()
                                               .build();

```




