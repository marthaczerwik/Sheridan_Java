package martha_artur_a2;

/**
 * Abstract employee class 
 * @author Martha Czerwik and Artur Hrytsenko
 */
public abstract class Employee {

    private int id;
    private String firstName;
    private String lastName;

    /**
     * 3 parameter constructor to create an employee instance
     * @param id employee id
     * @param firstName employee first name
     * @param lastName employee last name
     */
    public Employee(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * abstract method for other classes to calculate pay
     * @return 
     */
    abstract public double calculatePay();

    /**
     * Method to display employee information; this is called on from the Model 
     * class, displayEmployeeInformation method
     * @return employee information string format
     */
    @Override
    public String toString() {
        return "\nEmployee " + getId() + ": " + getLastName() + ", " + getFirstName();
    }
}
