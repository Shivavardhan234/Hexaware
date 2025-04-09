package dao;

import java.sql.*;

import entity.OrderDetails;
import utils.DatabaseConnection;

public class OrderDetails_dao implements OrderdetailsInterface_dao {
	private Connection connection = DatabaseConnection.getConnection();

    

    @Override
    public void addOrderDetails(int detailId, int orderId, int productId,int  quantity,float discount) throws SQLException {
        String sql = "INSERT INTO OrderDetails (OrderDetailID, OrderID, ProductID, Quantity, Discount) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, detailId);
            stmt.setInt(2,  orderId);
            stmt.setInt(3, productId);
            stmt.setInt(4, quantity);
            stmt.setFloat(5, discount);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Failed to add order details", e);
        }
    }

    @Override
    public void getOrderDetailsById(int orderDetailId) throws SQLException {
        String sql = "SELECT * FROM OrderDetails WHERE OrderDetailID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, orderDetailId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.println("OrderDetail ID: " + rs.getInt("OrderDetailID") +
                        ", Order ID: " + rs.getInt("OrderID") +
                        ", Product ID: " + rs.getInt("ProductID") +
                        ", Quantity: " + rs.getInt("Quantity") +
                        ", Discount: " + rs.getFloat("Discount"));
            } else {
                System.out.println("Order details not found for ID: " + orderDetailId);
            }
        } catch (SQLException e) {
            throw new SQLException("Failed to get order details by ID", e);
        }
    }

    @Override
    public void getAllOrderDetails() throws SQLException {
        String sql = "SELECT * FROM OrderDetails";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println("OrderDetail ID: " + rs.getInt("OrderDetailID") +
                        ", Order ID: " + rs.getInt("OrderID") +
                        ", Product ID: " + rs.getInt("ProductID") +
                        ", Quantity: " + rs.getInt("Quantity") +
                        ", Discount: " + rs.getFloat("Discount"));
            }
        } catch (SQLException e) {
            throw new SQLException("Failed to display all order details", e);
        }
    }

    @Override
    public void updateOrderDetails(int detailId, int orderId, int productId,int  quantity,float discount) throws SQLException {
        String sql = "UPDATE OrderDetails SET OrderID = ?, ProductID = ?, Quantity = ?, Discount = ? WHERE OrderDetailID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, orderId);
            stmt.setInt(2, productId);
            stmt.setInt(3, quantity);
            stmt.setFloat(4, discount);
            stmt.setInt(5, detailId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Failed to update order details", e);
        }
    }

    @Override
    public void removeOrderDetails(int orderDetailId) throws SQLException {
        String sql = "DELETE FROM OrderDetails WHERE OrderDetailID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, orderDetailId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Failed to delete order details", e);
        }
    }
}
