package se.yrgo.msgofday;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class MessageOfTheDayDynamicImpl implements MessageOfTheDayService{
    private String[] messages=new String[] {
            "Monday message",
            "Tuesday message",
            "Wednesday message",
            "Thursday message",
            "Friday message",
            "Saturday message",
            "Sunday message",
    };

    @Override
    public String getTodaysMessage() {
        int day = new GregorianCalendar().get(Calendar.DAY_OF_WEEK);
        String message =messages[day-2];
        return message;
    }
}
