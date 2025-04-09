package dao;

import java.sql.*;
import java.time.LocalDateTime;

import entity.Inventory;
import java.util.*;


public interface InventoryInterface_dao {
//--------------------------CRUD Methods-------------------------------------------------------	
	void addInventory(Inventory inventory) throws SQLException;
	void getAllInventories() throws SQLException;
    void getInventoryByInventoryId(int InventoryId) throws SQLException;
    void updateInventory(Inventory inventory) throws SQLException;
    void deleteInventory(int inventoryId) throws SQLException;
    
//----------------------------------update method for all attributes-----------------------------
    void updateInventoryID(int ID, int invID) throws SQLException;
    void updateInventoryQuantity(int ID, int quantity) throws SQLException;
    void updateInventoryProduct(int ID, int productId) throws SQLException;
    void updateLastStockUpdate(int ID, LocalDateTime lastStockUpdate) throws SQLException;


}
