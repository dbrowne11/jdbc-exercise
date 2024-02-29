import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeeDaoImpl implements EmployeeDao{
    Connection connection;

    public EmployeeDaoImpl() {
        connection = ConnectionFactory.getConnection();
    }

    @Override
    public void addEmployee(Employee employee) throws SQLException {
        String sql = "insert into employee (name, email) values (?, ?)";
        // prepare ths sql statement dynamically
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, employee.getName());
        preparedStatement.setString(2, employee.getEmail());
        // Execute statement
        int count = preparedStatement.executeUpdate();
        // Print depending on if employee was successfully added
        if (count > 0) {
            System.out.println("employee saved");
        } else {
            System.out.println("Something went wrong, please try again");
        }
    }

    @Override
    public void updateEmployee(Employee employee) throws SQLException {
        String sql = "update employee set name = ?, email = ? where emp_id=?\n";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        // prepare ths sql statement dynamically
        preparedStatement.setString(1, employee.getName());
        preparedStatement.setString(2, employee.getEmail());
        preparedStatement.setInt(3, employee.getId());
        // Execute
        int count = preparedStatement.executeUpdate();
        // Print depending on update changing at least 1 column
        if (count > 0) {
            System.out.println("employee updated");
        } else {
            System.out.println("Something went wrong, please try again");
        }
    }

    @Override
    public void deleteEmployee(int id) throws SQLException {
        // Set up statement
        String sql = "delete from employee where emp_id=?\n";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        // execute
        int count = preparedStatement.executeUpdate();
        // print
        if (count > 0) {
            System.out.println("employee deleted");
        } else {
            System.out.println("Something went wrong, please try again");
        }
    }

    @Override
    public List<Employee> getEmployees() throws SQLException {
        // set up statement
        String query = "select * from employee";
        Statement statement = connection.createStatement();
        // execute statement
        ResultSet results =  statement.executeQuery(query);
        List<Employee> employees = new ArrayList<>();
        // Iterate to print and fill output list
        while (results.next()) {
            Employee emp = new Employee();
            emp.setId(results.getInt(1));
            emp.setName(results.getString(2));
            emp.setEmail(results.getString(3));
            employees.add(emp);
            System.out.println("Got employee: " + emp.getId() +
                    " Name: " + emp.getName() +
                    " Email: " + emp.getEmail());
        }
        return employees;
    }

    @Override
    public Employee getEmployeeById(int id) throws SQLException {
        // set up statement
        String query = "select * from employee where emp_id=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        // execute
        ResultSet resultSet = statement.executeQuery();
        // get data from resultSet
        resultSet.next();
        Employee emp = new Employee();
        emp.setId(resultSet.getInt(1));
        emp.setName(resultSet.getString(2));
        emp.setEmail(resultSet.getString(3));
        return emp;
    }
}

class Main2 {

    public static void main(String[] args) {
        EmployeeDaoImpl empDao = new EmployeeDaoImpl();

        //Employee emp1 = new Employee(3, "Mark2", "m2@gmail.com");

        boolean flag = true;
        Scanner scanner = new Scanner(System.in);
        EmployeeDao dao = EmployeeDauFactory.getEmployeeDao();
        Employee emp1 = new Employee();
        emp1.setName("Mark2");
        emp1.setEmail("m2@gmail.com");


        //Employee updateEmp = new Employee(2, "Watson", "watson@gmail.com");
        try {
            System.out.println(dao.getEmployees());
            dao.addEmployee(emp1);
            System.out.println(dao.getEmployees());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        emp1.setName("Jimothy");
        try {
            dao.updateEmployee(emp1);
            System.out.println(dao.getEmployees());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            dao.deleteEmployee(dao.getEmployeeById(10).getId());
            System.out.println(dao.getEmployees());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
