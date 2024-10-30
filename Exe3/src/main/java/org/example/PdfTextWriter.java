package org.example;

import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

@Component("pdfTextWriter")
public class PdfTextWriter implements TextWriter {
    private String text;

    @Override
    public void input() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter text:");
        text = scanner.nextLine();
    }

    @Override
    public void save() {
        write("output.pdf", text);
    }

    @Override
    public void write(String fileName, String text) {
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            fileWriter.write(text);
            System.out.println("Text saved to " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

