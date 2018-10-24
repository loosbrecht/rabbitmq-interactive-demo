package sender;

import java.io.IOException;

public class StringSender extends Sender<String> {
    public StringSender(String host) {
        super(host);
    }

    @Override
    public void sendMessage(String msg, String binding) throws IOException {
        System.out.println("Exchange: [" + EXCHANGE + "] message[" + msg + "]");
        this.channel.basicPublish(EXCHANGE, binding, null, convertToBytes(msg));
    }

    @Override
    public byte[] convertToBytes(String msg) {
        return msg.getBytes();
    }
}
