package org.example.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.example.DAO.UserDAO;
import org.example.model.User;
import org.example.utils.HibernateUtils;

import java.io.IOException;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UserDAO userDAO;
    public LoginServlet() {
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String remember = req.getParameter("remember");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        try {
            User user = userDAO.findByEmail(email);
            System.out.println(email);
            System.out.println(user);
            if (user == null) {
                req.setAttribute("error", "User not found, please register.");
                RequestDispatcher dispatcher = req.getRequestDispatcher("register.jsp");
                dispatcher.forward(req, resp);
            } else if (user.getPassword().equals(password)) {
                HttpSession session = req.getSession();
                session.setAttribute("USERNAME", user.getUsername());
                session.setAttribute("ID", user.getID());
                req.setAttribute("username", user.getUsername());
                RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
                dispatcher.forward(req, resp);
                if (remember != null) {
                    Cookie cookie = new Cookie("ID", String.valueOf(user.getID()));
                    cookie.setMaxAge(30 * 60 * 60 * 24);
                    resp.addCookie(cookie);
                }
                resp.sendRedirect("/product");

            } else {
                req.setAttribute("error", "Incorrect password, please try again.");
                RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
                dispatcher.forward(req, resp);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void init() throws ServletException {
        super.init();
        this.userDAO=new UserDAO();
    }
}
