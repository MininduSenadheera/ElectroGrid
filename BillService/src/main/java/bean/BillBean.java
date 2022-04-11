package bean;

public class BillBean {
    private int billID;
    private int connectionID;
    private int customerID;
    private String customerName;
    private int paymentID;
    private String issuedDate;
    private String dueDate;
    private String status;
    private int units;
    private double amount;

    public int getBillID() {
        return billID;
    }
    public void setBillID(int billID) {
        this.billID = billID;
    }
    public int getConnectionID() {
        return connectionID;
    }
    public void setConnectionID(int connectionID) {
        this.connectionID = connectionID;
    }
    public int getCustomerID() {
        return customerID;
    }
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public int getPaymentID() {
        return paymentID;
    }
    public void setPaymentID(int paymentID) {
        this.paymentID = paymentID;
    }
    public String getIssuedDate() {
        return issuedDate;
    }
    public void setIssuedDate(String issuedDate) {
        this.issuedDate = issuedDate;
    }
    public String getDueDate() {
        return dueDate;
    }
    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public int getUnits() {
        return units;
    }
    public void setUnits(int units) {
        this.units = units;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
}
