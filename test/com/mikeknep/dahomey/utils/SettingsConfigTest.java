package com.mikeknep.dahomey.utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class SettingsConfigTest {
    String[] args = {"-p", "8399", "-d", "test/sample_files/", "-r", "mock.jar"};
    SettingsConfig config = new SettingsConfig(args);

    String[] emptyArgs = {};
    SettingsConfig defaultConfig = new SettingsConfig(emptyArgs);


    @Test
    public void itSetsPortNumberAsInteger() {
        assertEquals(8399, config.getPort());
    }

    @Test
    public void itSetsDirectoryToServe() {
        assertEquals("test/sample_files/", config.getDirectory());
    }

    @Test
    public void itSetsRoutingApplication() {
        assertEquals("mock.jar", config.getRouter());
    }

    @Test
    public void itDefaultsToPort1961() {
        assertEquals(1961, defaultConfig.getPort());
    }

    @Test
    public void itDefaultsToLocalPublicFolder() {
        assertEquals("public", defaultConfig.getDirectory());
    }

    @Test
    public void itDefaultsToNullRouter() {
        assertEquals(null, defaultConfig.getRouter());
    }


    //
    // Bad port tests
    //
    @Test
    public void testPortTooLow() {
        String[] badArgs = {"-p", "90"};
        SettingsConfig badConfig = new SettingsConfig(badArgs);
        assertEquals(1961, badConfig.getPort());
    }

    @Test
    public void testPortTooLarge() {
        String[] badArgs = {"-p", "888888"};
        SettingsConfig badConfig = new SettingsConfig(badArgs);
        assertEquals(1961, badConfig.getPort());
    }

    @Test
    public void testPortFlagNoValue() {
        String[] badArgs = {"-p"};
        SettingsConfig badConfig = new SettingsConfig(badArgs);
        assertEquals(1961, badConfig.getPort());
    }

    @Test
    public void testPortNotInteger() {
        String[] badArgs = {"-p", "NotAnInteger"};
        SettingsConfig badConfig = new SettingsConfig(badArgs);
        assertEquals(1961, badConfig.getPort());
    }

    @Test
    public void testMultiplePortFlags() {
        String[] badArgs = {"-p", "-p", "5000"};
        SettingsConfig badConfig = new SettingsConfig(badArgs);
        assertEquals(1961, badConfig.getPort());
    }

    @Test
    public void testMultiplePortFlagsDifferentOrder() {
        String[] badArgs = {"-p", "3000", "-p", "4000"};
        SettingsConfig badConfig = new SettingsConfig(badArgs);
        assertEquals(3000, badConfig.getPort());
    }


    //
    // Bad directory tests
    //
    @Test
    public void testDirectoryFlagNoValue() {
        String[] badArgs = {"-d"};
        SettingsConfig badConfig = new SettingsConfig(badArgs);
        assertEquals("public", badConfig.getDirectory());
    }

    @Test
    public void testMultipleDirectoryFlags() {
        String[] badArgs = {"-d", "-d", "some/directory"};
        SettingsConfig badConfig = new SettingsConfig(badArgs);
        assertEquals("public", badConfig.getDirectory());
    }

    @Test
    public void testMultipleDirectoryFlagsDifferentOrder() {
        String[] badArgs = {"-d", "some/dir", "-d", "other/directory"};
        SettingsConfig badConfig = new SettingsConfig(badArgs);
        assertEquals("some/dir", badConfig.getDirectory());
    }
}