package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

    @Autowired
    private StudentRepository studentRepository;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            // Thêm ít nhất 3 sinh viên vào cơ sở dữ liệu
            studentRepository.save(new Student(null, "Nguyen Van A", 20, "vana@example.com", 6.5));
            studentRepository.save(new Student(null, "Tran Thi B", 21, "thib@example.com", 7.0));
            studentRepository.save(new Student(null, "Le Van C", 22, "vanc@example.com", 6.0));

            // Đọc danh sách sinh viên và in ra console
            System.out.println("Danh sách sinh viên:");
            studentRepository.findAll().forEach(System.out::println);

            // Cập nhật thông tin sinh viên và in kết quả
            Student studentToUpdate = studentRepository.findById(1L).orElseThrow();
            studentToUpdate.setIeltsScore(7.5);
            studentRepository.save(studentToUpdate);
            System.out.println("Sau khi cập nhật:");
            studentRepository.findAll().forEach(System.out::println);

            // Xoá một sinh viên và in kết quả
            studentRepository.deleteById(2L);
            System.out.println("Sau khi xoá:");
            studentRepository.findAll().forEach(System.out::println);
        };
    }
}
