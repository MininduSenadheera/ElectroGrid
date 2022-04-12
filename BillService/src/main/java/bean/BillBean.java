package bean;

// for JSON
import com.google.gson.*;

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

    public void calculateAmount(int units) {
        double total = 0;

        if (units <= 60) {
            total = units * 7.85;
        } else if (units <= 90) {
            total = (64 * 7.85) + ((units-64) * 10) + 90.00;
        } else if (units <= 120) {
            total = (64 * 7.85) + (32 * 10.00) + ((units-96) * 27.75) + 480.00;
        } else if (units <= 180) {
            total = (64 * 7.85) + (32 * 10.00) + (32 * 27.75) + ((units-128) * 32.00) + 480.00;
        } else {
            total = (64 * 7.85) + (32 * 10.00) + (32 * 27.75) + (64 * 32.00) + ((units-192) * 45.00) + 540.00;
        }

        setAmount(total);
    }

    public void convertStringToJSONInsert(String billData) {
		//convert string to JSON object and assign to variables in the class
		JsonObject billObject  = new JsonParser().parse(billData).getAsJsonObject(); 		
		setConnectionID(billObject.get("connectionID").getAsInt());
		setUnits(billObject.get("units").getAsInt());
	}

    public void convertStringToJSONUpdate(String billData) {
		//convert string to JSON object and assign to variables in the class
		JsonObject billObject  = new JsonParser().parse(billData).getAsJsonObject();
        setConnectionID(billObject.get("billID").getAsInt());
        setPaymentID(billObject.get("paymentID").getAsInt());
	}
}