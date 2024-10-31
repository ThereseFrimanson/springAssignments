package se.yrgo.msgofday;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class MessageOfTheDayConfigurableImp implements MessageOfTheDayService{
    private String[] messagesList;

    public void setMessagesList(String[] messagesList) {
        this.messagesList = messagesList;
    }

    @Override
    public String getTodaysMessage() {
        int day = new GregorianCalendar().get(Calendar.DAY_OF_WEEK);
        String message = messagesList[day-2];
        return message;
    }
}
