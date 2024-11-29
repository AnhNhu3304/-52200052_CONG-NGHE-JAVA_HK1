package org.example.springcommerce.repository;

import org.example.springcommerce.entity.CartItem;
import org.example.springcommerce.entity.Product;
import org.example.springcommerce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByUser(User user);

    Optional<CartItem> findByUserAndProduct(User user, Product product);
}
