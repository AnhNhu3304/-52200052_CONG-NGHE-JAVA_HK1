package org.example.springcommerce.controller;

import org.example.springcommerce.entity.CustomerOrder;
import org.example.springcommerce.service.OrderService;
import org.example.springcommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    // Lấy tất cả đơn hàng
    @GetMapping("/orders")
    public List<CustomerOrder> getAllOrders() {
        return orderService.getAllOrders();
    }

    // Lấy đơn hàng theo ID
    @GetMapping("/orders/{id}")
    public CustomerOrder getOrderById(@PathVariable("id") Long id) {
        return orderService.getOrderById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID đơn hàng không hợp lệ: " + id));
    }

    // Xóa đơn hàng theo ID
    @DeleteMapping("/orders/{id}")
    public void deleteOrder(@PathVariable("id") Long id) {
        orderService.deleteOrder(id);
    }
}
