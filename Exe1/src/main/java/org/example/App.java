package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("appConfig.xml");

        Product product1 = (Product) context.getBean("product1");
        Product product2 = (Product) context.getBean("product2");
        Product product3 = (Product) context.getBean("product3");

        System.out.println(product1);
        System.out.println(product2);
        System.out.println(product3);

        Product product1Prototype = (Product) context.getBean("product1");
        Product product2Prototype = (Product) context.getBean("product2");
        Product product3Singleton = (Product) context.getBean("product3");

        System.out.println("Product 1 và Product 2 là prototype: " + (product1 != product1Prototype && product2 != product2Prototype));
        System.out.println("Product 3 là singleton: " + (product3 == product3Singleton));
    }
}
