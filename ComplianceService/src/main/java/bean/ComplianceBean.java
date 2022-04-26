package bean;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ComplianceBean {
 private int complainID;
 private int customerID;
 private String content;
 private String heading;
 private String specification;
 
    
    //complaintID uniquely identifies complaints
  
 public int getComplainID() {
	return complainID;
}

public void setComplainID(int complainID) {
	this.complainID = complainID;
}

public int getCustomerID() {
	return customerID;
}

public void setCustomerID(int customerID) {
	this.customerID = customerID;
}

public String getContent() {
	return content;
}

public void setContent(String content) {
	this.content = content;
}

public String getHeading() {
	return heading;
}

public void setHeading(String heading) {
	this.heading = heading;
}

public String getSpecification() {
	return specification;
}

public void setSpecification(String specification) {
	this.specification = specification;
}

public void convertStringToJSONInsert(String complainData) {
	//convert string to JSON object and assign to variables in the class
	JsonObject complainObject = new JsonParser().parse(complainData).getAsJsonObject();
	setCustomerID(complainObject.get("customerID").getAsInt());
	setContent(complainObject.get("content").getAsString());
	setHeading(complainObject.get("heading").getAsString());
	setSpecification(complainObject.get("specification").getAsString());
}
public void convertStringToJSONUpdate(String complainData) {
	 JsonObject complainObject = new JsonParser().parse(complainData).getAsJsonObject();
	 setContent(complainObject.get("content").getAsString());
	 setComplainID(complainObject.get("complainID").getAsInt());
 }
}