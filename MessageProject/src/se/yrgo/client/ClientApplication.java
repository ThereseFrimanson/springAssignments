package se.yrgo.client;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import se.yrgo.msgofday.MessageOfTheDayService;

public class ClientApplication {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext container = new
                ClassPathXmlApplicationContext("application.xml");

        MessageOfTheDayService helloSpring = container.getBean(
                MessageOfTheDayService.class);
        System.out.println(helloSpring.getTodaysMessage());
        container.close();
    }
}
