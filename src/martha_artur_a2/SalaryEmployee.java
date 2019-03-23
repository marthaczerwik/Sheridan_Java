package martha_artur_a2;

/**
 * Class to create Salary type employees
 * @author Martha Czerwik and Artur Hrytsenko
 */
public class SalaryEmployee extends Employee {

    double salary;

    /**
     * 3 parameter constructor to create instance of Salary type employees
     * @param salary yearly salary of employee
     * @param id employee id
     * @param firstName employee first name
     * @param lastName employee last name
     */
    public SalaryEmployee(double salary, int id, String firstName, String lastName) {
        super(id, firstName, lastName);
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }

    /**
     * Method to calculate weekly pay for salary employees
     * Overrides calculatePay method in Employee class
     * @return weekly pay of salary employee
     */
    @Override
    public double calculatePay() {
        double pay = this.salary / 52;
        return pay;
    }
}
