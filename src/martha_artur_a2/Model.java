package martha_artur_a2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * This class provides an interface for the user to input and view employee
 * information and provides the user with menus to select options from
 *
 * @author Martha Czerwik and Artur Hrytsenko
 */
public class Model {

    /**
     * Main method prompts user to create an array of employees, which follows
     * with a menu to begin inputting type of employee data; This is passed to
     * the createEmployee method
     * @exception InputMismatchException User must only enter input according to
     * type allowed
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Model newEmployee = new Model();

        Scanner in = new Scanner(System.in);
        String message = ("How many employees to create?");
        System.out.println(message);
        int userInput = 0;
        do {
            try {
                userInput = in.nextInt();
            } catch (InputMismatchException ex) {
                System.out.println("Error: Invalid input. " + message);
                in.next();
            }
        } while (userInput <= 0);

        //employee array
        Employee[] employees = new Employee[userInput];

        //employee data for employee __ of __
        for (int x = 0; x < employees.length; x++) {
            int loopControl = 0;
            int typeEmployee = 0;

            do {
                try {
                    int innerLoopControl = 0;
                    do {
                        System.out.println("\nEmployee data for employee " + (x + 1) + " of "
                                + employees.length);
                        System.out.println("Choose type of employee to add:\n1. Salaried\n"
                                + "2. Hourly\n3. Commission\n");

                        typeEmployee = in.nextInt();
                        if (typeEmployee > 3 || typeEmployee < 0) {
                            System.out.println("Error: Please enter a valid option");
                        } else {
                            innerLoopControl = 1;
                        }
                    } while (innerLoopControl == 0);
                    loopControl = 1;
                } catch (InputMismatchException ex) {
                    System.out.println("Error: Invalid Input");
                    in.next();
                }
            } while (loopControl == 0);
            switch (typeEmployee) {
                case 1:
                    employees[x] = newEmployee.createEmployee(1, employees);
                    break;
                case 2:
                    employees[x] = newEmployee.createEmployee(2, employees);
                    break;
                case 3:
                    employees[x] = newEmployee.createEmployee(3, employees);
                    break;
            }

        }

        //choose a report
        int typeReport = 0;
        do {
            System.out.println("\nChoose a report:\n1. Employee Listing\n2. Payroll"
                    + " listing\n3. Exit\n");
            try {
                typeReport = in.nextInt();
                if (typeReport > 3) {
                    System.out.println("Error: Invalid Input. Please select a valid option.");
                }
            } catch (InputMismatchException ex) {
                System.out.println("Error: Invalid Input.");
                in.next();
            }
            switch (typeReport) {
                case 1:
                    displayEmployeeInfo(employees);
                    break;
                case 2:
                    displayEmployeePayroll(employees);
                    break;
            }

        } while (typeReport != 3);
        System.out.println("\nThank you for using the program\n");

    }//end of main

    /**
     * This method displays weekly employee payroll information for all
     * employees in the array Information is called upon from instances of
     * employees created
     *
     * @param employees the array employees is passed here in order to access
     * information about each employee in the array created
     */
    public static void displayEmployeePayroll(Employee[] employees) {
        String format = "Weekly pay for %s, %s employee ID %d is $%.2f%n";

        for (int x = 0; x < employees.length; x++) {
            if (employees[x] instanceof HourlyEmployee) {
                HourlyEmployee employee = (HourlyEmployee) employees[x];
                System.out.printf(format, employee.getLastName(), employee.getFirstName(), employee.getId(), employee.calculatePay());
            } else if (employees[x] instanceof SalaryEmployee) {
                SalaryEmployee employee = (SalaryEmployee) employees[x];
                System.out.printf(format, employee.getLastName(), employee.getFirstName(), employee.getId(), employee.calculatePay());
            } else if (employees[x] instanceof CommissionEmployee) {
                CommissionEmployee employee = (CommissionEmployee) employees[x];
                System.out.printf(format, employee.getLastName(), employee.getFirstName(), employee.getId(), employee.calculatePay());
            }
        }
    } //end of displayEmployeePayroll method

    /**
     * This method displays employee information of all employees in the array
     * Information is called upon from instances of employees created
     
     * @param employees the array employees is passed here in order to access
     * information about each employee in the array created
     */
    public static void displayEmployeeInfo(Employee[] employees) {

        for (int x = 0; x < employees.length; x++) {
            if (employees[x] instanceof HourlyEmployee) {
                HourlyEmployee employee = (HourlyEmployee) employees[x];
                System.out.println(employee.toString());
                System.out.printf("Hours: %.2f%nRate: $%.2f%n", employee.getNumHours(), employee.getHourlyRate());
            } else if (employees[x] instanceof SalaryEmployee) {
                SalaryEmployee employee = (SalaryEmployee) employees[x];
                System.out.println(employee.toString());
                System.out.printf("Salary: $%.2f%n", employee.getSalary());
            } else if (employees[x] instanceof CommissionEmployee) {
                CommissionEmployee employee = (CommissionEmployee) employees[x];
                System.out.println(employee.toString());
                System.out.printf("Commission Rate: %.2f%nSales: $%.2f%n", employee.getRate(), employee.getSales());
            }
        }
    } //end of displayEmployeeInfo method

    /**
     *
     * @param type once the user selects what type of employee they are entering
     * into the system, type number is passed to this method in order to prompt
     * user to enter necessary employee information for the specific type of
     * employee
     * @param employees employee array is passed
     * @return null to compile without errors 
     * @exception InputMismatchException
     */
    public Employee createEmployee(int type, Employee[] employees) {

        Scanner in = new Scanner(System.in);
        int loopControl = 0;
        while (loopControl == 0) {

            try {
                //user input for id
                int tempId;
                int employeeId = 0;
                String idMessage = ("Employee ID: ");
                do {
                    System.out.print(idMessage);
                    tempId = in.nextInt();
                    employeeId = idCheck(employees, tempId);
                } while (employeeId == -1);

                if (employeeId <= 0) {
                    System.out.println("Error: ID must be greater than 0.");
                    System.out.print(idMessage);
                    in.nextInt();
                }

                //user input for first name                       
                System.out.print("First name: ");
                String employeeFirstName = in.next();
                if (!employeeFirstName.matches("^[a-zA-Z]+$")) {
                    System.out.println("Please enter a valid name: ");
                    in.nextLine();
                }

                //user input for last name            
                System.out.print("Last name: ");
                String employeeLastName = in.next();
                if (!employeeLastName.matches("^[a-zA-Z]+$")) {
                    System.out.println("Please enter a valid name: ");
                    in.nextLine();
                }

                //additional user input for salary employees        
                if (type == 1) {
                    String salaryMessage = ("Salary: ");
                    System.out.print(salaryMessage);
                    double employeeSalary = in.nextDouble();
                    if (employeeSalary <= 0) {
                        System.out.println("Error: Salary must be greater than 0.");
                        System.out.print(salaryMessage);
                        in.nextDouble();
                    }
                    Employee employee = new SalaryEmployee(employeeSalary, employeeId, employeeFirstName, employeeLastName);

                    return employee;
                }
                //additional user input for hourly employees

                if (type == 2) {
                    String hoursMessage = ("Hours Worked: ");
                    System.out.print(hoursMessage);
                    double employeeHoursWorked = in.nextDouble();
                    if (employeeHoursWorked < 0) {
                        System.out.println("Error: Hours must be over 0.");
                        System.out.print(hoursMessage);
                        in.nextDouble();
                    }
                    String rateMessage = ("Hourly Rate: ");
                    System.out.print(rateMessage);
                    double employeeHourlyRate = in.nextDouble();
                    if (employeeHourlyRate <= 0) {
                        System.out.println("Error: Rate must be over 0.");
                        System.out.print(rateMessage);
                        in.nextDouble();
                    }
                    Employee employee = new HourlyEmployee(employeeHoursWorked, employeeHourlyRate, employeeId, employeeFirstName, employeeLastName);
                    return employee;
                }
                //additional user input for commission employees        
                if (type == 3) {

                    String rateMessage = ("Commission rate: ");
                    System.out.print(rateMessage);
                    double employeeCommissionRate = in.nextDouble();
                    if (employeeCommissionRate < 0.5 || employeeCommissionRate > 20) {
                        System.out.println("Error: Rate must be between 0.5 and 20");
                        System.out.print(rateMessage);
                        in.nextDouble();
                    }
                    String salesMessage = ("Sales: ");
                    System.out.print(salesMessage);
                    double employeeSales = in.nextDouble();
                    if (employeeSales <= 0) {
                        System.out.println("Error: Sales must be greater than 0");
                        System.out.print(rateMessage);
                        in.nextDouble();
                    }

                    Employee employee = new CommissionEmployee(employeeCommissionRate, employeeSales, employeeId, employeeFirstName, employeeLastName);
                    return employee;
                }

            } catch (InputMismatchException ex) {

                System.out.println("Error: Invalid input. Please start over.");
                in.next();
            }
        }

        return null;

    } //end create employee method

    public int idCheck(Employee[] employees, int id) {
        Scanner in = new Scanner(System.in);
        int idToReturn;
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null) {
                if (employees[i].getId() == id) {
                    System.out.println("ID has already been entered. Enter a new ID:");
                    return -1;
                }
            }
        }
        idToReturn = id;
        return idToReturn;
    }//end of idcheck method
    
} //end of class Model


