package util;

public class PaymentValidations {
	
	//validating when inserting
    public boolean dataValidation(int customerID, double amount){
        if (customerID <= 0 || amount <= 0) {
            return false;
        } else {
            return true;
        }
    }

    //validating while updating 
    public boolean checkID(int paymentID) {
        if ( paymentID <= 0) {
            return false;
        } else {
            return true;
        }
    }

  
}
