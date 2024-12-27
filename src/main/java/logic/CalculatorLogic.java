package logic;

import java.util.Stack;

public class CalculatorLogic {

    public String calculate(String input) {
        try {
            // Replace comma with period for decimal numbers before calculation
            input = input.replace(",", ".");

            // Call the custom evaluate function
            double result = evaluate(input);

            // Convert the result to string
            String resultString;

            // Check if the result is a whole number (no fractional part)
            if (result == (long) result) {
                // If it's a whole number, return it as an integer
                resultString = String.format("%d", (long) result);
            } else {
                // If it has a fractional part, return as a decimal number
                resultString = String.valueOf(result);
            }

            // Replace period with comma for decimal numbers
            if (resultString.contains(".")) {
                resultString = resultString.replace(".", ",");
            }

            return resultString;
        } catch (Exception e) {
            return "Error"; // Return "Error" in case of invalid input
        }
    }

    private double evaluate(String expression) {
        // Remove spaces from the expression
        expression = expression.replaceAll(" ", "");

        Stack<Double> values = new Stack<>();
        Stack<Character> operators = new Stack<>();

        int i = 0;
        while (i < expression.length()) {
            char currentChar = expression.charAt(i);

            if (Character.isDigit(currentChar)) {
                // If it's a number, parse the whole number
                StringBuilder number = new StringBuilder();
                while (i < expression.length() && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
                    number.append(expression.charAt(i));
                    i++;
                }
                values.push(Double.parseDouble(number.toString())); // Parse the number as a double
            } else if (currentChar == '+') {
                operators.push(currentChar);
                i++;
            } else if (currentChar == '-') {
                operators.push(currentChar);
                i++;
            } else if (currentChar == '*') {
                operators.push(currentChar);
                i++;
            } else if (currentChar == '/') {
                operators.push(currentChar);
                i++;
            } else {
                throw new IllegalArgumentException("Invalid character in expression: " + currentChar);
            }

            // If we've reached the end or next operator, process the top of the stack
            if (i == expression.length() || isOperator(expression.charAt(i))) {
                while (!operators.isEmpty() && (operators.peek() == '*' || operators.peek() == '/')) {
                    char operator = operators.pop();
                    double b = values.pop();
                    double a = values.pop();

                    if (operator == '*') {
                        values.push(a * b);
                    } else if (operator == '/') {
                        values.push(a / b);
                    }
                }
            }
        }

        // Final computation for addition and subtraction
        while (!operators.isEmpty()) {
            char operator = operators.pop();
            double b = values.pop();
            double a = values.pop();

            if (operator == '+') {
                values.push(a + b);
            } else if (operator == '-') {
                values.push(a - b);
            }
        }

        // The final value in the stack is the result
        return values.pop();
    }

    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }
}
