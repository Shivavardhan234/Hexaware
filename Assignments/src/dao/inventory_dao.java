package dao;

import java.sql.*;
import java.time.LocalDateTime;
import entity.*;
import utils.DatabaseConnection;

public class inventory_dao implements InventoryInterface_dao {
	private Connection connection = DatabaseConnection.getConnection();

	@Override
    public void addInventory(int inventoryId, int productId, int quantity, LocalDateTime lastStockUpdate) throws SQLException {
        String sql = "INSERT INTO Inventory (InventoryID, ProductID, QuantityInStock, LastStockUpdate) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, inventoryId);
            stmt.setInt(2, productId);
            stmt.setInt(3, quantity);
            stmt.setTimestamp(4, Timestamp.valueOf(lastStockUpdate));
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Failed to add inventory", e);
        }
    }

    @Override
    public void getAllInventories() throws SQLException {
        String sql = "SELECT * FROM Inventory";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println("InventoryID: " + rs.getInt("InventoryID") +
                        ", ProductID: " + rs.getInt("ProductID") +
                        ", QuantityInStock: " + rs.getInt("QuantityInStock") +
                        ", LastStockUpdate: " + rs.getTimestamp("LastStockUpdate"));
            }
        } catch (SQLException e) {
            throw new SQLException("Failed to retrieve inventory list", e);
        }
    }

    @Override
    public void getInventoryByInventoryId(int inventoryId) throws SQLException {
        String sql = "SELECT * FROM Inventory WHERE InventoryID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, inventoryId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.println("InventoryID: " + rs.getInt("InventoryID") +
                        ", ProductID: " + rs.getInt("ProductID") +
                        ", QuantityInStock: " + rs.getInt("QuantityInStock") +
                        ", LastStockUpdate: " + rs.getTimestamp("LastStockUpdate"));
            } else {
                System.out.println("No inventory found with ID: " + inventoryId);
            }
        } catch (SQLException e) {
            throw new SQLException("Failed to get inventory by ID", e);
        }
    }

    @Override
    public void updateInventory(int inventoryId, int productId, int quantity, LocalDateTime lastStockUpdate) throws SQLException {
        String sql = "UPDATE Inventory SET ProductID = ?, QuantityInStock = ?, LastStockUpdate = ? WHERE InventoryID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, productId);
            stmt.setInt(2, quantity);
            stmt.setTimestamp(3, Timestamp.valueOf(lastStockUpdate));
            stmt.setInt(4, inventoryId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Failed to update inventory", e);
        }
    }

    @Override
    public void deleteInventory(int inventoryId) throws SQLException {
        String sql = "DELETE FROM Inventory WHERE InventoryID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, inventoryId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Failed to delete inventory", e);
        }
    }

    @Override
    public void updateInventoryID(int ID, int invID) throws SQLException {
        String sql = "UPDATE Inventory SET InventoryID = ? WHERE InventoryID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, invID);
            stmt.setInt(2, ID);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Failed to update InventoryID", e);
        }
    }

    @Override
    public void updateInventoryQuantity(int ID, int quantity) throws SQLException {
        String sql = "UPDATE Inventory SET QuantityInStock = ? WHERE InventoryID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, quantity);
            stmt.setInt(2, ID);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Failed to update QuantityInStock", e);
        }
    }

    @Override
    public void updateInventoryProduct(int ID, int productId) throws SQLException {
        String sql = "UPDATE Inventory SET ProductID = ? WHERE InventoryID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, productId);
            stmt.setInt(2, ID);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Failed to update ProductID", e);
        }
    }

    @Override
    public void updateLastStockUpdate(int ID, LocalDateTime lastStockUpdate) throws SQLException {
        String sql = "UPDATE Inventory SET LastStockUpdate = ? WHERE InventoryID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setTimestamp(1, Timestamp.valueOf(lastStockUpdate));
            stmt.setInt(2, ID);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Failed to update LastStockUpdate", e);
        }
    }
}
