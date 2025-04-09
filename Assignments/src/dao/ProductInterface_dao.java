package dao;
import java.sql.*;
import entity.Products;
import java.util.*;



public interface ProductInterface_dao {
//---------------------------------CRUD methods---------------------------------------------------------
	void addProduct(Products product) throws SQLException;
    Products getProductById(int productId) throws SQLException;
    List<Products> getAllProducts() throws SQLException;
    void updateProduct(Products product) throws SQLException;
    void deleteProduct(int productId) throws SQLException;
    
//-------------------------methods to update all attributes-----------------------------------------
    
    void updateProductID(Products product,int ID)throws SQLException;
    void updateProductName(Products product, String name) throws SQLException;
    void updateProductDescription(Products product, String description) throws SQLException;
    void updateProductPrice(Products product, int price) throws SQLException;

}
