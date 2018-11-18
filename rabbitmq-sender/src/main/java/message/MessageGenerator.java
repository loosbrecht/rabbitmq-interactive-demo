package message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.*;

public class MessageGenerator {

    private static final int MAX = 5;
    private static final List<String> NAMES = Arrays.asList("Luke", "Han", "Rey", "R2D2", "Alan");
    private static final List<String> MSG = Arrays.asList("Test 1234", "Hello World", "I'll be back", "I'm in Gent", "Time for testing");

    public MessageDataModel generateMessage() {
        Random random = new Random();
        int rndm1 = random.nextInt(MAX);
        int rndm2 = random.nextInt(MAX);
        int rndm3 = random.nextInt(MAX);
        var model = new MessageDataModel(rndm1 + 1, NAMES.get(rndm2), MSG.get(rndm3));
        return model;
    }

    public List<MessageDataModel> generateMessageList(int nums) {
        var lst = new ArrayList<MessageDataModel>();
        for (int i = 0; i < nums; ++i) {
            lst.add(generateMessage());
        }
        return lst;
    }

    public List<String> getMessagesInString(int nums) {
        var messages = generateMessageList(nums);
        var strMessages = messages.stream().map(obj -> {
            var objectMapper = new ObjectMapper();
            try {
                return objectMapper.writeValueAsString(obj);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return null;
        }).collect(toList());
        return strMessages;
    }
}
