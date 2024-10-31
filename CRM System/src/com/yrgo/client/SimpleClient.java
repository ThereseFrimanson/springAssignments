package com.yrgo.client;

import com.yrgo.domain.Customer;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.yrgo.services.customers.CustomerManagementService;

import java.util.ArrayList;

public class SimpleClient {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext container = new ClassPathXmlApplicationContext("application.xml");

        CustomerManagementService customers = container.getBean(CustomerManagementService.class);

        for (Customer customer : customers.getAllCustomers())
        System.out.println(customer);
    container.close();
    }
}
