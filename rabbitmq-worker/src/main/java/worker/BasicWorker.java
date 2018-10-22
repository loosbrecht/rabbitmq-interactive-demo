package worker;

import com.rabbitmq.client.*;
import message.Processor;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class BasicWorker extends Worker {


    public BasicWorker(String host, String queueName) {
        super(host, queueName);
    }

    @Override
    public void init() throws IOException, TimeoutException {
        super.init();
        //give only one message at the time to a queue
        super.channel.basicQos(1);
    }

    public void startReceiving(final Processor processor) throws IOException {

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
