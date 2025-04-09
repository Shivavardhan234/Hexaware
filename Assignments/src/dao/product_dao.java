package dao;
import utils.DatabaseConnection;
import java.sql.*;

import entity.Products;

public class product_dao implements ProductInterface_dao{
	private Connection connection = DatabaseConnection.getConnection();

    

    @Override
    public void addProduct(Products product) throws SQLException {
        String sql = "INSERT INTO Products (ProductID, ProductName, ProductDescription, ProductPrice) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, product.getProductID());
            stmt.setString(2, product.getProductName());
            stmt.setString(3, product.getDescription());
            stmt.setDouble(4, product.getPrice());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Database error occurred while adding product", e);
        }
    }

    @Override
    public void getProductById(int productId) throws SQLException {
        String sql = "SELECT * FROM Products WHERE ProductID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, productId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.println("Product ID: " + rs.getInt("ProductID"));
                System.out.println("Product Name: " + rs.getString("ProductName"));
                System.out.println("Product Description: " + rs.getString("ProductDescription"));
                System.out.println("Product Price: " + rs.getInt("ProductPrice"));
            }
            else {
                System.out.println("Product not found with ID: " + productId);
                } 
        }catch (SQLException e) {
            throw new SQLException("Database error occurred while getting product by ID", e);
        }
    }

    @Override
    public void getAllProducts() throws SQLException {
        String sql = "SELECT * FROM Products";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println("Product ID: " + rs.getInt("ProductID") +
                                   ", Name: " + rs.getString("ProductName") +
                                   ", Description: " + rs.getString("ProductDescription") +
                                   ", Price: " + rs.getInt("ProductPrice"));
            }
        } catch (SQLException e) {
            throw new SQLException("Database error occurred while displaying all products", e);
        }
    }

    @Override
    public void updateProduct(Products product) throws SQLException {
        String sql = "UPDATE Products SET ProductName = ?, ProductDescription = ?, ProductPrice = ? WHERE ProductID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, product.getProductName());
            stmt.setString(2, product.getDescription());
            stmt.setDouble(3, product.getPrice());
            stmt.setInt(4, product.getProductID());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Database error occurred while updating the product", e);
        }
    }

    @Override
    public void deleteProduct(int productId) throws SQLException {
        String sql = "DELETE FROM Products WHERE ProductID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, productId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Database error occurred while deleting the product", e);
        }
    }

    @Override
    public void updateProductID(int ID, int productID) throws SQLException {
        String sql = "UPDATE Products SET ProductID = ? WHERE ProductID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, productID);
            stmt.setInt(2, ID);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Database error occurred while updating ProductID", e);
        }
    }

    @Override
    public void updateProductName(int ID, String name) throws SQLException {
        String sql = "UPDATE Products SET ProductName = ? WHERE ProductID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setInt(2, ID);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Database error occurred while updating ProductName", e);
        }
    }

    @Override
    public void updateProductDescription(int ID, String description) throws SQLException {
        String sql = "UPDATE Products SET ProductDescription = ? WHERE ProductID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, description);
            stmt.setInt(2, ID);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Database error occurred while updating ProductDescription", e);
        }
    }

    @Override
    public void updateProductPrice(int ID, int price) throws SQLException {
        String sql = "UPDATE Products SET ProductPrice = ? WHERE ProductID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, price);
            stmt.setInt(2, ID);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Database error occurred while updating ProductPrice", e);
        }
    }

}
