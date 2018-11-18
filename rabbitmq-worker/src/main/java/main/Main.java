package main;

import message.StringProcessor;
import worker.BasicWorker;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Main {
    public static void main(String... args) throws IOException, TimeoutException {
        String username = "dmadmin";
        String password = "Installer1";
        String queueName = "queue";
        String host = "localhost";

        BasicWorker worker = null;
        try {
            worker = new BasicWorker(host, username, password);
            worker.init(queueName);
            worker.startReceiving(queueName, new StringProcessor());
        } finally {
            if (worker != null) {
                worker.close();
            }
        }
    }
}
