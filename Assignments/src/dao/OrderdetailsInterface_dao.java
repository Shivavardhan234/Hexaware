package dao;

import java.sql.*;
import entity.OrderDetails;
import java.util.*;


public interface OrderdetailsInterface_dao {
	
//------------------------CRUD methods---------------------------------------
    void addOrderDetails(OrderDetails details) throws SQLException;
    OrderDetails getOrderDetailsById(int orderDetailId) throws SQLException;
    List<OrderDetails> getAllOrderDetails() throws SQLException;
    void updateOrderDetails(OrderDetails details) throws SQLException;
    void removeOrderDetails(int orderDetailId) throws SQLException;
    
   
    

}
