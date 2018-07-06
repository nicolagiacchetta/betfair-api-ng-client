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

It is possible but highly discouraged to provide via the `BetfairClient.Builder` a custom implementation of the `it.nicolagiacchetta.betfair.utils.HttpClient` and a `com.fasterxml.jackson.databind.ObjectMapper`. In particular before reusing your application `com.fasterxml.jackson.databind.ObjectMapper`, be sure that its custom configuration does not break the compatibility with the JSON format expected by the Betfair Exchange API-NG.   

### Examples
Some examples showing how to use the `BetfairClient` can be found [here](/src/main/java/it/nicolagiacchetta/betfair/Examples.java).

To run the examples, substitute the values of following variables defined at the top of the `main` method with your real Betfair credentials: 
```Java
String username = "YOUR_BETFAIR_USERNAME";
String password = "YOUR_BETFAIR_PASSWORD";
String appKey = "YOUR_BETFAIR_APPKEY";
```

## Supported APIs 
The project currently supports the following operations:

* Login & Session Management described [here](http://docs.developer.betfair.com/docs/pages/viewpage.action?pageId=3834909#Login&SessionManagement-Logout)
* List Events (`exchange/betting/rest/v1.0/listEvents/`)

