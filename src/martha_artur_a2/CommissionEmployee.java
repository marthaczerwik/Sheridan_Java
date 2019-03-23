package martha_artur_a2;

/**
 *
 * @author Martha Czerwik and Artur Hrytsenko
 */
public class CommissionEmployee extends Employee {

    private double rate;
    private double sales;

    /**
     * 3 parameter constructor to create instance of Commission type employees
     * @param rate rate of pay
     * @param sales how many sales
     * @param id employee id
     * @param firstName employee first name
     * @param lastName employee last name
     */
    public CommissionEmployee(double rate, double sales, int id, String firstName, String lastName) {
        super(id, firstName, lastName);
        this.rate = rate;
        this.sales = sales;
    }

    public double getRate() {
        return rate;
    }

    public double getSales() {
        return sales;
    }

    /**
     * Method to calculate weekly pay for commission type employees
     * Overrides calculatePay method in Employee class
     * @return weekly pay of commission employee
     */
    @Override
    public double calculatePay() {
        double pay = ((this.sales) * (this.rate)) / 100;
        return pay;
    }

}
