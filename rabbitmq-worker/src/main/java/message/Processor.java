package message;

public interface Processor {

    public boolean processMessage(byte[] body);
}
