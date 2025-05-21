package se.yrgo.client;

import org.springframework.context.support.*;

public class Client {
    public static void main(String[] args) {

        ClassPathXmlApplicationContext container = new ClassPathXmlApplicationContext("application.xml");
        
    }
}
