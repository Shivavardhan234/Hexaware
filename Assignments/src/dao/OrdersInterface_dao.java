package dao;
import java.sql.*;
import java.time.LocalDateTime;

import entity.Orders;
import java.util.*;


public interface OrdersInterface_dao {
//---------------------------CRUD methods-------------------------------------	
	void addOrder(int orderId, int customerID, LocalDateTime dt,String status,double  totalAmount) throws SQLException;
    void getOrderById(int orderId) throws SQLException;
    void getAllOrders() throws SQLException;
    void updateOrderStatus(int orderId, String status) throws SQLException;
    void deleteOrder(int orderId) throws SQLException;

}
