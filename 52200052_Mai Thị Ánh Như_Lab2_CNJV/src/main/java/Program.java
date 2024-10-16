import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.sql.Statement;

public class Program {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java Program <database-url>");
            return;
        }

        // Lấy URL kết nối từ đối số dòng lệnh
        // java -cp "F:\HKI_2024-2025\CONG NGHE JAVA\LAB_02\target\classes;C:\Users\ANHNHU\.m2\repository\com\mysql\mysql-connector-j\8.0.33\mysql-connector-j-8.0.33.jar" Program jdbc:mysql://localhost:3366/lab2 root your_password
        // Set up the connection URL
        String url = args[0];
        String user = args[1];
        String password = args[2];

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("Connection successful to: " + conn.getCatalog());
            ProductDAO productDAO = new ProductDAO(conn);

            initializeDatabase(conn);

            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("\n1. Read product list");
                System.out.println("2. Read product by ID");
                System.out.println("3. Add new product");
                System.out.println("4. Update product");
                System.out.println("5. Delete product");
                System.out.println("6. Exit");
                System.out.print("Your choice: ");

                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        List<Product> products = productDAO.readAll();
                        products.forEach(System.out::println);
                        break;
                    case 2:
                        System.out.print("Enter product ID: ");
                        int id = scanner.nextInt();
                        Product product = productDAO.read(id);
                        System.out.println(product != null ? product : "Product not found.");
                        break;
                    case 3:
                        System.out.print("Enter product ID: ");
                        int productId = scanner.nextInt();
                        System.out.print("Enter product name: ");
                        String name = scanner.next();
                        System.out.print("Enter product price: ");
                        double price = scanner.nextDouble();
                        Product newProduct = new Product(productId, name, price);
                        productDAO.add(newProduct);
                        System.out.println("New product added with ID: " + productId);
                        break;
                    case 4:
                        System.out.print("Enter product ID: ");
                        int updateId = scanner.nextInt();
                        System.out.print("Enter new name: ");
                        String newName = scanner.next();
                        System.out.print("Enter new price: ");
                        double newPrice = scanner.nextDouble();
                        boolean updated = productDAO.update(new Product(updateId, newName, newPrice));
                        System.out.println(updated ? "Update successful." : "Update failed.");
                        break;
                    case 5:
                        System.out.print("Enter product ID to delete: ");
                        int deleteId = scanner.nextInt();
                        boolean deleted = productDAO.delete(deleteId);
                        System.out.println(deleted ? "Delete successful." : "Delete failed.");
                        break;
                    case 6:
                        System.out.println("Exiting the program.");
                        return;
                    default:
                        System.out.println("Invalid choice.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
    }

    private static void initializeDatabase(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            // Drop the Product table if it exists
            stmt.executeUpdate("DROP TABLE IF EXISTS Product");
            // Create the Product table
            stmt.executeUpdate("CREATE TABLE Product (id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255), price DOUBLE)");
        }
    }
}
