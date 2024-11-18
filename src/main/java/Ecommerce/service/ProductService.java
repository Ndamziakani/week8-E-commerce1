package Ecommerce.sevice;


import com.ecommerce.model.Product;
import com.ecommerce.utils.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductService {
    public boolean addProduct(Product product) {
        try (Connection conn = Database.getConnection()) {
            String query = "INSERT INTO products (name, description, price, stock, category_id) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, product.getName());
            stmt.setString(2, product.getDescription());
            stmt.setDouble(3, product.getPrice());
            stmt.setInt(4, product.getStock());
            stmt.setInt(5, product.getCategoryId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        try (Connection conn = Database.getConnection()) {
            String query = "SELECT * FROM products";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Product product = new Product(
                        rs.getInt("product_id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getDouble("price"),
                        rs.getInt("stock"),
                        rs.getInt("category_id")
                );
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
}