package ui;

import logic.CalculatorLogic;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class InterfaceListener implements ActionListener {
    private final Interface interfaceUI;
    private StringBuilder currentInput;
    private final CalculatorLogic calculatorLogic;

    public InterfaceListener(Interface interfaceUI) {
        this.interfaceUI = interfaceUI;
        this.currentInput = new StringBuilder();
        this.calculatorLogic = new CalculatorLogic();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("C")) {  // Clear input (reset the display)
            currentInput.setLength(0);  // Clear the StringBuilder
            interfaceUI.updateDisplay(""); // Clear the display
        } else if (command.equals("=")) {  // Handle calculation (perform the calculation)
            processEquals();
        } else {  // Append the button text (numbers and operators) to the current input
            currentInput.append(command);
            interfaceUI.updateDisplay(currentInput.toString());
        }
    }

    public void handleKeyPress(char keyChar) {
        String currentText = interfaceUI.getDisplayText();

        if (keyChar == '=' || keyChar == KeyEvent.VK_ENTER) {
            // Handle '=' or 'Enter' key press
            processEquals();
        } else if (Character.isDigit(keyChar) || "+-*/".indexOf(keyChar) >= 0) {
            // Append numbers or operators to the current input
            currentInput.append(keyChar);
            interfaceUI.updateDisplay(currentText + keyChar);
        } else if (keyChar == 'c' || keyChar == 'C') {
            // Clear the display
            interfaceUI.updateDisplay("");
            currentInput.setLength(0);
        } else if (keyChar == '.' && !currentText.contains(".")) {
            // Prevent adding multiple decimal points
            currentInput.append(".");
            interfaceUI.updateDisplay(currentText + ".");
        }
    }

    private void processEquals() {
        // Use CalculatorLogic to calculate the result
        String input = currentInput.toString();
        String result = calculatorLogic.calculate(input);

        // Display the result
        interfaceUI.updateDisplay(result);
        System.out.println("The result of the calculation is: " + result);

        currentInput.setLength(0);
        currentInput.append(result);
    }
}
