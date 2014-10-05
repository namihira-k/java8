/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */
package jp.co.namihira.java8;

import static org.junit.Assert.*;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

public class CharacterStreamTest {

    @Test
    public void test_characterStream() {
        //prepare
        final String origin = "boat";

        // action
        Stream<Character> results = CharacterStream.characterStream(origin);

        // check
        final String RESULT_STRING = results.map(Object::toString).collect(Collectors.joining());
        assertEquals(origin, RESULT_STRING);
    }

    @Test
    public void test_characterStream_lengthZeroString() {
        //prepare
        final String origin = "";

        // action
        Stream<Character> results = CharacterStream.characterStream(origin);

        // check
        final String RESULT_STRING = results.map(Object::toString).collect(Collectors.joining());
        assertEquals(origin, RESULT_STRING);
    }

    @Test
    public void test_characterStream_null() {
        //prepare
        final String origin = null;

        // action
        Stream<Character> results = CharacterStream.characterStream(origin);

        // check
        final String RESULT_STRING = results.map(Object::toString).collect(Collectors.joining());
        assertEquals("", RESULT_STRING);
    }

}
