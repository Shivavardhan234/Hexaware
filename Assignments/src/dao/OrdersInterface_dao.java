package dao;
import java.sql.*;
import entity.Orders;
import java.util.*;


public interface OrdersInterface_dao {
//---------------------------CRUD methods-------------------------------------	
	void addOrder(Orders order) throws SQLException;
    void getOrderById(int orderId) throws SQLException;
    void getAllOrders() throws SQLException;
    void updateOrderStatus(int orderId, String status) throws SQLException;
    void deleteOrder(int orderId) throws SQLException;

}
