package calc;

import java.util.Scanner;

public class calc {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the first number: ");
        double num1 = scanner.nextDouble();

        System.out.print("Enter the second number: ");
        double num2 = scanner.nextDouble();

        System.out.println("Enter the operation (+, -, *, /) ");
        char operator = scanner.next().charAt(0);

        double result=0;

        switch (operator) {
            case '+':
                result=num1+num2;
                break;
            case '-':
                result=num1-num2;
                break;
            case '*':
                result=num1*num2;
                break;
            case '/':
                result=num1/num2;
                if(num1==0 || num2==0) {
                	System.out.println("Divide by zero");
                }
                break;
            default:
                System.out.println("Invalid");
                
        }

        System.out.println("Result: " + num1 + " " + operator + " " + num2 + " = " + result);
    }
}
