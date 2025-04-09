package dao;
import java.sql.*;


import entity.Customers;
import utils.DatabaseConnection;

public class Customer_dao implements CustomerInterface_dao{
	private Connection connection=DatabaseConnection.getConnection();;

    
    
//------------------------------adding customer---------------------------------------------- 
    @Override
    public void addCustomer(Customers customer) throws SQLException {
        String sql = "INSERT INTO Customers (CustomerID, FirstName, LastName, Email, Phone, Address) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, customer.getCustomerID());
            stmt.setString(2, customer.getFirstName());
            stmt.setString(3, customer.getLastName());
            stmt.setString(4, customer.getEmail());
            stmt.setString(5, customer.getPhone());
            stmt.setString(6, customer.getAddress());
            stmt.executeUpdate();
        }
        catch (SQLException e) {
            throw new SQLException("Database error occurred while adding  customer", e);
        }
    }
    
    
//-----------------------------displaying customer info by id---------------------------------------   
    @Override
    public void getCustomerById(int customerId) throws SQLException {
        String sql = "SELECT * FROM Customers WHERE CustomerID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
            	System.out.println("Customer ID: " + rs.getInt("CustomerID"));
                System.out.println("First Name: " + rs.getString("FirstName"));
                System.out.println("Last Name: " + rs.getString("LastName"));
                System.out.println("Email: " + rs.getString("Email"));
                System.out.println("Phone: " + rs.getString("Phone"));
                System.out.println("Address: " + rs.getString("Address"));
        
            }
            else {
                System.out.println("Customer not found with ID: " + customerId);
                }
            
        }
        catch (SQLException e) {
            throw new SQLException("Database error occurred while getting customer details", e);
        }
    }
    
//------------------------------display all customer details-------------------------------------    
    @Override
    public void getAllCustomers() throws SQLException {
        String sql = "SELECT * FROM Customers";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.print("Customer ID: " + rs.getInt("CustomerID"));
                System.out.print("  First Name: " + rs.getString("FirstName"));
                System.out.print("  Last Name: " + rs.getString("LastName"));
                System.out.print("  Email: " + rs.getString("Email"));
                System.out.print("  Phone: " + rs.getString("Phone"));
                System.out.print("  Address: " + rs.getString("Address"));
                System.out.println();
            }
        }
        catch (SQLException e) {
            throw new SQLException("Database error occurred while getting all customer details", e);
        }
       
    }
    
  //------------------------------update customer details---------------------------------------------------
    
    @Override
    public void updateCustomer(Customers customer) throws SQLException {
        String sql = "UPDATE Customers SET FirstName = ?, LastName = ?, Email = ?, Phone = ?, Address = ? WHERE CustomerID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, customer.getFirstName());
            stmt.setString(2, customer.getLastName());
            stmt.setString(3, customer.getEmail());
            stmt.setString(4, customer.getPhone());
            stmt.setString(5, customer.getAddress());
            stmt.setInt(6, customer.getCustomerID());
            stmt.executeUpdate();
        }
        catch (SQLException e) {
            throw new SQLException("Database error occurred while updating customer details", e);
        }
    }
    
//-------------------------------------delete customer by id-----------------------------------------------  
    @Override
    public void deleteCustomer(int customerId) throws SQLException {
        String sql = "DELETE FROM Customers WHERE CustomerID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, customerId);
            stmt.executeUpdate();
        }
        catch (SQLException e) {
            throw new SQLException("Database error occurred while deleting customer", e);
        }
    }
    
    
//----------------------------------update attributrs------------------------------------------------------
    @Override
    public void updateCustomerID(int ID, int customerID) throws SQLException {
        String sql = "UPDATE Customers SET CustomerID = ? WHERE CustomerID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, customerID);
            stmt.setInt(2, ID);
            stmt.executeUpdate();
        }
        catch (SQLException e) {
            throw new SQLException("Database error occurred while updating CustomerID", e);
        }
    }

    @Override
    public void updateCustomerFirstName(int ID, String firstName) throws SQLException {
        String sql = "UPDATE Customers SET FirstName = ? WHERE CustomerID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, firstName);
            stmt.setInt(2, ID);
            stmt.executeUpdate();
        }
        catch (SQLException e) {
            throw new SQLException("Database error occurred while updating Customer First Name", e);
        }
    }

    @Override
    public void updateCustomerLastName(int ID, String lastName) throws SQLException {
        String sql = "UPDATE Customers SET LastName = ? WHERE CustomerID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, lastName);
            stmt.setInt(2, ID);
            stmt.executeUpdate();
        }
        catch (SQLException e) {
            throw new SQLException("Database error occurred while updating Customer Last Name", e);
        }
    }

    @Override
    public void updateEmail(int ID, String email) throws SQLException {
        String sql = "UPDATE Customers SET Email = ? WHERE CustomerID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setInt(2, ID);
            stmt.executeUpdate();
        }
        catch (SQLException e) {
            throw new SQLException("Database error occurred while updating Customer Email", e);
        }
    }

    @Override
    public void updatePhone(int ID, String phone) throws SQLException {
        String sql = "UPDATE Customers SET Phone = ? WHERE CustomerID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, phone);
            stmt.setInt(2, ID);
            stmt.executeUpdate();
        }
        catch (SQLException e) {
            throw new SQLException("Database error occurred while updating Customer Phone number", e);
        }
    }

    @Override
    public void updateCustomerAddress(int ID, String address) throws SQLException {
        String sql = "UPDATE Customers SET Address = ? WHERE CustomerID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, address);
            stmt.setInt(2, ID);
            stmt.executeUpdate();
        }
        catch (SQLException e) {
            throw new SQLException("Database error occurred while updating Customer Address", e);
        }
    }
    
    
}
