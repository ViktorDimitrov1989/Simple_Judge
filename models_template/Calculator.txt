package models;
import annotations.TestableMethod;

public class Calculator {

    public Calculator(){
    }

    @TestableMethod(name = "calculate")
    public int calculate(int operand1,int operand2, String operationSymbol){
        switch (operationSymbol){
            case "+":
                return operand1 + operand2;
            case "-":
                return operand1 - operand2;
            case "*":
                return operand1 * operand2;
            case "/":
                return operand1 / operand2;
            default:
                return 0;
        }
    }

}