package org.example.springcommerce.controller;

import org.example.springcommerce.entity.*;
import org.example.springcommerce.service.CartService;
import org.example.springcommerce.service.OrderService;
import org.example.springcommerce.service.ProductService;
import org.example.springcommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;

@Controller
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userService.getUserByUsername(username);
    }


    @PostMapping("/cart/add")
    public String addToCart(@RequestParam Long productId, @RequestParam int quantity, Model model) {
        Product product = productService.getProductById(productId);
        if (product == null) {
            model.addAttribute("errorMessage", "Sản phẩm không tồn tại.");
            return "error";
        }
        User currentUser = getCurrentUser();
        if (currentUser == null) {
            model.addAttribute("errorMessage", "Người dùng không tồn tại.");
            return "error";
        }
        cartService.addToCart(product, quantity, currentUser);
        return "redirect:/cart/view";
    }

    @GetMapping("/cart/view")
    public String viewCart(Model model) {
        User currentUser = getCurrentUser();
        if (currentUser == null) {
            model.addAttribute("errorMessage", "Không tìm thấy người dùng.");
            return "error";
        }

        List<CartItem> cartItems = cartService.getCartItems(currentUser);
        model.addAttribute("cartItems", cartItems);
        return "cart";
    }

    @PostMapping("/api/cart/update")
    public ResponseEntity<?> updateCartItemQuantity(@RequestBody Map<String, Object> payload) {
        try {
            Long cartItemId = Long.valueOf(payload.get("cartItemId").toString());
            int quantity = Integer.parseInt(payload.get("quantity").toString());

            CartItem updatedItem = cartService.updateCartItemQuantity(cartItemId, quantity);
            return ResponseEntity.ok().body(Map.of(
                    "success", true,
                    "message", "Cập nhật thành công",
                    "updatedItem", updatedItem
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                    "success", false,
                    "message", "Không thể cập nhật sản phẩm: " + e.getMessage()
            ));
        }
    }

    @PostMapping("/api/cart/delete")
    public ResponseEntity<?> deleteCartItem(@RequestParam Long cartItemId) {
        try {
            cartService.deleteCartItem(cartItemId);
            return ResponseEntity.ok().body(Map.of(
                    "success", true,
                    "message", "Sản phẩm đã được xóa khỏi giỏ hàng"
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                    "success", false,
                    "message", "Không thể xóa sản phẩm: " + e.getMessage()
            ));
        }
    }

    @GetMapping("/checkout")
    public String showCheckoutPage(
            @RequestParam(value = "selectedItems", required = false) List<String> selectedItems,
            Model model) {
        User currentUser = getCurrentUser();
        if (currentUser == null) {
            model.addAttribute("errorMessage", "Không tìm thấy người dùng.");
            return "error";
        }

        if (selectedItems == null || selectedItems.isEmpty()) {
            model.addAttribute("errorMessage", "Vui lòng chọn ít nhất một sản phẩm để tiếp tục.");
            return "cart"; // Quay lại giỏ hàng nếu không có sản phẩm nào được chọn
        }

        List<Long> itemIds = selectedItems.stream()
                .map(Long::parseLong)
                .toList();

        List<CartItem> cartItems = cartService.getCartItemsByIds(itemIds);
        model.addAttribute("cartItems", cartItems);

        double totalAmount = cartItems.stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();
        model.addAttribute("totalAmount", totalAmount);

        return "checkout";
    }

    @PostMapping("/checkout")
    public String processCheckout(
            @RequestParam String customerName,
            @RequestParam String customerPhone,
            @RequestParam String customerAddress,
            Model model) {

        User currentUser = userService.getCurrentUser();
        if (currentUser == null) {
            model.addAttribute("errorMessage", "Bạn cần đăng nhập để thực hiện thao tác này.");
            return "error";
        }

        List<CartItem> cartItems = cartService.getCartItems(currentUser);
        if (cartItems.isEmpty()) {
            model.addAttribute("errorMessage", "Giỏ hàng của bạn đang trống.");
            return "cart";
        }

        double totalAmount = cartItems.stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();

        CustomerOrder newOrder = orderService.createOrder(currentUser, cartItems);

        model.addAttribute("orderId", newOrder.getId());
        model.addAttribute("orderDate", newOrder.getOrderDate());
        model.addAttribute("totalAmount", totalAmount);
        model.addAttribute("customerName", customerName);
        model.addAttribute("customerPhone", customerPhone);
        model.addAttribute("customerAddress", customerAddress);
        model.addAttribute("orderItems", newOrder.getItems());

        return "checkoutss";
    }

    @GetMapping("/order-history")
    public String viewOrderHistory(Model model) {
        User currentUser = userService.getCurrentUser();
        if (currentUser == null) {
            model.addAttribute("errorMessage", "Bạn chưa đăng nhập.");
            return "error";
        }

        List<CustomerOrder> orders = orderService.getOrdersByUser(currentUser);

        model.addAttribute("orders", orders);

        return "order-history";  // Trả về view "order-history"
    }
}
