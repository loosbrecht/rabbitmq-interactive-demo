package sender;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public abstract class Sender<T> {
    protected Channel channel;
    protected String queueName;
    protected String host;
    protected Connection connection;

    public Sender(String host, String queueName) {
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

    public abstract void sendMessage(T msg) throws IOException;

    public abstract byte[] convertToBytes(T msg);

    public void close() throws IOException, TimeoutException {
        channel.close();
        connection.close();
    }

}
