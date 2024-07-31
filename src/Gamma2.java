import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;

public class Gamma2{


    /**
     * Power function to calculate the power of a number by applying the formula x^y = x * x * x * ... * x (y times)
     * for negative exponent x^y = x * x * x * ... * x (|y| times)
     * for fractional part x^y = x^(n + fractionalPart) = x^n * x^(fractionalPart)
     * @param base
     * @param exponent
     * @return
     */
    public static double power(double base, double exponent) {
        double result = 1.0;
        int n = (int) exponent;
        double fractionalPart = exponent - n;

        for (int i = 0; i < absolute(n); i++) {
            result *= base;
        }

        if (n < 0) {
            result = 1 / result;
        }

        if (fractionalPart != 0) {
            result *= expApprox(fractionalPart * lnApprox(base));
        }

        return result;
    }

    private static int absolute(int n) {
        return n < 0 ? -n : n;
    }


    /**
     * lnApprox function to calculate the natural logarithm of a number by applying the formula :
     * ln(x) = 2 * [(x - 1) / (x + 1) + (1/3) * ((x - 1) / (x + 1))^3 + (1/5) * ((x - 1) / (x + 1))^5 + ...]
     * @param x
     * @return natural logarithm of x
     */
    private static double lnApprox(double x) {
        if (x <= 0) throw new IllegalArgumentException("lnApprox not defined for non-positive values.");
        double sum = 0.0;
        double term = (x - 1) / (x + 1);
        double square = term * term;
        double denominator = 1.0;

        for (int i = 0; i < 10; i++) {
            sum += term / denominator;
            term *= square;
            denominator += 2.0;
        }

        return 2 * sum;
    }

    /**
     * expApprox function to calculate the exponential of a number by applying the formula :
     * e^x = 1 + x + (x^2 / 2!) + (x^3 / 3!) + (x^4 / 4!) + ...
     * @param x
     * @return exponential value of x
     */
    private static double expApprox(double x) {
        double sum = 1.0;
        double term = 1.0;

        for (int i = 1; i < 20; i++) {
            term *= x / i;
            sum += term;
        }

        return sum;
    }


    /**
     * round function to round off the number to the nearest integer
     * @param number
     * @return rounded off number
     */
    public static double round(double number) {
        int integer = (int) number;
        double decimal = number - integer;
        if (decimal >= 0.5) {
            return (double) integer + 1;
        } else {
            return integer;
        }
    }


    /**
     * eulerInfiniteGamma function to calculate the gamma function for values greater than 170
     * formula : gamma(z) = prod n = 1..infinity { [(1 + 1/n)^z / (1 + z/n)] } * (1/z)
     * @param z
     * @return product obtained by applying Euler's infinite product formula
     */
    private static double eulerInfiniteGamma(double z) {
        double product = 1;
        for (int n = 1; n <= 80000; n++) {
            double numerator = power((1 + (1.0 / n)), z);
            double denominator = (1 + (z / n));
            double value = numerator / denominator;
            product = product * value;
        }
        return product / z;
    }

    /**
     * gamma function to calculate the gamma function for a given number
     * formula : gamma(z) = (z-1) * gamma(z-1)
     * @param number
     * @return gamma value of the number
     */
    public static double gamma(double number) {
        if (round(number) != number) {
            return eulerInfiniteGamma(number);
        }
        if (number == 1) {
            return 1;
        }
        return ((number - 1) * gamma(number - 1));
    }

    /**
     * main function to create the GUI for the gamma function calculator using Swing
     * @param args
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            // create a frame for the application window
            JFrame frame = new JFrame("Gamma Function Calculator");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 300);
            frame.setLocationRelativeTo(null); // Center the frame

            // create a panel to hold the components
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 14, 10));

            // COMPONENT-1: JLabel for the prompt
            JLabel promptLabel = new JLabel("Enter a number to calculate its gamma function:");
            promptLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);

            // COMPONENT-2: JTextField for user input
            JTextField inputField = new JTextField();
            inputField.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
            inputField.setAlignmentX(JTextField.CENTER_ALIGNMENT);


            // COMPONENT-3: JLabel to display the result
            JLabel resultLabel = new JLabel();
            resultLabel.setText("Result will be displayed here : ");
            resultLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
            resultLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));


            // COMPONENT-4: JButton to calculate the gamma function
            JButton calculateButton = new JButton("Calculate");
            calculateButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
            calculateButton.setHorizontalTextPosition(SwingConstants.CENTER);
            calculateButton.setVerticalTextPosition(SwingConstants.CENTER);

            // COMPONENT-5: JButton to exit the application
            JButton exitButton = new JButton("Close");
            exitButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
            exitButton.setHorizontalTextPosition(SwingConstants.CENTER);
            exitButton.setVerticalTextPosition(SwingConstants.CENTER);

            calculateButton.addActionListener(e -> {
                try {
                    double number = Double.parseDouble(inputField.getText());
                    if (number <= 0) {
                        if (round(number) == number) {
                            resultLabel.setText("The gamma function is not defined for negative integers or 0.");
                        } else {
                            resultLabel.setText("Gamma Value " + number + " is " + eulerInfiniteGamma(number));
                        }
                    } else if (number < 25) {
                        resultLabel.setText("Gamma Value of " + number + " is " + gamma(number));
                    } else {
                        resultLabel.setText("Gamma Value of " + number + " is " + eulerInfiniteGamma(number));
                    }
                } catch (NumberFormatException ex) {
                    resultLabel.setText("Invalid input. Please enter a valid number.");
                } catch (Exception ex) {
                    resultLabel.setText("An error occurred: " + ex.getMessage());
                }
            });

            exitButton.addActionListener(e -> {
                frame.dispose();
            });

            // add all components 1,2,3,4 to the panel
            panel.add(promptLabel);
            panel.add(inputField);
            panel.add(resultLabel);
            panel.add(calculateButton);
            panel.add(exitButton);

            frame.add(panel);
            frame.setVisible(true);
        });
    }
}