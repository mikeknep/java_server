import org.junit.Test;

import static org.junit.Assert.*;

public class SettingsConfigTest {
    String[] args = {"-p", "8399", "-d", "/Users/mrk/Desktop"};
    SettingsConfig config = new SettingsConfig(args);

    String[] emptyArgs = {};
    SettingsConfig defaultConfig = new SettingsConfig(emptyArgs);


    @Test
    public void itSetsPortNumberAsInteger() {
        assertEquals(8399, config.getPort());
    }

    @Test
    public void itSetsDirectoryToServe() {
        assertEquals("/Users/mrk/Desktop", config.getDirectory());
    }

    @Test
    public void itDefaultsToPort1961() {
        assertEquals(1961, defaultConfig.getPort());
    }

    @Test
    public void itDefaultsToLocalPublicFolder() {
        assertEquals("public", defaultConfig.getDirectory());
    }


    //
    // Bad port tests
    //
    @Test
    public void testPortTooLow() {
        String[] badArgs = {"-p", "90"};
        SettingsConfig badCongig = new SettingsConfig(badArgs);
        assertEquals(1961, badCongig.getPort());
    }

    @Test
    public void testPortTooLarge() {
        String[] badArgs = {"-p", "888888"};
        SettingsConfig badCongig = new SettingsConfig(badArgs);
        assertEquals(1961, badCongig.getPort());
    }

    @Test
    public void testPortFlagNoValue() {
        String[] badArgs = {"-p"};
        SettingsConfig badCongig = new SettingsConfig(badArgs);
        assertEquals(1961, badCongig.getPort());
    }

    @Test
    public void testPortNotInteger() {
        String[] badArgs = {"-p", "NotAnInteger"};
        SettingsConfig badCongig = new SettingsConfig(badArgs);
        assertEquals(1961, badCongig.getPort());
    }

    @Test
    public void testMultiplePortFlags() {
        String[] badArgs = {"-p", "-p", "5000"};
        SettingsConfig badCongig = new SettingsConfig(badArgs);
        assertEquals(1961, badCongig.getPort());
    }

    @Test
    public void testMultiplePortFlagsDifferentOrder() {
        String[] badArgs = {"-p", "3000", "-p", "4000"};
        SettingsConfig badCongig = new SettingsConfig(badArgs);
        assertEquals(3000, badCongig.getPort());
    }


    //
    // Bad directory tests
    //
    @Test
    public void testDirectoryFlagNoValue() {
        String[] badArgs = {"-d"};
        SettingsConfig badCongig = new SettingsConfig(badArgs);
        assertEquals("public", badCongig.getDirectory());
    }

    @Test
    public void testMultipleDirectoryFlags() {
        String[] badArgs = {"-d", "-d", "some/directory"};
        SettingsConfig badCongig = new SettingsConfig(badArgs);
        assertEquals("public", badCongig.getDirectory());
    }

    @Test
    public void testMultipleDirectoryFlagsDifferentOrder() {
        String[] badArgs = {"-d", "some/dir", "-d", "other/directory"};
        SettingsConfig badCongig = new SettingsConfig(badArgs);
        assertEquals("some/dir", badCongig.getDirectory());
    }
}