package sender;

import java.io.IOException;

public class StringSender extends Sender<String> {
    public StringSender(String host, String queueName) {
        super(host, queueName);
    }

    @Override
    public void sendMessage(String msg) throws IOException {
        this.channel.basicPublish("",queueName,null,convertToBytes(msg));
    }

    @Override
    public byte[] convertToBytes(String msg) {
        return msg.getBytes();
    }
}
