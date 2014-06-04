package com.mikeknep.dahomey.utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class ArgsParserTest {
    String[] args = {"-p", "8399", "-d", "public/", "-a", "public/mock.jar"};
    ArgsParser config = new ArgsParser(args);

    String[] emptyArgs = {};
    ArgsParser defaultConfig = new ArgsParser(emptyArgs);


    @Test
    public void itSetsPortNumberAsInteger() {
        assertEquals(8399, config.getPort());
    }

    @Test
    public void itSetsDirectoryToServe() {
        assertEquals("public/", config.getDirectory());
    }

    @Test
    public void itSetsApplication() {
        assertEquals("public/mock.jar", config.getApplication());
    }

    @Test
    public void itDefaultsToPort1961() {
        assertEquals(1961, defaultConfig.getPort());
    }

    @Test
    public void itDefaultsToLocalPublicFolder() {
        assertEquals("public/", defaultConfig.getDirectory());
    }

    @Test
    public void itDefaultsToNullApplication() {
        assertEquals(null, defaultConfig.getApplication());
    }


    //
    // Bad port tests
    //
    @Test
    public void testPortTooLow() {
        String[] badArgs = {"-p", "90"};
        ArgsParser badConfig = new ArgsParser(badArgs);
        assertEquals(1961, badConfig.getPort());
    }

    @Test
    public void testPortTooLarge() {
        String[] badArgs = {"-p", "888888"};
        ArgsParser badConfig = new ArgsParser(badArgs);
        assertEquals(1961, badConfig.getPort());
    }

    @Test
    public void testPortFlagNoValue() {
        String[] badArgs = {"-p"};
        ArgsParser badConfig = new ArgsParser(badArgs);
        assertEquals(1961, badConfig.getPort());
    }

    @Test
    public void testPortNotInteger() {
        String[] badArgs = {"-p", "NotAnInteger"};
        ArgsParser badConfig = new ArgsParser(badArgs);
        assertEquals(1961, badConfig.getPort());
    }

    @Test
    public void testMultiplePortFlags() {
        String[] badArgs = {"-p", "-p", "5000"};
        ArgsParser badConfig = new ArgsParser(badArgs);
        assertEquals(1961, badConfig.getPort());
    }

    @Test
    public void testMultiplePortFlagsDifferentOrder() {
        String[] badArgs = {"-p", "3000", "-p", "4000"};
        ArgsParser badConfig = new ArgsParser(badArgs);
        assertEquals(3000, badConfig.getPort());
    }


    //
    // Bad directory tests
    //
    @Test
    public void testDirectoryFlagNoValue() {
        String[] badArgs = {"-d"};
        ArgsParser badConfig = new ArgsParser(badArgs);
        assertEquals("public/", badConfig.getDirectory());
    }

    @Test
    public void testMultipleDirectoryFlags() {
        String[] badArgs = {"-d", "-d", "some/directory"};
        ArgsParser badConfig = new ArgsParser(badArgs);
        assertEquals("public/", badConfig.getDirectory());
    }

    @Test
    public void testMultipleDirectoryFlagsDifferentOrder() {
        String[] badArgs = {"-d", "some/dir", "-d", "other/directory"};
        ArgsParser badConfig = new ArgsParser(badArgs);
        assertEquals("some/dir", badConfig.getDirectory());
    }
}