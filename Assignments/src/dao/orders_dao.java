package dao;
import java.sql.*;
import java.time.LocalDateTime;

import entity.Orders;
import utils.DatabaseConnection;
public class orders_dao implements OrdersInterface_dao {

	
	private Connection connection = DatabaseConnection.getConnection();

    
	 @Override
	    public void addOrder(int orderId, int customerID, LocalDateTime dt,String status,double  totalAmount) throws SQLException {
	        String sql = "INSERT INTO Orders (OrderID, CustomerID, OrderDate, orderStatus, TotalAmount) VALUES (?, ?, ?, ?, ?)";
	        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
	            stmt.setInt(1, orderId);
	            stmt.setInt(2, customerID);
	            stmt.setTimestamp(3, Timestamp.valueOf(dt));
	            stmt.setString(4, status);
	            stmt.setDouble(5, totalAmount);
	            stmt.executeUpdate();
	        } catch (SQLException e) {
	            throw new SQLException("Failed to add the order", e);
	        }
	    }

	    @Override
	    public void getOrderById(int orderId) throws SQLException {
	        String sql = "SELECT * FROM Orders WHERE OrderID = ?";
	        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
	            stmt.setInt(1, orderId);
	            ResultSet rs = stmt.executeQuery();
	            if (rs.next()) {
	                System.out.println("Order ID: " + rs.getInt("OrderID") +
	                        ", Customer ID: " + rs.getInt("CustomerID") +
	                        ", Date: " + rs.getTimestamp("OrderDate") +
	                        ", Status: " + rs.getString("orderStatus") +
	                        ", Total Amount: " + rs.getDouble("TotalAmount"));
	            } else {
	                System.out.println("Order not found with ID: " + orderId);
	            }
	        } catch (SQLException e) {
	            throw new SQLException("Failed to get order by its id", e);
	        }
	    }

	    @Override
	    public void getAllOrders() throws SQLException {
	        String sql = "SELECT * FROM Orders";
	        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
	            while (rs.next()) {
	                System.out.println("Order ID: " + rs.getInt("OrderID") +
	                        ", Customer ID: " + rs.getInt("CustomerID") +
	                        ", Date: " + rs.getTimestamp("OrderDate") +
	                        ", Status: " + rs.getString("orderStatus") +
	                        ", Total Amount: " + rs.getDouble("TotalAmount"));
	            }
	        } catch (SQLException e) {
	            throw new SQLException("Failed to get all orders", e);
	        }
	    }

	    @Override
	    public void updateOrderStatus(int orderId, String status) throws SQLException {
	        String sql = "UPDATE Orders SET orderStatus = ? WHERE OrderID = ?";
	        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
	            stmt.setString(1, status);
	            stmt.setInt(2, orderId);
	            stmt.executeUpdate();
	        } catch (SQLException e) {
	            throw new SQLException("Failed to update the order status", e);
	        }
	    }

	    @Override
	    public void deleteOrder(int orderId) throws SQLException {
	        String sql = "DELETE FROM Orders WHERE OrderID = ?";
	        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
	            stmt.setInt(1, orderId);
	            stmt.executeUpdate();
	        } catch (SQLException e) {
	            throw new SQLException("Failed to delete the order", e);
	        }
	    }
}
