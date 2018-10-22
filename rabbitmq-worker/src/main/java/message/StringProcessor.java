package message;

import java.io.UnsupportedEncodingException;

public class StringProcessor implements Processor {


    public boolean processMessage(byte[] body) {

        String msg = new String(body);

        System.out.println("Message received: [" + msg + "]");


        return true;
    }
}
