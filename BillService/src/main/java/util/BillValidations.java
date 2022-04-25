package util;

public class BillValidations {
    
    public boolean insertValidation(int billID, int units){
        if (billID <= 0 || units <= 0) {
            return false;
        } else {
            return true;
        }
    }

    public boolean updateValidation(int billID, int paymentID) {
        if (billID <= 0 || paymentID <= 0) {
            return false;
        } else {
            return true;
        }
    }

    public boolean deleteValidation(int billID) {
        if (billID <= 0) {
            return false;
        } else {
            return true;
        }
    }
}
