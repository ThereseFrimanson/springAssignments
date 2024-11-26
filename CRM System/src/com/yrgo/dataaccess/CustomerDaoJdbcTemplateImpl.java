package com.yrgo.dataaccess;

import com.yrgo.dataaccess.CustomerDao;
import com.yrgo.dataaccess.RecordNotFoundException;
import com.yrgo.domain.Call;
import com.yrgo.domain.Customer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Repository
public class CustomerDaoJdbcTemplateImpl implements CustomerDao {
    private static final String CREATE_CUSTOMER_TABLE = "CREATE TABLE CUSTOMER (CUSTOMER_ID INTEGER generated by default as identity (start with 1), COMPANY_NAME VARCHAR(255), TELEPHONE VARCHAR(30), NOTES VARCHAR(255), EMAIL VARCHAR(255))";
    private static final String CREATE_CUSTOMER_CALLS_TABLE = "CREATE TABLE CUSTOMER_CALLS (CALL_ID INTEGER generated by default as identity (start with 1), CUSTOMER_ID INTEGER, DATE_AND_TIME DATE, NOTES VARCHAR (255))";
    private static final String DELETE_CUSTOMER_SQL = "DELETE FROM CUSTOMER WHERE CUSTOMER_ID=?";
    private static final String UPDATE_CUSTOMER_SQL = "UPDATE CUSTOMER SET COMPANY_NAME=?, TELEPHONE=?, NOTES=?, EMAIL=? WHERE CUSTOMER_ID=?";
    private static final String INSERT_CUSTOMER_SQL = "INSERT INTO CUSTOMER (CUSTOMER_ID, COMPANY_NAME, TELEPHONE, NOTES, EMAIL) VALUES (?,?,?,?,?)";
    private static final String UPDATE_CUSTOMER_CALL_SQL = "UPDATE CUSTOMER_CALLS SET DATE_AND_TIME=?, NOTES=? WHERE CUSTOMER_ID=?";
    private static final String GET_CUSTOMER_BY_ID = "SELECT CUSTOMER_ID, COMPANY_NAME, TELEPHONE, NOTES, EMAIL FROM CUSTOMER WHERE CUSTOMER_ID=?";
    private static final String GET_CUSTOMER_BY_NAME = "SELECT CUSTOMER_ID, COMPANY_NAME, TELEPHONE, NOTES, EMAIL FROM CUSTOMER WHERE COMPANY_NAME=?";
    private static final String GET_ALL_CUSTOMERS = "SELECT * FROM CUSTOMER";
    private static final String GET_ALL_CUSTOMER_CALLS = "SELECT  CALL_ID, CUSTOMER_ID, DATE_AND_TIME, NOTES FROM CUSTOMER_CALLS WHERE CUSTOMER_ID=?";
    private JdbcTemplate template;

    public CustomerDaoJdbcTemplateImpl(JdbcTemplate template){
        this.template = template;
    }
    @Override
    public void create(Customer customer) {
    template.update(INSERT_CUSTOMER_SQL, customer.getCustomerId(),
                                customer.getCompanyName(),
                                customer.getTelephone(),
                                customer.getNotes(),
                                customer.getEmail());
    }

    private void createTables() {
        try{
            this.template.update(CREATE_CUSTOMER_TABLE);
            this.template.update(CREATE_CUSTOMER_CALLS_TABLE);
            System.out.println("Tables created successfully.");
        }catch (org.springframework.jdbc.BadSqlGrammarException e){
            System.out.println("Assuming the Customer table exists");
        }

    }
    @Override
    public Customer getById(String customerId) throws RecordNotFoundException {
        return this.template.queryForObject(GET_CUSTOMER_BY_ID, new CustomerRowMapper(), customerId);
    }


    @Override
    public List<Customer> getByName(String name) {
        return this.template.query(GET_CUSTOMER_BY_NAME, new CustomerRowMapper(), name);
    }

    @Override
    public void update(Customer customerToUpdate) throws RecordNotFoundException {
        this.template.update(UPDATE_CUSTOMER_SQL, customerToUpdate.getCompanyName(),
                customerToUpdate.getTelephone(), customerToUpdate.getNotes(),
                customerToUpdate.getEmail(), customerToUpdate.getCustomerId());

    }

    @Override
    public void delete(Customer oldCustomer) throws RecordNotFoundException {
        this.template.update(DELETE_CUSTOMER_SQL, oldCustomer.getCustomerId());
    }

    @Override
    public List<Customer> getAllCustomers() {
        return this.template.query(GET_ALL_CUSTOMERS, new CustomerRowMapper());
    }

    @Override
    public Customer getFullCustomerDetail(String customerId) throws RecordNotFoundException {
        Customer customer = getById(customerId);
        System.out.println("Fetching calls for customer ID: " + customerId);
        List<Call> calls = this.template.query(GET_ALL_CUSTOMER_CALLS, new CallRowMapper(), customerId);
        customer.setCalls(calls);
        return customer;
    }


    @Override
    public void addCall(Call newCall, String customerId) throws RecordNotFoundException {
        Date timeAndDate = newCall.getTimeAndDate();
        String notes = newCall.getNotes();
        this.template.update(UPDATE_CUSTOMER_CALL_SQL, timeAndDate, notes, customerId);
    }
}

class CustomerRowMapper implements RowMapper<Customer> {
    @Override
    public Customer mapRow(ResultSet resultSet, int i) throws SQLException {
        String customerID = resultSet.getString(1);
        String companyName = resultSet.getString(2);
        String telephone = resultSet.getString(3);
        String notes = resultSet.getString(4);
        String email = resultSet.getString(5);
        return new Customer(customerID, companyName, telephone, notes, email);
    }
}

class CallRowMapper<C> implements RowMapper<Call> {
    public Call mapRow(ResultSet rs, int arg1) throws SQLException {
        String notes = rs.getString("NOTES");
        Date timeAndDate = rs.getDate("TIME_AND_DATE");
        if (timeAndDate == null) {
            timeAndDate = new Date();
        }
        return new Call(notes, timeAndDate);
    }
}
