package com.mikeknep.dahomey.utils;

import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
* Created by mrk on 5/7/14.
*/
public class Server {
    private String directory;
    private ServerSocket serverSocket;
    private String application;
    private ExecutorService threadPool = Executors.newFixedThreadPool(16);

    public Server(String directory, ServerSocket serverSocket, String application) throws Exception {
        this.directory = directory;
        this.serverSocket = serverSocket;
        this.application = application;
        if (this.application == null) {
            throw new MissingApplicationException();
        }
        Logger.logStartup(serverSocket.getLocalPort(), directory, application);
    }

    public void run() throws Exception {
        while (true) {
            SocketConnection clientConnection = new SocketConnection(serverSocket);
            threadPool.execute(new Worker(clientConnection, directory, application));
        }
    }
}
