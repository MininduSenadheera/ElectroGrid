package util;

public class BillValidations {
    
    //validating when inserting
    public boolean insertValidation(int connectionID, int units){
        if (connectionID <= 0 || units <= 0) {
            return false;
        } else {
            return true;
        }
    }

    //validating while updating 
    public boolean updateValidation(int billID, int paymentID) {
        if (billID <= 0 || paymentID <= 0) {
            return false;
        } else {
            return true;
        }
    }

    //validating while deleting
    public boolean deleteValidation(int billID) {
        if (billID <= 0) {
            return false;
        } else {
            return true;
        }
    }
}
