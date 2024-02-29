import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        EmployeeDao employeeDao = EmployeeDauFactory.getEmployeeDao();


        //Employee emp1 = new Employee(3, "Mark2", "m2@gmail.com");

        boolean flag = true;
        Scanner scanner = new Scanner(System.in);
        while (flag) {
            System.out.println("""
                    Select database operation from the following options:\s
                    1: Add to database\s
                    2: Update database\s
                    3: Delete from database\s
                    4: Get Employee by ID
                    5: Get all employees
                    6: Exit""");
            int input = scanner.nextInt();
            //Variables common between multiple switch statements
            Employee employee;
            int empId;

            switch (input) {
                // Adding to databse
                case 1:
                    // Prompt and read input
                    System.out.println("database add selected");
                    System.out.println("Enter your name...");
                    String addName = scanner.next();
                    System.out.println("Enter your email...");
                    String addEmail = scanner.next();
                    // creating employee object
                    employee = new Employee();
                    employee.setName(addName);
                    employee.setEmail(addEmail);
                    // execute add command
                    try {
                        employeeDao.addEmployee(employee);
                        System.out.println("Successfully added " + employee);
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                // Updating database
                case 2:
                    // Prompt and read input
                    System.out.println("Database Update Selected");
                    System.out.println("Enter target employee's ID");
                    int id = scanner.nextInt();
                    System.out.println("Enter your name...");
                    String updateName = scanner.next();
                    System.out.println("Enter your email...");
                    String updateEmail = scanner.next();
                    // creating employee object
                    employee = new Employee();
                    employee.setName(updateName);
                    employee.setEmail(updateEmail);
                    employee.setId(id);
                    // execute update command
                    try {
                        employeeDao.updateEmployee(employee);
                        System.out.println("Successfully updated " + employee);
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                //Delete from database
                case 3:
                    // Prompt and read input
                    System.out.println("Database deletion selected");
                    System.out.println("Enter employee Id of employee");
                    empId = scanner.nextInt();
                    // execute delete command
                    try {
                        employeeDao.deleteEmployee(empId);
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                // Get by ID
                case 4:
                    // Prompt and read input
                    System.out.println("Selected get employee");
                    System.out.println("Enter employee ID");
                    empId = scanner.nextInt();
                    // execute get command
                    try {
                        Employee emp = employeeDao.getEmployeeById(empId);
                        System.out.println(emp.toString());
                    } catch (SQLException e) {
                        System.out.printf(e.getMessage());
                    }
                    break;
                // Get all Employees
                case 5:
                    System.out.println("Selected get all employees");
                    // execute get command
                    try {
                        List<Employee> employees = employeeDao.getEmployees();
                        employees.forEach(System.out::println);
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 6:
                    flag = false;
                    break;
                default:
                    System.out.println("Ensure number selection is in range [1-6]");
            }
        }






    }
}