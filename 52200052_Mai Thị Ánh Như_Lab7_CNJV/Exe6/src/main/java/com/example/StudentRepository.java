package com.example;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {

    // 1. Lấy danh sách sinh viên có tuổi lớn hơn hoặc bằng x
    @Query("SELECT s FROM Student s WHERE s.age >= :age")
    List<Student> findStudentsByAge(int age);

    // 2. Đếm số lượng sinh viên có điểm IELTS bằng x
    @Query("SELECT COUNT(s) FROM Student s WHERE s.ieltsScore = :ieltsScore")
    long countStudentsByIelts(double ieltsScore);

    // 3. Tìm danh sách sinh viên có tên chứa từ xxx (không phân biệt chữ hoa/thường)
    @Query("SELECT s FROM Student s WHERE LOWER(s.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Student> searchStudentsByName(String name);
}

