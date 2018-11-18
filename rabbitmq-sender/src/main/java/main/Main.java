package main;

import message.MessageGenerator;
import sender.ExchangeSender;
import sender.QueueSender;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Main {

    public static void main(String... args) throws IOException, TimeoutException {
      var host = "localhost";
      var sender = new QueueSender(host,"dmadmin","Installer1");
      sender.initChannel("newQueue",null);
       var messages = new MessageGenerator().getMessagesInString(10);
       messages.stream().forEach(msg->{
           try {
               sender.sendMessage(msg,"#");
               Thread.sleep(1000);
           } catch (IOException e) {
               e.printStackTrace();
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       });
       sender.close();
    }
}
