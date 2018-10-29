package worker;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import message.Processor;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public abstract class Worker {

    protected Channel channel;
    protected Connection connection;




    public Worker(String host, String username, String password) throws IOException, TimeoutException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(host);
        factory.setUsername(username);
        factory.setPassword(password);
        this.connection = factory.newConnection();
        this.channel = connection.createChannel();

    }

    public void init(String exchange, String queueName, String bindingPhrase, String bindingType) throws IOException {
        channel.exchangeDeclare(exchange, bindingType);
        channel.queueDeclare(queueName, true, false, false, null);
        channel.queueBind(queueName, exchange, bindingPhrase);
    }
    public void init(String queueName) throws IOException {
        channel.queueDeclare(queueName,true,false,false,null);
    }

    public abstract void startReceiving(String queueName, final Processor processor) throws IOException;

    public void close() throws IOException, TimeoutException {
        channel.close();
        connection.close();
    }
}
