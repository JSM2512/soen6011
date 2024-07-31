import javax.swing.*;

public class Extra{


    // formula for power used is
    // x^y = x * x * x * ... * x (y times)
    // x^y = x * x * x * ... * x (|y| times) if y is negative
    // x^y = x^(n + fractionalPart) = x^n * x^(fractionalPart)
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


    // formula for lnApprox is
    // ln(x) = 2 * [(x - 1) / (x + 1) + (1/3) * ((x - 1) / (x + 1))^3 + (1/5) * ((x - 1) / (x + 1))^5 + ...]
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

    // formula for expApprox is
    // e^x = 1 + x + (x^2 / 2!) + (x^3 / 3!) + (x^4 / 4!) + ...
    private static double expApprox(double x) {
        double sum = 1.0;
        double term = 1.0;

        for (int i = 1; i < 20; i++) {
            term *= x / i;
            sum += term;
        }

        return sum;
    }

    public static double round(double number) {
        int integer = (int) number;
        double decimal = number - integer;
        if (decimal >= 0.5) {
            return (double) integer + 1;
        } else {
            return integer;
        }
    }


    // Implementing Euler's Infinite series Gamma approximation
    // Function Gamma(z) =  prod n = 1..infinity { [(1 + 1/n)^z / (1 + z/n)] } * (1/z)
    // Values greater than 170 will result in infinity
    // if we want to calculate gamma function for values greater than 170, we need to use BigDecimal
    // double range in java is 1.7e-308 to 1.7e+308
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

    public static double gamma(double number) {
        if (round(number) != number) {
            return eulerInfiniteGamma(number);
        }
        if (number == 1) {
            return 1;
        }
        return ((number - 1) * gamma(number - 1));
    }

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



































//import java.math.BigDecimal;
//import java.math.MathContext;
//import java.util.Scanner;
//
//public class Extra {
//    public static double pi = 3.14159265358979323846;
//    public static double e = 2.71828182845904523536;
//    private static final BigDecimal E = new BigDecimal(e);
//
//    // context need to be used for vaery very large values like 100!, 170! etc.
//    private static final MathContext MATH_CONTEXT = new MathContext(50);
//
//    // Implementing Stirling's approximation
//    // Function gamma(n-1) =  sqrt(2*pi*n) * (n/e)^(n-1)
//    public static BigDecimal gammaUsingStirling(double z) {
//        BigDecimal bdZ = new BigDecimal(z);
//        BigDecimal sqrtTerm = new BigDecimal(Math.sqrt(2 * pi * z));
//        BigDecimal powerTerm = bdZ.divide(E, MATH_CONTEXT).pow((int) (z-1));
//        return sqrtTerm.multiply(powerTerm, MATH_CONTEXT);
//    }
//
//    public static BigDecimal gamma(double number){
//        BigDecimal bdNumberMinus1 = new BigDecimal(number-1);
//        if(number == 1){
//            return BigDecimal.valueOf(1);
//        }
//        return bdNumberMinus1.multiply(gamma(number-1));
//    }
//
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.println("Gamma Function Calculator");
//        System.out.println("======================================");
//        System.out.println("Note: The gamma function is not defined for negative numbers.");
//        System.out.println("Enter a number to calculate its gamma function:");
//        System.out.print("Number: ");
//        double number = scanner.nextDouble();
//
//        if(number <= 0) {
//            System.out.println("The gamma function is not defined for negative numbers.");
//            return;
//        }
//
//        if(number < 25){
//            System.out.println("The gamma function of " + number + " is " + gamma(number));
//        } else {
//            System.out.println("The gamma function of " + number + " is " + gammaUsingStirling((int) number));
//        }
//
//    }
//
//
//
//
//}