package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import java.util.List;

@SpringBootApplication
public class DemoApplication {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentPagingRepository studentPagingRepository;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            // Tạo dữ liệu mẫu (thêm nhiều hơn 10 sinh viên để thử nghiệm)
            studentRepository.save(new Student(null, "Nguyen Van A", 20, "vana@example.com", 6.5));
            studentRepository.save(new Student(null, "Tran Thi B", 21, "thib@example.com", 7.0));
            studentRepository.save(new Student(null, "Le Van C", 22, "vanc@example.com", 6.0));
            studentRepository.save(new Student(null, "Pham Minh D", 18, "minhd@example.com", 7.0));
            studentRepository.save(new Student(null, "Doan Kha E", 23, "doank@example.com", 5.5));
            studentRepository.save(new Student(null, "Vo Thi F", 20, "vof@example.com", 6.2));
            studentRepository.save(new Student(null, "Mai Anh G", 22, "maig@example.com", 7.2));
            studentRepository.save(new Student(null, "Nguyen Huu H", 20, "huuh@example.com", 6.0));
            studentRepository.save(new Student(null, "Le Hoang I", 19, "hoangi@example.com", 6.8));
            studentRepository.save(new Student(null, "Trinh Gia K", 22, "tri@example.com", 5.8));
            studentRepository.save(new Student(null, "Luong The L", 21, "luong@example.com", 6.4));

            // 1. Đọc danh sách sinh viên sắp xếp giảm dần theo tuổi, tăng dần theo điểm IELTS khi tuổi bằng nhau
            Iterable<Student> sortedStudents = studentPagingRepository.findAll(
                    Sort.by(Sort.Order.desc("age"), Sort.Order.asc("ieltsScore"))
            );
            System.out.println("Danh sách sinh viên sắp xếp theo tuổi giảm dần, điểm IELTS tăng dần khi cùng tuổi:");
            sortedStudents.forEach(System.out::println);

            // 2. Giả định danh sách có hơn 10 sinh viên và lấy sinh viên ở vị trí 4, 5, 6
            Pageable pageRequest = PageRequest.of(1, 3, Sort.by(Sort.Order.desc("age"), Sort.Order.asc("ieltsScore")));
            List<Student> pageStudents = studentPagingRepository.findAll(pageRequest).getContent();
            System.out.println("Sinh viên ở vị trí 4, 5, 6:");
            pageStudents.forEach(System.out::println);
        };
    }
}


