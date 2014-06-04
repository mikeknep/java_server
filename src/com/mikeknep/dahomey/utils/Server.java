package com.mikeknep.dahomey.utils;

import com.mikeknep.dahomey.requests.Listener;
import com.mikeknep.dahomey.requests.Request;
import com.mikeknep.dahomey.requests.RequestBuilder;
import com.mikeknep.dahomey.responses.Responder;
import com.mikeknep.dahomey.responses.Response;
import com.mikeknep.dahomey.responses.ResponseFactory;

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
        Logger.logStartup(serverSocket.getLocalPort(), directory);
    }

    public void run() throws Exception {
        while (true) {
            final SocketConnection clientConnection = new SocketConnection(serverSocket);
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        String rawRequest = Listener.receiveRawRequest(clientConnection.getIn());
                        Request request = RequestBuilder.buildRequest(rawRequest);
                        ApplicationInteractor interactor = new ApplicationInteractor(directory, request, application);
                        interactor.runApplication();
                        Response response = ResponseFactory.buildResponse(interactor.getStatus(), interactor.getHeaders(), interactor.getBody());
                        Responder.sendResponse(response, clientConnection.getOut());
                        Logger.logBasic(request, response, clientConnection.getSocketOpenTime());
                        clientConnection.close();
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
            });
        }
    }
}
