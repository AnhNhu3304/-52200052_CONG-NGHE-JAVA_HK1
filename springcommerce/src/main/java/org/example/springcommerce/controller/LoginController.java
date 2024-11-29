package org.example.springcommerce.controller;

import org.example.springcommerce.entity.Product;
import org.example.springcommerce.entity.User;
import org.example.springcommerce.service.ProductService;
import org.example.springcommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ProductService productService;

    @GetMapping("/login")
    public String showLoginPage(Model model,
                                @RequestParam(value = "error", required = false) String error,
                                @RequestParam(value = "logout", required = false) String logout) {
        if (error != null) {
            model.addAttribute("error", "Sai tên đăng nhập hoặc mật khẩu!");
        }
        return "login";
    }

    @PostMapping("/login")
    public String handleLogin(@RequestParam String username, @RequestParam String password,
                              RedirectAttributes redirectAttributes, Model model) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);

            model.addAttribute("products", productService.getAllProducts());
            return "redirect:/welcome";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Sai tên đăng nhập hoặc mật khẩu!");
            return "redirect:/login";
        }
    }

    @GetMapping("/register")
    public String showRegistrationPage() {
        return "registration";
    }

    @PostMapping("/register")
    public String handleRegistration(@RequestParam String username, @RequestParam String password,
                                     RedirectAttributes redirectAttributes) {
        if (userRepository.findByUsername(username) != null) {
            redirectAttributes.addFlashAttribute("error", "Tên người dùng đã tồn tại");
            return "redirect:/register";
        }

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(passwordEncoder.encode(password));

        userRepository.save(newUser);

        redirectAttributes.addFlashAttribute("message", "Đăng ký thành công, vui lòng đăng nhập");
        return "redirect:/login";
    }

    // Hiển thị trang chủ
    @GetMapping("/welcome")
    public String showWelcomePage(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("pants", products.stream()
                .filter(product -> product.getCategory().equals("Quần"))
                .collect(Collectors.toList()));

        model.addAttribute("shirts", products.stream()
                .filter(product -> product.getCategory().equals("Áo"))
                .collect(Collectors.toList()));

        model.addAttribute("shoes", products.stream()
                .filter(product -> product.getCategory().equals("Giày"))
                .collect(Collectors.toList()));

        model.addAttribute("accessories", products.stream()
                .filter(product -> product.getCategory().equals("Nón"))
                .collect(Collectors.toList()));

        model.addAttribute("jewelry", products.stream()
                .filter(product -> product.getCategory().equals("Trang sức"))
                .collect(Collectors.toList()));



        return "welcome";  // trả về view 'welcome'
    }

    // Hiển thị chi tiết sản phẩm
    @GetMapping("/product/{id}")
    public String viewProductDetails(@PathVariable("id") Long id, Model model) {
        Product product = productService.getProductById(id);
        if (product == null) {
            return "redirect:/error";
        }
        model.addAttribute("product", product);
        return "product_detail";
    }

    // Tìm kiếm theo từ khóa
    @GetMapping("/searchByKeyword")
    public String searchByKeyword(@RequestParam("keyword") String keyword, Model model) {
        List<Product> products = productService.searchProducts(keyword);
        model.addAttribute("products", products);
        model.addAttribute("keyword", keyword);
        return "searchResults";
    }

    // Tìm kiếm theo bộ lọc
    @GetMapping("/filter-search")
    public String searchProductsByFilters(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String price,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String color,
            Model model) {
        List<Product> products = productService.advancedSearch(null, category, price, brand, color);
        model.addAttribute("products", products);

        return "filterResults";
    }
}
