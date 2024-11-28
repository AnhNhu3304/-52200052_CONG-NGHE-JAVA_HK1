package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
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
            // Tạo các sinh viên mẫu
            studentRepository.save(new Student(null, "Nguyen Van A", 20, "vana@example.com", 6.5));
            studentRepository.save(new Student(null, "Tran Thi B", 21, "thib@example.com", 7.0));
            studentRepository.save(new Student(null, "Le Van C", 22, "vanc@example.com", 6.0));
            studentRepository.save(new Student(null, "Pham Minh D", 18, "minhd@example.com", 7.0));

            // 1. Tìm danh sách sinh viên có tuổi lớn hơn hoặc bằng 20
            List<Student> studentsByAge = studentRepository.findStudentsByAge(20);
            System.out.println("Sinh viên có tuổi từ 20 trở lên:");
            studentsByAge.forEach(System.out::println);

            // 2. Đếm số lượng sinh viên có điểm IELTS bằng 7.0
            long countByIeltsScore = studentRepository.countStudentsByIelts(7.0);
            System.out.println("Số sinh viên có điểm IELTS bằng 7.0: " + countByIeltsScore);

            // 3. Tìm danh sách sinh viên có tên chứa "Van" (không phân biệt chữ hoa/thường)
            List<Student> studentsByName = studentRepository.searchStudentsByName("Van");
            System.out.println("Sinh viên có tên chứa 'Van':");
            studentsByName.forEach(System.out::println);
        };
    }
}

