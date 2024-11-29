package org.example.springcommerce.repository;

import org.example.springcommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByNameContainingOrDescriptionContaining(String name, String description);
    @Query("SELECT p FROM Product p WHERE "
            + "(:keyword IS NULL OR p.name LIKE %:keyword% OR p.description LIKE %:keyword%) AND "
            + "(:category IS NULL OR p.category = :category) AND "
            + "(:priceMin IS NULL OR p.price >= :priceMin) AND "
            + "(:priceMax IS NULL OR p.price <= :priceMax) AND "
            + "(:brand IS NULL OR p.brand = :brand) AND "
            + "(:color IS NULL OR p.color = :color) "
            + "ORDER BY "
            + "CASE WHEN :sortOrder = 'asc' THEN p.price END ASC, "
            + "CASE WHEN :sortOrder = 'desc' THEN p.price END DESC")
    List<Product> findProductsByCriteria(
            @Param("keyword") String keyword,
            @Param("category") String category,
            @Param("priceMin") Double priceMin,
            @Param("priceMax") Double priceMax,
            @Param("brand") String brand,
            @Param("color") String color,
            @Param("sortOrder") String sortOrder);
}
