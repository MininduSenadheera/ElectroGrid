package bean;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class ConnectionBean {
	private int connectionID;
	private String status;
	private String connectionType;
	private int units;
	private int customerID;
	private String firstName;
	private String lastName;
	
	public int getConnectionID() {
		return connectionID;
	}
	public void setConnectionID(int connectionID) {
		this.connectionID = connectionID;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getConnectionType() {
		return connectionType;
	}
	public void setConnectionType(String connectionType) {
		this.connectionType = connectionType;
	}
	public int getUnits() {
		return units;
	}
	public void setUnits(int units) {
		this.units = units;
	}
	public int getCustomerID() {
		return customerID;
	}
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void convertStringToJSONInsert(String connectionData) {
		//convert string to JSON object and assign to variables in the class
		JsonObject connectionObject = new JsonParser().parse(connectionData).getAsJsonObject();
		setCustomerID(connectionObject.get("customerID").getAsInt());
		setStatus(connectionObject.get("status").getAsString());
		setUnits(connectionObject.get("units").getAsInt());
		setConnectionType(connectionObject.get("connectionType").getAsString());
	}
	
	public void convertStringToJSONUpdateUnits(String connectionData) {
		//convert string to JSON object and assign to variables in the class
		JsonObject connectionObject  = new JsonParser().parse(connectionData).getAsJsonObject();
        setConnectionID(connectionObject.get("connectionID").getAsInt());
       	
	   	setUnits(connectionObject.get("units").getAsInt());
	}

	public void convertStringToJSONUpdateStatus(String connectionData) {
		//convert string to JSON object and assign to variables in the class
		JsonObject connectionObject  = new JsonParser().parse(connectionData).getAsJsonObject();
        setConnectionID(connectionObject.get("connectionID").getAsInt());
       	setStatus(connectionObject.get("status").getAsString());
	   	
	}

	public void convertStringToJSONUnits(String connectionData){
		//convert String to JSON object and assign to variables in the class
		JsonObject connectionObject  = new JsonParser().parse(connectionData).getAsJsonObject();

		setUnits(connectionObject.get("units").getAsInt());
		setConnectionID(connectionObject.get("connectionID").getAsInt());
		
	}	
}
