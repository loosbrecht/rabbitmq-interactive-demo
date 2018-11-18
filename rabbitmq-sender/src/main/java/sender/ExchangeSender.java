package sender;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ExchangeSender extends Sender<String> {
    private String exchange;
    public ExchangeSender(String host, String username, String password) throws IOException, TimeoutException {
        super(host, username, password);
        this.exchange = null;
    }

    @Override
    public void initChannel(String name, String binding) throws IOException {

        channel = connection.createChannel();
        channel.exchangeDeclare(name, binding, true);
        this.exchange = name;
    }

    @Override
    public void sendMessage(String msg, String binding) throws IOException {
        System.out.println("Exchange: [" + exchange + "] binding: [" + binding + "] message[" + msg + "]");
        this.channel.basicPublish(exchange, binding, null, convertToBytes(msg));
    }

    @Override
    public byte[] convertToBytes(String msg) {
        return msg.getBytes();
    }
}
