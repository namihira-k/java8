/**
 * Copyright 2015 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

import static org.junit.Assert.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class PaserTest {

    @Test
    public void test_getLinks() throws Exception{
        // prepare
        final String url = "http://horstmann.com";

        // action
        CompletableFuture<List<String>> links = CompletableFuture.supplyAsync(() -> URLUtils.readPage(url))
                .thenApply(Parser::getLinks);

        // check
        assertEquals(49, links.get().size());

        ForkJoinPool.commonPool().awaitQuiescence(10, TimeUnit.SECONDS);
    }

    @Test(expected = NullPointerException.class)
    public void test_getLinks_null() throws Exception{
        // prepare
        // - nothing

        // action
        Parser.getLinks(null);

        // check
        // - nothing
    }



}