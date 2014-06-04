package com.mikeknep.dahomey;

import com.mikeknep.dahomey.utils.Server;
import com.mikeknep.dahomey.utils.SettingsConfig;

import java.net.ServerSocket;

/**
 * Created by mrk on 5/28/14.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        SettingsConfig config = new SettingsConfig(args);
        String rootDirectory = config.getDirectory();
        ServerSocket serverSocket = new ServerSocket(config.getPort());
        String application = config.getApplication();
        Server server = new Server(rootDirectory, serverSocket, application);
        server.run();
    }
}
