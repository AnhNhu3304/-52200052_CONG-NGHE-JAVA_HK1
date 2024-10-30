package org.example;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        TextEditor textEditor = context.getBean(TextEditor.class);

        textEditor.input();
        textEditor.save();

        context.close();
    }
}
