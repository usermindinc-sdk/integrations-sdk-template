package com.usermind.usermindsdk.fetch;

import com.usermind.usermindsdk.TestBase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FullFetchIT extends TestBase {

    private FullFetch fullFetch;

    @BeforeEach
    void setUp() {
        fullFetch = new FullFetch(restTemplate) ;
    }

    @Test
    void testFullFetch() {
        fullFetch.runFullFetch();
    }
}