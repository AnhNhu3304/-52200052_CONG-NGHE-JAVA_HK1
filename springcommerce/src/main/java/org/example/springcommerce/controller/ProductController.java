package org.example.springcommerce.controller;

import org.example.springcommerce.entity.Product;
import org.example.springcommerce.exception.ResourceNotFoundException;
import org.example.springcommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.example.springcommerce.service.CartService;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CartService cartService;

    public ProductController(ProductService productService, CartService cartService) {
        this.productService = productService;
        this.cartService = cartService;
    }

    // API lấy tất cả sản phẩm với bộ lọc tùy chọn
    @GetMapping("/api/products")
    @ResponseBody
    public List<Product> getAllProducts(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) Double price,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String color) {
        return productService.getProducts(name, description, price, brand, category, color);
    }

    // API lấy một sản phẩm theo ID
    @GetMapping("/api/products/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }


    // API thêm sản phẩm mới
    @PostMapping("/api/products")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        // Đảm bảo đường dẫn hình ảnh từ client được sử dụng trực tiếp
        // Không gán cứng hay thay đổi đường dẫn URL
        Product createdProduct = productService.addProduct(product);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }



    // API cập nhật sản phẩm theo ID
    @PutMapping("/api/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
        Product updatedProduct = productService.updateProduct(id, productDetails);
        if (updatedProduct == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(updatedProduct);
    }

    // API xóa sản phẩm theo ID
    @DeleteMapping("/api/products/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("Sản phẩm đã được xóa.");
    }
}
