package sender;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class QueueSender extends Sender<String> {
    private String queue;
    public QueueSender(String host,String username, String password) throws IOException, TimeoutException {
        super(host,username,password);
        this.queue = null;
    }

    @Override
    public void initChannel(String name, String binding) throws IOException {

        channel = connection.createChannel();
        channel.queueDeclare(name,false,false,false,null);
        this.queue = name;
    }

    @Override
    public void sendMessage(String msg, String binding) throws IOException {
        System.out.println("Queue: [" + queue + "] message[" + msg + "]");
        this.channel.basicPublish("",queue, null, convertToBytes(msg));
    }

    @Override
    public byte[] convertToBytes(String msg) {
        return msg.getBytes();
    }
}
