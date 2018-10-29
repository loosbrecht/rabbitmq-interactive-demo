package worker;

import com.rabbitmq.client.*;
import message.Processor;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class BasicWorker extends Worker {


    public BasicWorker(String host, String username, String password) throws IOException, TimeoutException {
        super(host, username,password);
    }



    public void startReceiving(String queueName, final Processor processor) throws IOException {

        Consumer consumer = new DefaultConsumer(super.channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {

                boolean ack = processor.processMessage(body);
                if(ack) {
                    channel.basicAck(envelope.getDeliveryTag(), false);
                }else{
                    channel.basicNack(envelope.getDeliveryTag(),false,true);
                }
            }
        };
        channel.basicConsume(queueName, false, consumer);

    }
}
