package worker;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import message.Processor;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public abstract class Worker {

    protected Channel channel;
    protected String queueName;
    protected String host;
    protected Connection connection;

    public Worker(String host, String queueName) {
        this.queueName = queueName;
        this.host = host;
        this.channel = null;
        this.connection = null;

    }

    public void init() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(host);
         connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(queueName, false, false, false, null);
        System.out.println("Start listening on queue: [" + queueName + "]");
    }

    public abstract void startReceiving(final Processor processor) throws IOException;

    public void close() throws IOException, TimeoutException {
        channel.close();
        connection.close();
    }
}
