//package com.example.usermannager.dao;
//
//public class UserDAO {
//}
package com.example.employeemanager.dao;

import com.example.employeemanager.model.Employee;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    private String jdbcURL = "jdbc:mysql://localhost:3306/yourDatabaseName?useSSL=false";
    private String jdbcUsername = "yourUsername";
    private String jdbcPassword = "yourPassword";

    private static final String INSERT_EMPLOYEE_SQL =
            "INSERT INTO employee (first_name, last_name, email, position, salary) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_EMPLOYEE_BY_ID =
            "SELECT id, first_name, last_name, email, position, salary FROM employee WHERE id = ?";
    private static final String SELECT_ALL_EMPLOYEES =
            "SELECT * FROM employee";
    private static final String DELETE_EMPLOYEE_SQL =
            "DELETE FROM employee WHERE id = ?";
    private static final String UPDATE_EMPLOYEE_SQL =
            "UPDATE employee SET first_name = ?, last_name = ?, email = ?, position = ?, salary = ? WHERE id = ?";

    // Get a database connection
    protected Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        return connection;
    }

    // CREATE
    public void insertEmployee(Employee employee) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EMPLOYEE_SQL)) {
            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getLastName());
            preparedStatement.setString(3, employee.getEmail());
            preparedStatement.setString(4, employee.getPosition());
            preparedStatement.setDouble(5, employee.getSalary());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // READ: Select employee by ID
    public Employee selectEmployee(int id) {
        Employee employee = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EMPLOYEE_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String email = rs.getString("email");
                String position = rs.getString("position");
                double salary = rs.getDouble("salary");
                employee = new Employee(id, firstName, lastName, email, position, salary);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

    // READ: Select all employees
    public List<Employee> selectAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_EMPLOYEES)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String email = rs.getString("email");
                String position = rs.getString("position");
                double salary = rs.getDouble("salary");
                employees.add(new Employee(id, firstName, lastName, email, position, salary));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    // UPDATE
    public boolean updateEmployee(Employee employee) {
        boolean rowUpdated = false;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_EMPLOYEE_SQL)) {
            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getLastName());
            preparedStatement.setString(3, employee.getEmail());
            preparedStatement.setString(4, employee.getPosition());
            preparedStatement.setDouble(5, employee.getSalary());
            preparedStatement.setInt(6, employee.getId());
            rowUpdated = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowUpdated;
    }

    // DELETE
    public boolean deleteEmployee(int id) {
        boolean rowDeleted = false;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_EMPLOYEE_SQL)) {
            preparedStatement.setInt(1, id);
            rowDeleted = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowDeleted;
    }
}
