package com.example;

import org.springframework.boot.CommandLineRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

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
            // Thêm 3 sinh viên vào cơ sở dữ liệu
            Student student1 = new Student(null, "Nguyen Van A", 20, "a@example.com", 6.5);
            Student student2 = new Student(null, "Tran Thi B", 22, "b@example.com", 7.0);
            Student student3 = new Student(null, "Le Van C", 21, "c@example.com", 6.0);

            studentRepository.save(student1);
            studentRepository.save(student2);
            studentRepository.save(student3);

            // Đọc danh sách sinh viên và in ra console
            List<Student> students = (List<Student>) studentRepository.findAll();
            System.out.println("Danh sách sinh viên:");
            students.forEach(System.out::println);

            // Cập nhật thông tin sinh viên
            student1.setEmail("new_a@example.com");
            studentRepository.save(student1);
            System.out.println("Sau khi cập nhật:");
            System.out.println(student1);

            // Xóa một sinh viên và in kết quả
            studentRepository.delete(student2);
            System.out.println("Danh sách sinh viên sau khi xóa:");
            students = (List<Student>) studentRepository.findAll();
            students.forEach(System.out::println);
        };
    }
}
