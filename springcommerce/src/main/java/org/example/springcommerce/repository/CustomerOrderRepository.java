package org.example.springcommerce.repository;

import org.example.springcommerce.entity.CustomerOrder;
import org.example.springcommerce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Long> {
    List<CustomerOrder> findByUser(User user);
}
