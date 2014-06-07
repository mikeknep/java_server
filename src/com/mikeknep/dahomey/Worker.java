package com.mikeknep.dahomey;

import com.mikeknep.dahomey.requests.Listener;
import com.mikeknep.dahomey.requests.Request;
import com.mikeknep.dahomey.requests.RequestBuilder;
import com.mikeknep.dahomey.responses.Responder;
import com.mikeknep.dahomey.responses.Response;
import com.mikeknep.dahomey.responses.ResponseFactory;
import com.mikeknep.dahomey.utils.ApplicationInteractor;
import com.mikeknep.dahomey.utils.Logger;
import com.mikeknep.dahomey.utils.StreamPair;

import java.util.ArrayList;

/**
 * Created by mrk on 6/4/14.
 */
public class Worker implements Runnable {
    private StreamPair clientConnection;
    private String directory;
    private String application;

    public Worker(StreamPair clientConnection, String directory, String application) {
        this.clientConnection = clientConnection;
        this.directory = directory;
        this.application = application;
    }

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
}
