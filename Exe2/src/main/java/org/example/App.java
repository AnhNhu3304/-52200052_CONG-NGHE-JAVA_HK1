package org.example;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        // Khởi tạo Spring context
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        // Tải các bean từ context
        Product product1 = context.getBean("product1", Product.class);
        Product product2 = context.getBean("product2", Product.class);
        Product singletonProduct = context.getBean("singletonProduct", Product.class);

        // In thông tin sản phẩm
        System.out.println("Product 1: " + product1);
        System.out.println("Product 2: " + product2);
        System.out.println("Singleton Product: " + singletonProduct);

        // Kiểm tra phạm vi của các bean
        Product anotherProduct1 = context.getBean("product1", Product.class);
        Product anotherProduct2 = context.getBean("product2", Product.class);

        // Kiểm tra xem product1 và anotherProduct1 có phải là các đối tượng khác nhau hay không
        System.out.println("Is product1 equal to anotherProduct1? " + (product1 == anotherProduct1)); // true nếu là prototype

        // Kiểm tra xem product2 và anotherProduct2 có phải là các đối tượng khác nhau hay không
        System.out.println("Is product2 equal to anotherProduct2? " + (product2 == anotherProduct2)); // true nếu là prototype

        // Kiểm tra xem singletonProduct có phải là một singleton hay không
        Product anotherSingletonProduct = context.getBean("singletonProduct", Product.class);
        System.out.println("Is singletonProduct equal to anotherSingletonProduct? " + (singletonProduct == anotherSingletonProduct)); // true nếu là singleton

        // Đóng context
        context.close();
    }
}
