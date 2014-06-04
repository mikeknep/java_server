package com.mikeknep.dahomey.utils;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/**
 * Created by mrk on 5/13/14.
 */
public class ArgsParser {
    private int port;
    private String directory;
    private String application;

    public int getPort() { return this.port; }
    public String getDirectory() { return this.directory; }
    public String getApplication() { return this.application; }

    public ArgsParser(String[] args) {
        List<String> arguments = Arrays.asList(args);
        setPortNumber(arguments);
        setDirectory(arguments);
        setApplication(arguments);
    }


    private void setPortNumber(List<String> arguments) {
        this.port = 1961;
        if (arguments.contains("-p")) {
            String portString = getFlagValue(arguments, "-p");
            if (isValidPort(portString)) {
                this.port = Integer.parseInt(portString);
            }
        }
    }

    private void setDirectory(List<String> arguments) {
        this.directory = "public/";
        if (arguments.contains("-d")) {
            String directory = getFlagValue(arguments, "-d");
            if (isValidPath(directory)) {
                this.directory = directory;
            }
        }
    }

    private void setApplication(List<String> arguments) {
        if (arguments.contains("-a")) {
            String application = getFlagValue(arguments, "-a");
            if (isValidApplication(application)) {
                this.application = application;
            }
        }
    }


    private String getFlagValue(List<String> arguments, String flag) {
        try {
            return arguments.get(arguments.indexOf(flag) + 1);
        }
        catch (ArrayIndexOutOfBoundsException e) {
            return "";
        }
    }

    private boolean isValidPort(String portString) {
        return (portString.length() > 0 && portString.matches("[0-9]*") && Integer.parseInt(portString) > 1024 && Integer.parseInt(portString) < 65536);
    }

    private boolean isValidPath(String directory) {
        return (directory.length() > 0 && directory.matches("[\\w/]*"));
    }

    private boolean isValidApplication(String application) {
        return (Files.exists(Paths.get(application)) && application.substring(application.lastIndexOf(".")).equals(".jar"));
    }
}
