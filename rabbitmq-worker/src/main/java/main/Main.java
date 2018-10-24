package main;

import message.StringProcessor;
import worker.BasicWorker;
import worker.QueueProperties;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Main {
    public static void main(String... args) throws IOException, TimeoutException {
        String queueName = QueueProperties.QUEUE_NAME;
        String binding = QueueProperties.BINDING;
        if(args.length >= 1){
            queueName = args[0];
        }
        if(args.length == 2){
            binding = args[1];
        }
        BasicWorker worker = new BasicWorker(QueueProperties.HOST,queueName,binding);
        worker.init(QueueProperties.USERNAME,QueueProperties.PASSWORD);
        worker.startReceiving(new StringProcessor());

    }
}
