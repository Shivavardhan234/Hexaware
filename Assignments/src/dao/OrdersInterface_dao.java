package dao;
import java.sql.*;
import entity.Orders;
import java.util.*;


public interface OrdersInterface_dao {
//---------------------------CRUD methods-------------------------------------	
	void placeOrder(Orders order) throws SQLException;
    Orders getOrderById(int orderId) throws SQLException;
    List<Orders> getAllOrders() throws SQLException;
    void updateOrderStatus(int orderId, String status) throws SQLException;
    void deleteOrder(int orderId) throws SQLException;

}
