package main;

import message.StringProcessor;
import worker.BasicWorker;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Main {
    public static void main(String... args) throws IOException, TimeoutException {
        String username = "dmadmin";
        String password = "Installer1";
        String queueName = "newQueue";
        String host = "localhost";

        BasicWorker worker = null;

            worker = new BasicWorker(host, username, password);
            worker.init(queueName);
            worker.startReceiving(queueName, new StringProcessor());

    }
}
