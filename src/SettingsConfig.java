import java.util.Arrays;
import java.util.List;

/**
 * Created by mrk on 5/13/14.
 */
public class SettingsConfig {
    private int port;
    private String directory;

    public int getPort() { return this.port; }
    public String getDirectory() { return this.directory; }

    public SettingsConfig(String[] args) {
        List<String> arguments = Arrays.asList(args);
        setPortNumber(arguments);
        setDirectory(arguments);
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
        this.directory = "public";
        if (arguments.contains("-d")) {
            String directory = getFlagValue(arguments, "-d");
            if (isValidPath(directory)) {
                this.directory = directory;
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
        return (directory.length() > 0 && directory.matches("[A-Za-z0-9/]*"));
    }
}
