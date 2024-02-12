package emailcheck;
import java.util.regex.*;
import java.util.Scanner;

public class emailCheck {
    
    public static boolean checkEmail(String email) {
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter email:");
        String input = sc.next();
        boolean result = checkEmail(input);
        
        if(result) {
            System.out.println("It is an Email");
        } else {
            System.out.println("It is not an email");
        }
        
    }
}
