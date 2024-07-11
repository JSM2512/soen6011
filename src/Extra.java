



































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