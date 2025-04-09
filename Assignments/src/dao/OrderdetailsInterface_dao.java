package dao;

import java.sql.*;
import entity.OrderDetails;
import java.util.*;


public interface OrderdetailsInterface_dao {
	
//------------------------CRUD methods---------------------------------------
    void addOrderDetails(int detailId, int orderId, int productId,int  quantity,float discount) throws SQLException;
    void getOrderDetailsById(int orderDetailId) throws SQLException;
    void getAllOrderDetails() throws SQLException;
    void updateOrderDetails(int detailId, int orderId, int productId,int  quantity,float discount) throws SQLException;
    void removeOrderDetails(int orderDetailId) throws SQLException;
    
   
    

}
