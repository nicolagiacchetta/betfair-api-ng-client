package it.nicolagiacchetta.betfair;

public class BetfairClients {

    public static BetfairClientBuilder custom() {
        return BetfairClientBuilder.create();
    }

    public static BetfairClient createDefault() {
        return BetfairClientBuilder.create().build();
    }
}
