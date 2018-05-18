package com.usermind.usermindsdk.fetch.time;


import com.usermind.usermindsdk.fetch.FetchedData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class TimeLimitedFetchTito implements TimeLimitedFetch {
    private static final Logger LOGGER = LoggerFactory.getLogger(TimeLimitedFetchTito.class);

    @Override
    public FetchedData performTimeLimitedFetch(String accountName, String apiKey) throws NoSuchMethodException {
        LOGGER.info("Running time limited fetch");
        throw new NoSuchMethodException("Time based fetch has not been implemented for this integration type.");
    }

}
