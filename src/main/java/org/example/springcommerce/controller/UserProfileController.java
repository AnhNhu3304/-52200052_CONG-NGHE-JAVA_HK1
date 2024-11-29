package org.example.springcommerce.controller;

import org.example.springcommerce.entity.User;
import org.example.springcommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class UserProfileController {

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public String viewProfile(Model model) {
        User currentUser = userService.getCurrentUser();
        if (currentUser == null) {
            model.addAttribute("errorMessage", "Bạn chưa đăng nhập.");
            return "error";
        }

        model.addAttribute("user", currentUser);
        return "profile";
    }

    @PostMapping("/profile")
    public String updateProfile(
            @RequestParam String username,
            @RequestParam(required = false) String currentPassword,
            @RequestParam(required = false) String newPassword,
            @RequestParam(required = false) String confirmPassword,
            Model model) {

        User currentUser = userService.getCurrentUser();
        if (currentUser == null) {
            model.addAttribute("errorMessage", "Bạn chưa đăng nhập.");
            return "error";
        }

        currentUser.setUsername(username);

        if (currentPassword != null && !currentPassword.isEmpty()) {
            if (!userService.checkPassword(currentUser, currentPassword)) {
                model.addAttribute("errorMessage", "Mật khẩu hiện tại không đúng.");
                model.addAttribute("user", currentUser);
                return "profile";
            }

            if (newPassword == null || newPassword.isEmpty() || !newPassword.equals(confirmPassword)) {
                model.addAttribute("errorMessage", "Mật khẩu mới không khớp hoặc không hợp lệ.");
                model.addAttribute("user", currentUser);
                return "profile";
            }

            currentUser.setPassword(userService.encodePassword(newPassword));
        }

        userService.saveUser(currentUser);
        model.addAttribute("successMessage", "Thông tin cá nhân đã được cập nhật.");
        model.addAttribute("user", currentUser);
        return "profile";
    }
}
