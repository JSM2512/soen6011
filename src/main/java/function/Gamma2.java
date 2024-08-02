package function;

import java.awt.Component;
import java.util.logging.Logger;
import javax.accessibility.AccessibleContext;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;



/**
 * Gamma2 class to calculate the gamma function of a number using Euler's infinite product formula.
 * Also display the result using a Swing GUI.
 */
public class Gamma2 {

  /**
   * Power function to calculate the power of a number.
   * formula x^y = x * x * x * ... * x (y times).
   * for negative exponent x^y = x * x * x * ... * x (|y| times).
   * for fractional part x^y = x^(n + fractionalPart) = x^n * x^(fractionalPart).
   *
   * @param base number to be raised to the power
   * @param exponent power to which the base is raised
   * @return power of the number
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

  /**
   * Absolute function to calculate the absolute value of a number.
   */
  private static int absolute(int n) {
    return n < 0 ? -n : n;
  }


  /**
   * Calculates an approximate value of the natural logarithm of a given number using the formula:
   * ln(x) = 2 * [(x - 1) / (x + 1) + (1/3) * ((x - 1) / (x + 1))^3 + ...].
   *
   * <p>The method uses a series expansion to provide an approximation.</p>
   *
   * @param x The number for which the natural logarithm is to be calculated.
   * @return The approximate natural logarithm value of x.
   * @throws IllegalArgumentException if x is non-positive.
   */
  private static double lnApprox(double x) {
    if (x <= 0) {
      throw new IllegalArgumentException("lnApprox not defined for non-positive values.");
    }
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
   * Calculates an approximate value of the exponential function e^x for a given number
   * using the series expansion formula:
   * e^x = 1 + x + (x^2 / 2!) + (x^3 / 3!) + (x^4 / 4!) + ...
   *
   * <p>The method uses a series expansion to provide an approximation.</p>
   *
   * @param x The number for which the exponential value is to be calculated.
   * @return The approximate exponential value of x.
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
   * Rounds a given number to the nearest integer.
   *
   * <p>If the decimal part of the number is 0.5 or greater, the number is rounded up.
   * Otherwise, it is rounded down.</p>
   *
   * @param number The number to be rounded off.
   * @return The rounded off integer value.
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
   * Calculates the gamma function for a given value using Euler's infinite product formula.
   *
   * <p>This method is particularly useful for values greater than 170 where typical gamma
   * function calculations may result in overflow.</p>
   *
   * <p>Formula: gamma(z) = prod n = 1..infinity { [(1 + 1/n)^z / (1 + z/n)] } * (1/z)</p>
   *
   * @param z The input value for which the gamma function is to be calculated.
   * @return The product obtained by applying Euler's infinite product formula.
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
   * Calculates the gamma function for a given number using the recursive relation:
   * gamma(z) = (z - 1) * gamma(z - 1).
   *
   * <p>This method uses recursion to compute the gamma function for integer values.
   * For non-integer values, it may use the Euler's infinite product approximation.</p>
   *
   * @param number The input value for which the gamma function is to be calculated.
   * @return The gamma function value for the given number.
   */

  public static double gamma(double number) {
    if (round(number) != number || number >= 25) {
      return eulerInfiniteGamma(number);
    }
    if (number == 1) {
      return 1;
    }
    return ((number - 1) * gamma(number - 1));
  }

  /**
   * Inspects the accessibility properties of a Swing component
   * and logs the accessible name and description.
   *
   * @param component The Swing component to be inspected.
   */
  public static void inspectAccessibility(Component component) {
    AccessibleContext ac = component.getAccessibleContext();
    Logger logger = Logger.getLogger(Gamma2.class.getName());
    if (ac != null) {
      logger.info("Accessible Name: " + ac.getAccessibleName());
      logger.info("Accessible Description: " + ac.getAccessibleDescription());
      logger.info("");
    } else {
      logger.info("AccessibleContext is null");
      logger.info("");
    }
  }

  /**
   * Entry point for the application that initializes
   * and displays the GUI for the gamma function calculator.
   *
   * <p>This method sets up the Swing components,
   * including the frame, panel, labels, text fields, and buttons,
   * and configures their layout and event handling.
   * It provides a user interface for inputting a number and calculating its gamma function.</p>
   *
   * @param args Command-line arguments (not used).
   */

  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {

      // create a frame for the application window
      JFrame frame = new JFrame("Gamma Function Calculator");
      frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      frame.setSize(500, 300);
      frame.setLocationRelativeTo(null); // Center the frame

      // create a panel to hold the components
      JPanel panel = new JPanel();
      panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
      panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 14, 10));

      // COMPONENT-1: JLabel for the prompt
      JLabel promptLabel = new JLabel("Enter a number to calculate its gamma function:");
      promptLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
      promptLabel.getAccessibleContext().setAccessibleDescription("Prompt for "
              + "user to enter a number");

      // COMPONENT-2: JTextField for user input
      JTextField inputField = new JTextField();
      inputField.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
      inputField.setAlignmentX(Component.CENTER_ALIGNMENT);
      inputField.getAccessibleContext().setAccessibleName("Input Field"); 
      inputField.getAccessibleContext().setAccessibleDescription("Field for "
              + "entering a number for gamma function");

      // COMPONENT-3: JLabel to display the result
      JLabel resultLabel = new JLabel();
      resultLabel.setText("Result will be displayed here : ");
      resultLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
      resultLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
      resultLabel.getAccessibleContext().setAccessibleName("Result Label"); 
      resultLabel.getAccessibleContext().setAccessibleDescription("Displays the "
              + "result of the gamma function calculation");


      // COMPONENT-4: JButton to calculate the gamma function
      JButton calculateButton = new JButton("Calculate");
      calculateButton.setAlignmentX(Component.CENTER_ALIGNMENT);
      calculateButton.setHorizontalTextPosition(SwingConstants.CENTER);
      calculateButton.setVerticalTextPosition(SwingConstants.CENTER);
      calculateButton.getAccessibleContext().setAccessibleName("Calculate Button"); 
      calculateButton.getAccessibleContext().setAccessibleDescription("Button to "
              + "calculate the gamma function");

      // COMPONENT-5: JButton to exit the application
      JButton exitButton = new JButton("Close");
      exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
      exitButton.setHorizontalTextPosition(SwingConstants.CENTER);
      exitButton.setVerticalTextPosition(SwingConstants.CENTER);
      exitButton.getAccessibleContext().setAccessibleName("Close Button"); 
      exitButton.getAccessibleContext().setAccessibleDescription("Button to "
              + "close the application");

      calculateButton.addActionListener(e -> {
        try {
          double number = Double.parseDouble(inputField.getText());
          if (number <= 0) {
            if (round(number) == number) {
              resultLabel.setText("The gamma function is not defined for negative integers or 0.");
            } else {
              resultLabel.setText("Gamma Value " + number + " is " + eulerInfiniteGamma(number));
            }
          } else {
            double result = gamma(number);
            resultLabel.setText("Gamma Value of " + number + " is " + result);
          }
        } catch (NumberFormatException ex) {
          resultLabel.setText("Invalid input. Please enter a valid number.");
        } catch (Exception ex) {
          resultLabel.setText("An error occurred: " + ex.getMessage());
        }
      });

      exitButton.addActionListener(e ->
              frame.dispose());

      // add all components 1,2,3,4 to the panel
      panel.add(promptLabel);
      panel.add(inputField);
      panel.add(resultLabel);
      panel.add(calculateButton);
      panel.add(exitButton);

      frame.add(panel);
      frame.setVisible(true);

      // Inspect accessibility
      inspectAccessibility(promptLabel);
      inspectAccessibility(inputField);
      inspectAccessibility(resultLabel);
      inspectAccessibility(calculateButton);
      inspectAccessibility(exitButton);

    });
  }
}