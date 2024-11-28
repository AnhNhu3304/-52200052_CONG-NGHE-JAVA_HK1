package org.example.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.DAO.ProductDAO;
import org.example.model.Product;
import org.example.utils.HibernateUtils;

import java.io.IOException;
import java.util.List;

@WebServlet("/product")
public class ProductServlet extends HttpServlet {

    private ProductDAO productDAO;
    private HibernateUtils hibernateUtils;

    public ProductServlet() {
    }

    public ProductServlet(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> productList = productDAO.getAllProducts();
        req.setAttribute("products", productList);
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }

    @Override
    public void init() throws ServletException {
        this.productDAO = new ProductDAO();
        super.init();
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String productName = req.getParameter("productName");
        String priceString = req.getParameter("priceProduct");
        System.out.println(productName);
        System.out.println(priceString);
        int price = 0;
        if (priceString != null && !priceString.isEmpty()) {
            try {
                price = Integer.parseInt(priceString);
            } catch (NumberFormatException e) {
                System.out.println("Invalid price value: " + priceString);
            }
        } else {
            System.out.println("Price parameter is null or empty");
        }
        Product product = productDAO.getProductByProductName(productName);

        if (productName == null || price == 0) {
            req.setAttribute("error", "Please enter this field");
            RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
            dispatcher.forward(req, resp);
        } else if (product != null) {
            req.setAttribute("error", "This name already exists, please rename it");
            RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
            dispatcher.forward(req, resp);
        } else {
            product = new Product();
            product.setName(productName);
            product.setPrice(price);
            productDAO.add(product);
            req.setAttribute("error", "Add product successfully");
            RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
            dispatcher.forward(req, resp);
            resp.sendRedirect("/product");
        }
    }
}
