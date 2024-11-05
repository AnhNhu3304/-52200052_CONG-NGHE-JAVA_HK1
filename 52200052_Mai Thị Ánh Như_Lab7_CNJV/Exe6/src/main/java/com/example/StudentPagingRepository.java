package com.example;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentPagingRepository extends PagingAndSortingRepository<Student, Long> {
    // Các phương thức phân trang và sắp xếp sẽ được cung cấp tự động bởi PagingAndSortingRepository
}
