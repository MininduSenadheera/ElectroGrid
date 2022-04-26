package util;


public class ConnectionValidation {

    // validate status values
    public boolean statusValidation( String status){

        if(status.equals("Established") || status.equals("Requested") || status.equals("Disconnected") ){
            return true;
        }else {
            return false;
        }
    }

    //validate units values
    public boolean unitsValidation(int units){

        if(units >=0){
            return true;
        }else {
            return false;
        }
        
    }

    //validate connection types 
    public boolean typeValidation(String type){
        
        if(type.equals("Domestic") || type.equals("Industrial") || type.equals("Religious") || type.equals("Government") || type.equals("Hotel")){
            return true;
        }else {
            return false;
        }
    }

}
