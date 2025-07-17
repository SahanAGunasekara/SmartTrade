
package model;


public class Util {
    public static String generateCode(){
        double r = Math.random();
        int x = (int) (r*1000000);
        return String.format("%06d", x);
    }
    
    public static boolean isEmailValid(String email){
        return email.matches("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");
    }
    
    public static boolean isPasswordValid(String password){
       return  password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=]).{8,}$");
    }
    
    public static boolean iscodeValid(String code){
       return code.matches("^\\d{4,6}$");
    }
    
}
