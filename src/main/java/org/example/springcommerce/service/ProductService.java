package org.example.springcommerce.service;

import org.example.springcommerce.entity.Product;
import org.example.springcommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.example.springcommerce.exception.ResourceNotFoundException;
import java.util.stream.Collectors;
import java.util.ArrayList;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartService cartService;

    @Transactional

    // Lấy tất cả sản phẩm
    public List<Product> getProducts(String name, String description, Double price, String brand, String category, String color) {
        return productRepository.findAll();
    }
    // Lấy tất cả sản phẩm
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Thêm mới sản phẩm
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    // Lấy sản phẩm theo ID
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }

    // Cập nhật sản phẩm
    public Product updateProduct(Long id, Product productDetails) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        existingProduct.setName(productDetails.getName());
        existingProduct.setDescription(productDetails.getDescription());
        existingProduct.setPrice(productDetails.getPrice());
        existingProduct.setCategory(productDetails.getCategory());
        existingProduct.setColor(productDetails.getColor());
        return productRepository.save(existingProduct);
    }

    // Xóa sản phẩm
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        productRepository.delete(product);
    }

    public List<Product> getProductsByCategory(String category) {
        return productRepository.findAll().stream()
                .filter(product -> category == null || product.getCategory() == null || product.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    public List<Product> searchProducts(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return new ArrayList<>(); // Trả về danh sách rỗng nếu không có từ khóa
        }
        return productRepository.findByNameContainingOrDescriptionContaining(keyword, keyword);
    }

    public List<Product> advancedSearch(String keyword, String category, String price, String brand, String color) {
        Double priceMin = null;
        Double priceMax = null;
        String sortOrder = null;

        if (price != null) {
            switch (price) {
                case "Giá từ thấp đến cao":
                    priceMin = 0.0;
                    priceMax = Double.MAX_VALUE;
                    sortOrder = "asc";
                    break;
                case "Giá từ cao đến thấp":
                    priceMin = null;
                    priceMax = null;
                    sortOrder = "desc";
                    break;
                default:
                    try {
                        String[] priceRange = price.split("-");
                        priceMin = Double.parseDouble(priceRange[0].trim());
                        priceMax = Double.parseDouble(priceRange[1].trim());
                    } catch (Exception e) {
                        throw new IllegalArgumentException("Định dạng giá không hợp lệ: " + price);
                    }
            }
        }

        return productRepository.findProductsByCriteria(keyword, category, priceMin, priceMax, brand, color, sortOrder);
    }
}
