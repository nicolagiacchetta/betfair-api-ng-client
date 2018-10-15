package it.nicolagiacchetta.betfair;

import it.nicolagiacchetta.betfair.annotations.Required;
import it.nicolagiacchetta.betfair.entities.EventResult;
import it.nicolagiacchetta.betfair.entities.EventTypeResult;
import it.nicolagiacchetta.betfair.entities.LoginResponse;
import it.nicolagiacchetta.betfair.entities.MarketCatalogue;
import it.nicolagiacchetta.betfair.entities.MarketFilter;
import it.nicolagiacchetta.betfair.entities.MarketType;

import java.util.Locale;

public interface BetfairClient extends AutoCloseable {

    LoginResponse login(@Required String username,
                        @Required String password,
                        @Required String appKey) throws Exception;

    LoginResponse keepAliveSession(@Required String appKey,
                                   @Required String sessionToken) throws Exception;

    LoginResponse logout(@Required String appKey,
                         @Required String sessionToken) throws Exception;

    EventResult[] listEvents(@Required String appKey,
                             @Required String sessionToken,
                             @Required MarketFilter marketFilter) throws Exception;

    EventResult[] listEvents(@Required String appKey,
                             @Required String sessionToken,
                             @Required MarketFilter marketFilter,
                             Locale locale) throws Exception;

    EventTypeResult[] listEventTypes(@Required String appKey,
                                     @Required String sessionToken,
                                     @Required MarketFilter marketFilter) throws Exception;

    EventTypeResult[] listEventTypes(@Required String appKey,
                                     @Required String sessionToken,
                                     @Required MarketFilter marketFilter,
                                     Locale locale) throws Exception;

    MarketType[] listMarketTypes(@Required String appKey,
                                 @Required String sessionToken,
                                 @Required MarketFilter marketFilter) throws Exception;

    MarketType[] listMarketTypes(@Required String appKey,
                                 @Required String sessionToken,
                                 @Required MarketFilter marketFilter,
                                 Locale locale) throws Exception;

    MarketCatalogue[] listMarketCatalogue(@Required String appKey,
                                          @Required String sessionToken,
                                          @Required MarketFilter marketFilter,
                                          Integer maxResults) throws Exception;

    MarketCatalogue[] listMarketCatalogue(@Required String appKey,
                                          @Required String sessionToken,
                                          @Required MarketFilter marketFilter,
                                          Integer maxResults,
                                          Locale locale) throws Exception;
}
