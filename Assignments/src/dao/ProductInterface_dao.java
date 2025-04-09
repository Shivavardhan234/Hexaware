package dao;
import java.sql.*;
import entity.Products;
import java.util.*;



public interface ProductInterface_dao {
//---------------------------------CRUD methods---------------------------------------------------------
	void addProduct(Products product) throws SQLException;
    void getProductById(int productId) throws SQLException;
    void getAllProducts() throws SQLException;
    void updateProduct(Products product) throws SQLException;
    void deleteProduct(int productId) throws SQLException;
    
//-------------------------methods to update all attributes-----------------------------------------
    
    void updateProductID(int ID,int productID)throws SQLException;
    void updateProductName(int ID , String name) throws SQLException;
    void updateProductDescription(int ID, String description) throws SQLException;
    void updateProductPrice(int ID, int price) throws SQLException;

}
