package javadb;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Customer;


public class CustomerDAO extends AbstractDAO {

    public CustomerDAO(Connection connection) {
        super(connection);
    }

    public void storeCustomer(Customer customer) {
        String sql = "Insert into Klant(voorletters, voorvoegsels, achternaam, telefoon) values(?,?,?,?) ;";
        try {
            PreparedStatement ps = getStatementWithKey(sql);
            ps.setString(1, customer.getInitials());
            ps.setString(2, customer.getPrefix());
            ps.setString(3, customer.getSurName());
            ps.setString(4, customer.getMobilePhone());
            int key = executeInsertPreparedStatement(ps);
            customer.setCustomerId(key);
        } catch (SQLException e) {
            System.out.println("SQL error " + e.getMessage());
        }
    }

    public void updateCustomer(Customer customer) {
        String sql = "Update Klant Set voorletters = ?, voorvoegsels = ?, achternaam = ?, telefoon = ? where klantnr = ?;";
        try {
            PreparedStatement ps = getStatement(sql);
            ps.setString(1, customer.getInitials());
            ps.setString(2, customer.getPrefix());
            ps.setString(3, customer.getSurName());
            ps.setString(4, customer.getMobilePhone());
            ps.setInt(5, customer.getCustomerId());
            executeManipulatePreparedStatement(ps);
        } catch (SQLException e) {
            System.out.println("SQL error " + e.getMessage());
        }
    }

    public Customer getCustomerById(int custId) {

        String sql = "Select * From Klant Where klantnr = ?";
        Customer result = null;
        try {
            PreparedStatement ps = getStatement(sql);
            ps.setInt(1, custId);
            ResultSet rs = executeSelectPreparedStatement(ps);
            if (rs.next()) {
                String initials = rs.getString("voorletters");
                String prefix = rs.getString("voorvoegsels");
                String surName = rs.getString("achternaam");
                String mobile = rs.getString("telefoon");
                result = new Customer(initials, prefix, surName, mobile);
                result.setCustomerId(custId);
            } else {
                System.out.println("Klant met dit klantnummer bestaat niet");
            }

        }
        catch (SQLException e){
            System.out.println("SQL error " + e.getMessage());
        }
        return result;
    }

    public List<Customer> getAllCustomers() {

        String sql = "Select * From Klant";
        List<Customer> result = new ArrayList<>();
        try {
            PreparedStatement ps = getStatement(sql);
            ResultSet rs = super.executeSelectPreparedStatement(ps);
            Customer customer;
            while (rs.next()) {
                String initials = rs.getString("voorletters");
                String prefix = rs.getString("voorvoegsels");
                String surName = rs.getString("achternaam");
                String mobile = rs.getString("telefoon");
                customer = new Customer(initials, prefix, surName, mobile);
                customer.setCustomerId(rs.getInt("klantnr")); 
                result.add(customer);
            }
        } catch (SQLException e){
                System.out.println("SQL error " + e.getMessage());
            }
        return  result;
    }

}
