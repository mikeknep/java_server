package com.mikeknep.dahomey;

import com.mikeknep.dahomey.utils.ArgsParser;

import java.net.ServerSocket;

/**
 * Created by mrk on 5/28/14.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        ArgsParser parser = new ArgsParser(args);
        String rootDirectory = parser.getDirectory();
        ServerSocket serverSocket = new ServerSocket(parser.getPort());
        String application = parser.getApplication();
        Server server = new Server(rootDirectory, serverSocket, application);
        server.run();
    }
}
