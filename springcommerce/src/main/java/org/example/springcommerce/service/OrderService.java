package org.example.springcommerce.service;

import org.example.springcommerce.entity.CartItem;
import org.example.springcommerce.entity.CustomerOrder;
import org.example.springcommerce.entity.OrderItem;
import org.example.springcommerce.entity.User;
import org.example.springcommerce.repository.CustomerOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private CustomerOrderRepository customerOrderRepository;



    // Lấy tất cả đơn hàng
    public List<CustomerOrder> getAllOrders() {
        return customerOrderRepository.findAll();
    }

    // Lấy đơn hàng theo ID
    public Optional<CustomerOrder> getOrderById(Long id) {
        return customerOrderRepository.findById(id);
    }

    // Xóa đơn hàng theo ID
    public void deleteOrder(Long id) {
        CustomerOrder order = getOrderById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID đơn hàng không hợp lệ: " + id));
        customerOrderRepository.delete(order);
    }

    // Lấy danh sách đơn hàng của người dùng
    public List<CustomerOrder> getOrdersByUser(User user) {
        return customerOrderRepository.findByUser(user);
    }

    // Tạo đơn hàng mới
    public CustomerOrder createOrder(User user, List<CartItem> cartItems) {
        CustomerOrder order = new CustomerOrder();
        order.setUser(user);
        order.setOrderDate(LocalDateTime.now());

        List<OrderItem> orderItems = new ArrayList<>();
        double totalAmount = 0;

        for (CartItem cartItem : cartItems) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProductName(cartItem.getProduct().getName());
            orderItem.setProductPrice(cartItem.getProduct().getPrice());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItems.add(orderItem);

            totalAmount += cartItem.getProduct().getPrice() * cartItem.getQuantity();
        }

        order.setItems(orderItems);
        order.setTotalAmount(totalAmount);
        order = customerOrderRepository.save(order);

//        cartService.clearCart(user);

        return order;
    }







}
