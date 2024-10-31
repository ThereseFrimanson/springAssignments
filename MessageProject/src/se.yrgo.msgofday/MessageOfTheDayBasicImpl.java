package se.yrgo.msgofday;


public class MessageOfTheDayBasicImpl implements MessageOfTheDayService{
    private String message;

    public void setMessage(String message) {
        this.message = message;
    }
    @Override
    public String getTodaysMessage() {
        return message;
    }
}
