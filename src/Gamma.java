import java.util.Scanner;
public class Gamma {

    // Implementing Euler's Infinite series Gamma approximation
    // Function Gamma(z) =  prod n = 1..infinity { [(1 + 1/n)^z / (1 + z/n)] } * (1/z)
    // Values greater than 170 will result in infinity
    // if we want to calculate gamma function for values greater than 170, we need to use BigDecimal
    // double range in java is 1.7e-308 to 1.7e+308

    private static double eulerInfiniteGamma(double z) {
        double product = 1;
        for(int n = 1; n <= 80000; n++){
            double numerator = Math.pow((1 + (1.0/n)), z);
            double denominator = (1 + (z/n));
            double value = numerator/denominator;
            product = product * value;
        }
        return product/z;
    }

    public static double gamma(double number){
        if(Math.round(number) != number){
            return eulerInfiniteGamma(number);
        }
        if(number == 1){
            return 1;
        }
        return ((number-1) * gamma(number-1));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Gamma Function Calculator");
            System.out.println("======================================");
            System.out.println("Note: The gamma function is not defined for negative numbers or 0.");
            System.out.println("Enter a number to calculate its gamma function:");
            System.out.print("Number: ");
            double number;
            try {
                number = Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                continue;
            }

            if (number <= 0) {
                if (Math.round(number) == number) {
                    System.out.println("The gamma function is not defined for negative integers or 0.");
                } else {
                    System.out.println("The gamma function by Euler for negative number " + number + " is " + eulerInfiniteGamma(number));
                }
            }

            if (number < 25 && number > 0) {
                System.out.println("The gamma function of " + number + " is " + gamma(number));
            }
            if (number >= 25) {
                System.out.println("The gamma function by Euler for " + number + " is " + eulerInfiniteGamma(number));
            }
            System.out.println();
            System.out.println( "Do you want to continue? (yes/no)");
            String choice = scanner.nextLine();
            if(choice.equals("no")){
                break;
            }
            System.out.println();
            System.out.println();

        }
    }




}