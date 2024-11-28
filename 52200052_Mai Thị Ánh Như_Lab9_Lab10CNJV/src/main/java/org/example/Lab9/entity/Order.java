package org.example.Lab9.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "oders") // Đặt tên bảng là "oders"
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String orderNumber;
    private Double totalSellingPrice;

    @ElementCollection
    private List<String> productList;

    // Constructor không tham số (bắt buộc cho JPA)
    public Order() {
    }

    // Constructor đầy đủ tham số
    public Order(String orderNumber, Double totalSellingPrice, List<String> productList) {
        this.orderNumber = orderNumber;
        this.totalSellingPrice = totalSellingPrice;
        this.productList = productList;
    }

    // Getters và Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Double getTotalSellingPrice() {
        return totalSellingPrice;
    }

    public void setTotalSellingPrice(Double totalSellingPrice) {
        this.totalSellingPrice = totalSellingPrice;
    }

    public List<String> getProductList() {
        return productList;
    }

    public void setProductList(List<String> productList) {
        this.productList = productList;
    }
}
