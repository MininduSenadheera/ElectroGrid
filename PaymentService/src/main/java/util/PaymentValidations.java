package util;

public class PaymentValidations {
	
	//validating when inserting
    public boolean insertValidation(int customerID, double amount){
        if (customerID <= 0 || amount <= 0) {
            return false;
        } else {
            return true;
        }
    }

    //validating while updating 
    public boolean updateValidation( int paymentID) {
        if ( paymentID <= 0) {
            return false;
        } else {
            return true;
        }
    }

    //validating while deleting
    public boolean deleteValidation(int paymentID) {
        if (paymentID <= 0) {
            return false;
        } else {
            return true;
        }
    }
}
