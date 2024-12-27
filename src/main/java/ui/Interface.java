package ui;

import config.AppSettings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Interface {

    private JFrame frame;
    private JTextField display;
    private boolean isScientificMode = false; // To toggle between simple and scientific modes

    public Interface() {
        initialize();
    }

    private void initialize() {

        // ----- Listener for button clicks
        InterfaceListener listener = new InterfaceListener(this);

        // ----- Main frame setup
        frame = new JFrame(AppSettings.WINDOW_TITLE);
        frame.setSize(AppSettings.WINDOW_WIDTH, AppSettings.WINDOW_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // ----- Display setup and style (where the numbers and operations are displayed)
        display = new JTextField();
        display.setEditable(false);
        display.setFont(new Font(AppSettings.BUTTON_FONT_NAME, Font.BOLD, 40));  // Larger font
        display.setForeground(AppSettings.DISPLAY_TEXT_COLOR);  // Text color
        display.setBackground(AppSettings.DISPLAY_BG_COLOR);  // Background color
        display.setHorizontalAlignment(SwingConstants.RIGHT);

        display.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(AppSettings.STYLE_COLOR, 3),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        frame.add(display, BorderLayout.NORTH);

        // ----- Button panel setup (where the numbers and operations buttons are placed)
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(AppSettings.GRID_ROWS, AppSettings.GRID_COLS, AppSettings.GRID_GAP, AppSettings.GRID_GAP));  // Adjusted to 5 rows and 4 columns for 20 grid spaces

        // --- Buttons placement and styling
        for (String text : AppSettings.BUTTON_TEXTS) {
            if (text == null) { // Empty panel for empty buttons :D
                JPanel skip = new JPanel();
                skip.setBackground(AppSettings.DISPLAY_BG_COLOR);
                buttonPanel.add(skip);
            } else {
                JButton button = new JButton(text);
                button.setFont(new Font(AppSettings.BUTTON_FONT_NAME, Font.BOLD, AppSettings.BUTTON_FONT_SIZE));

                // Assign colors based on button type (numbers/operations)
                if (text.matches("[0-9.,]")) {
                    button.setBackground(AppSettings.NUMBERSBUTTON_COLOR);
                    button.setForeground(AppSettings.DISPLAY_TEXT_COLOR);
                } else { // Operations and other buttons
                    button.setBackground(AppSettings.OPERATIONSBUTTON_COLOR);
                    button.setForeground(AppSettings.DISPLAY_TEXT_COLOR);
                }

                button.setBorder(BorderFactory.createLineBorder(AppSettings.STYLE_COLOR, 2));

                // Add hover effects
                button.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        button.setBackground(AppSettings.HOVER_COLOR); // Hover effect
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        if (text.matches("[0-9.,]")) {
                            button.setBackground(AppSettings.NUMBERSBUTTON_COLOR);  // Reset to number button color
                        } else {
                            button.setBackground(AppSettings.OPERATIONSBUTTON_COLOR); // Reset to operation button color
                        }
                    }
                });
                button.addActionListener(listener);
                buttonPanel.add(button);
            }
        }

        buttonPanel.setBackground(AppSettings.DISPLAY_BG_COLOR);
        frame.add(buttonPanel, BorderLayout.CENTER);

        // ----- SCI mode switch button
        JButton switchModeButton = initSCIButton();
        frame.add(switchModeButton, BorderLayout.SOUTH);  // Position at the bottom

        // ----- Key bindings setup
        setupKeyBindings(listener);
        frame.setFocusable(true);
        frame.requestFocus();
    }

    private JButton initSCIButton() {
        // Init the last button of the UI : the SCI mode switch button
        // The same style as the other UI button will be applied, it is just treated differently for UI style
        JButton switchModeButton = new JButton("SCI");

        switchModeButton.setFont(new Font(AppSettings.BUTTON_FONT_NAME, Font.BOLD, AppSettings.BUTTON_FONT_SIZE));
        switchModeButton.setBackground(AppSettings.OPERATIONSBUTTON_COLOR);
        switchModeButton.setForeground(AppSettings.DISPLAY_TEXT_COLOR);
        switchModeButton.addActionListener(_ -> toggleScientificMode());
        switchModeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                switchModeButton.setBackground(AppSettings.HOVER_COLOR); // Hover effect
            }

            @Override
            public void mouseExited(MouseEvent e) {
                switchModeButton.setBackground(AppSettings.OPERATIONSBUTTON_COLOR); // Reset to operation button color
            }
        });
        return switchModeButton;
    }


    public void updateDisplay(String text) {
        // Method to update the current display text
        display.setText(text);
    }

    public String getDisplayText() {
        // Method to get the current display text
        return display.getText();
    }


    private void toggleScientificMode() {
        // Method to handle toggle between simple and scientific mode
        isScientificMode = !isScientificMode;
        System.out.println("Scientific Mode: " + (isScientificMode ? "ON" : "OFF"));
        // TODO : modify the UI or buttons based on the mode here
    }

    private void setupKeyBindings(InterfaceListener listener) {
        // Setup key bindings for the keyboard (match 1-9, 0, +, -, *, /, =, C, c, .)
        JComponent rootPane = frame.getRootPane();

        String[] keys = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "+", "-", "*", "/", "=", "C", "c", ","};

        for (String key : keys) {
            KeyStroke keyStroke = KeyStroke.getKeyStroke(key.charAt(0));
            rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStroke, key);
            rootPane.getActionMap().put(key, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    listener.handleKeyPress(key.charAt(0));
                }
            });
        }
    }


    public void showScientificButtons(boolean show) {
        // Function to show or hide scientific buttons based on the mode

        // You can dynamically add/remove scientific buttons here when mode changes
        // For example, add buttons like sin, cos, tan, etc. when in SCI mode
    }

    public void show() {
        // Show the UI
        frame.setVisible(true);
    }

}
