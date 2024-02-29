public class EmployeeDauFactory {
    private static EmployeeDao employeeDao;

    private EmployeeDauFactory() {

    }
    public static EmployeeDao getEmployeeDao() {
        if (employeeDao == null) {
            employeeDao = new EmployeeDaoImpl();
        }
        return employeeDao;
    }
}
