package message;

public class MessageDataModel {

    private int score;
    private String name;
    private String message;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MessageDataModel(int score, String name, String message) {
        this.score = score;
        this.name = name;
        this.message = message;
    }
}
