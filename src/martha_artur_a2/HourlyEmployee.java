package martha_artur_a2;

/**
 * Class to create Hourly type employees
 * @author Martha Czerwik and Artur Hrytsenko
 */
public class HourlyEmployee extends Employee {

    double numHours;
    double hourlyRate;

    /**
     * 3 parameter constructor to create instance of Hourly type employees
     * @param numHours number of hours per week employee works
     * @param hourlyRate pay employee earns per hour of work
     * @param id employee id
     * @param firstName employee first name
     * @param lastName employee last name
     */
    public HourlyEmployee(double numHours, double hourlyRate, int id, String firstName, String lastName) {
        super(id, firstName, lastName);
        this.numHours = numHours;
        this.hourlyRate = hourlyRate;
    }

    public double getNumHours() {
        return numHours;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    /**
     * Method to calculate weekly pay for hourly employees
     * Overrides calculatePay method in Employee class
     * @return weekly pay of hourly employee
     */
    @Override
    public double calculatePay() {
        double pay = this.numHours * this.hourlyRate;
        return pay;
    }

}
