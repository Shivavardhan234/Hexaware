package dao;

import java.sql.*;
import entity.Customers;
import java.util.*;


public interface CustomerInterface_dao {
	
//----------------CRUD methods--------------------------------------------------
	 	void addCustomer(Customers customer) throws SQLException;
	    void getCustomerById(int customerId) throws SQLException;
	    void getAllCustomers() throws SQLException;
	    void updateCustomer(Customers customer) throws SQLException;
	    void deleteCustomer(int customerId) throws SQLException;
	    
//-------------------------update method for every single attribute-------------------------------
	    void updateCustomerID(int oldID,int newcustomerID) throws SQLException;
	    void updateCustomerFirstName(int ID,String FirstName) throws SQLException;
	    void updateCustomerLastName(int ID,String LastName) throws SQLException;
	    void updateEmail(int ID,String Email) throws SQLException;
	    void updatePhone(int ID,String phone) throws SQLException;
	    void updateCustomerAddress(int ID,String Address) throws SQLException;

}
