package sender;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public abstract class Sender<T> {
    protected Channel channel;
    protected String host;
    protected Connection connection;

    public static String EXCHANGE = "lotofmessages";

    public Sender(String host,String username,String password) throws IOException, TimeoutException {
        this.host = host;
        this.channel = null;
        this.connection = null;
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(host);
        factory.setUsername(username);
        factory.setPassword(password);
        connection = factory.newConnection();
    }

    public abstract void initChannel(String name, String binding) throws IOException;

    public abstract void sendMessage(String msg, String binding) throws IOException;

    public abstract byte[] convertToBytes(T msg);

    public void close() throws IOException, TimeoutException {
        channel.close();
        connection.close();
    }

}
