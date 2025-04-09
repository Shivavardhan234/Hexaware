package dao;

import java.sql.*;
import java.time.LocalDateTime;

import entity.Inventory;
import java.util.*;


public interface InventoryInterface_dao {
//--------------------------CRUD Methods-------------------------------------------------------	
	void addInventory(Inventory inventory) throws SQLException;
	List<Inventory> getAllInventories() throws SQLException;
    Inventory getInventoryByInventoryId(int InventoryId) throws SQLException;
    void updateInventory(Inventory inventory) throws SQLException;
    void deleteInventory(int inventoryId) throws SQLException;
    
//----------------------------------update method for all attributes-----------------------------
    void updateInventoryID(Inventory inventory, int ID) throws SQLException;
    void updateInventoryQuantity(Inventory inventory, int quantity) throws SQLException;
    void updateInventoryProduct(Inventory inventory, int productId) throws SQLException;
    void updateLastStockUpdate(Inventory inventory, LocalDateTime lastStockUpdate) throws SQLException;


}
