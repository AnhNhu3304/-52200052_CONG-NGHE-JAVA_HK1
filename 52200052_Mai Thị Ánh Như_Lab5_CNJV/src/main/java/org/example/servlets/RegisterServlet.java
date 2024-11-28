package org.example.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.DAO.UserDAO;
import org.example.model.User;
import org.example.utils.HibernateUtils;

import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private UserDAO userDAO;
    private HibernateUtils hibernateUtils;

    public RegisterServlet() {
    }

    public RegisterServlet(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("register.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String userName = req.getParameter("username");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirmPassword");
        User user = userDAO.findByEmail(email);
        System.out.println(user);
        if (user != null) {
            req.setAttribute("error", "This email has been register, please try another");
            RequestDispatcher dispatcher = req.getRequestDispatcher("register.jsp");
            dispatcher.forward(req, resp);
        } else {
            if (userName == null || userName.trim().isEmpty()) {
                req.setAttribute("error", "Please fill in this fields username");
                RequestDispatcher dispatcher = req.getRequestDispatcher("register.jsp");
                dispatcher.forward(req, resp);
            } else if (password == null || password.trim().isEmpty()) {
                req.setAttribute("error", "Please fill in this fields password");
                RequestDispatcher dispatcher = req.getRequestDispatcher("register.jsp");
                dispatcher.forward(req, resp);
            } else if (password.length() < 6) {
                req.setAttribute("error", "Password must have at least 6 characters!");
                RequestDispatcher dispatcher = req.getRequestDispatcher("register.jsp");
                dispatcher.forward(req, resp);
            } else if (email == null || email.trim().isEmpty()) {
                req.setAttribute("error", "Please fill in this fields email");
                RequestDispatcher dispatcher = req.getRequestDispatcher("register.jsp");
                dispatcher.forward(req, resp);
            } else if (!password.equals(confirmPassword)) {
                req.setAttribute("error", "Password are not equal");
                RequestDispatcher dispatcher = req.getRequestDispatcher("register.jsp");
                dispatcher.forward(req, resp);
            } else {
                HttpSession session = req.getSession();
                User newUser = new User(userName,password,email);

                userDAO.save(newUser);

                session.setAttribute("USERNAME", userName);
                req.setAttribute("error", "Register successfully");
                RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
                dispatcher.forward(req, resp);
            }

        }
    }


    @Override
    public void init() throws ServletException {
        super.init();
        this.userDAO = new UserDAO();
        // this.hibernateUtils=new HibernateUtils();
    }
}
