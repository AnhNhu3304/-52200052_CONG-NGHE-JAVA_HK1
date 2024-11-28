package com.example;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {

    // Lấy danh sách sinh viên có tuổi lớn hơn hoặc bằng x
    List<Student> findByAgeGreaterThanEqual(int age);

    // Đếm số lượng sinh viên có điểm IELTS bằng x
    long countByIeltsScore(double ieltsScore);

    // Tìm danh sách sinh viên có tên chứa từ xxx (không phân biệt chữ hoa/thường)
    List<Student> findByNameContainingIgnoreCase(String name);
}
