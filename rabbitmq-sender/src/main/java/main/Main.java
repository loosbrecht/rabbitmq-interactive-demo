package main;

import sender.StringSender;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeoutException;

public class Main {

    public static void main(String... args) throws IOException, TimeoutException {
        String binding = "";
        String msg = "";
        if(args.length == 2){
            binding = args[1];
            msg = args[0];
        }else if(args.length == 1){
            msg = args[0];
        }else{
            return;
        }
        StringSender ss = new StringSender("localhost");
        ss.init("dmadmin","Installer1");
       ss.sendMessage(msg,binding);

        ss.close();
    }
}
