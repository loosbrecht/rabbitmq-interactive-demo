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
    protected String binding;

    public static String EXCHANGE = "lotofmessages";

    public Worker(String host, String queueName, String binding) {
        this.queueName = queueName;
        this.host = host;
        this.channel = null;
        this.connection = null;
        this.binding = binding;

    }

    public void init(String username, String password) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(host);
        factory.setUsername(username);
        factory.setPassword(password);
        connection = factory.newConnection();
        channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE, "direct");
        channel.queueDeclare(queueName, false, false, false, null);
        channel.queueBind(queueName, EXCHANGE, binding);
        System.out.println("Start listening on queue: [" + queueName + "]");
    }

    public abstract void startReceiving(final Processor processor) throws IOException;

    public void close() throws IOException, TimeoutException {
        channel.close();
        connection.close();
    }
}
