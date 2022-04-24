package bean;

import java.sql.Timestamp;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class PaymentBean {
	
	private int paymentID;
	private int customerID;
	private int billID;
	private Timestamp paymentDateTime;
	private float amount;
    private String type;
    
    
    public int getPaymentID() {
		return paymentID;
	}
	public void setPaymentID(int paymentID) {
		this.paymentID = paymentID;
	}
	public int getCustomerID() {
		return customerID;
	}
	
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	
	public int getBillID() {
		return billID;
	}
	public void setBillID(int billID) {
		this.billID = billID;
	}
	
	public Timestamp getPaymentDateTime() {
		return paymentDateTime;
	}
	public void setPaymentDateTime(Timestamp paymentDateTime) {
		this.paymentDateTime = paymentDateTime;
	}
   
	public double getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	 public String convertObjectToJsonString(PaymentBean payBean) {
	        Gson gson = new Gson();
	        String jsonString = gson.toJson(payBean);
	        return jsonString;
	    }

	  public void convertStringToJSONInsert(String paymentData) {
			//convert string to JSON object and assign to variables in the class
			JsonObject payObject  = new JsonParser().parse(paymentData).getAsJsonObject(); 		
			setCustomerID(payObject.get("customerID").getAsInt());
			setAmount(payObject.get("amount").getAsFloat());
			setType(payObject.get("type").getAsString());
			setBillID(payObject.get("billID").getAsInt());
		}

	 public void convertStringToJSONUpdate(String paymentData) {
		 
		//convert string to JSON object and assign to variables in the class
	 
		 	JsonObject payObject = new JsonParser().parse(paymentData).getAsJsonObject(); 
			setPaymentID(payObject.get("paymentID").getAsInt());
	        setAmount(payObject.get("amount").getAsFloat());
	        setType(payObject.get("type").getAsString());
		}
	

}
