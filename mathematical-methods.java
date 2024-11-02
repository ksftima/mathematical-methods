import java.util.Scanner;

public class Main {
    // Creation of scanner object.
    private static Scanner userInputScanner = new Scanner(System.in);

    // Constants
    static final int QUIT = -1;

    /**
     * This method should be used only for unit testing on CodeGrade. Do not change this method!
     * Do not remove this method! Swaps userInputScanner with a custom scanner object bound to a test input stream
     *
     * @param inputScanner - test scanner object
     */
    public static void injectInput(final Scanner inputScanner) {
        userInputScanner = inputScanner;
    }

    public static void main(final String[] args) {
        int radius = 0;
        int height = 0;
        int numerator = 0;
        int denominator = 0;

        // Print the header of the program for area and volume.
        System.out.println("----------------------------------");
        System.out.println("# Test of area and volume methods");
        System.out.println("----------------------------------");

        // While loop that runs until user enters "q" for area and volume.
        while (true) {
            radius = input();
            if (radius == QUIT) {
                break;
            }

            height = input();
            if (height == QUIT) {
                break;
            }

            System.out.println("r = " + radius + ", h = " + height);
            System.out.printf("Circle area: %.2f %n", area(radius));
            System.out.printf("Cone area: %.2f %n", area(radius, height));
            System.out.printf("Cone volume: %.2f %n", volume(radius, height));
        }

        // Print the header of the program for area and volume.
        System.out.println("----------------------------------");
        System.out.println("# Test of the fractional methods");
        System.out.println("----------------------------------");

        // While loop that runs until user enters "q" for the fraction part
        while (true) {
            numerator = input();
            if (numerator == QUIT) {
                break;
            }

            denominator = input();
            if (denominator == QUIT) {
                break;
            }

            System.out.printf("%d/%d = ", numerator, denominator);
            printFraction(fraction(numerator, denominator));
        }
    }
/**
 * Retrieves user input and returns either a positive integer or -1 if 'q' is entered.
 *
 * @return a positive integer entered by the user or -1 if 'q' is entered.
 */
    public static int input() {
        String input = userInputScanner.next();
        try {
            int value = Integer.parseInt(input);
            return Math.abs(value); //takes the absolute value of the integer
        } catch (NumberFormatException e) {
            if (input.equalsIgnoreCase("q")) {
                return QUIT;
            } else {
                return input();
            }
        }
    }


/**
 * Calculates the area of a circle.
 * @param radius the radius of the circle
 * @return the area of the circle
 */
    public static double area(final int radius) {
        double area = Math.PI * Math.pow(radius, 2);
        return area;
    }
/**
 * Calculates the hypotenuse of a triangle using Pythagoras' theorem.
 * @param sideA the length of one side of the right angle triangle
 * @param sideB the length of the other side of the right angle triangle
 * @return the length of the hypotenuse
 */
    public static double pythagoras(final int sideA, final int sideB) {
        return Math.sqrt(Math.pow(sideA, 2) + Math.pow(sideB, 2));
    }
/**
 * Calculates the area of a cone's lateral surface.
 * @param radius the radius of the cone's base
 * @param height the height of the cone
 * @return the area of the cone's lateral surface
 */
    public static double area(final int radius, final int height) {
        double s = pythagoras(radius, height);
        return Math.PI * radius * s;
    }
/**
 * Calculates the volume of a cone.
 * @param radius the radius of the cone's base
 * @param height the height of the cone
 * @return the volume of the cone
 */
    public static double volume(final int radius, final int height) {
        return ((1.0 / 3) * Math.PI * Math.pow(radius, 2) * height);
    }
/**
 * Converts a fraction represented by a numerator and a denominator into a mixed fraction.
 * @param numerator the numerator of the fraction
 * @param denominator the denominator of the fraction
 * @return an array representing the mixed fraction, where index 0 is the integer part, index 1 is the numerator of the fractional part, and index 2 is the denominator
 */
    public static int[] fraction(final int numerator, final int denominator) {
        int[] array = new int[3];
        array[0] = 0;
        array[1] = 0;
        array[2] = 0;

        if (denominator == 0) {
            return null;
        } else if (numerator == 0) {
            return array;
        }

        int positiveNumer = Math.abs(numerator);
        int positiveDenom = Math.abs(denominator);
        int gcdValue = gcd(positiveNumer, positiveDenom);

        int integerPart = positiveNumer / positiveDenom;
        int newNumer = positiveNumer % positiveDenom;

        newNumer = newNumer / gcdValue;
        positiveDenom = positiveDenom / gcdValue;

        array[0] = integerPart;
        array[1] = newNumer;
        array[2] = positiveDenom;

        return array;
    }

    /**
     * This method calculates GCD using Euclid's algorithm as follows:
     * 1. Make sure that a > b (change the place if necessary)
     * 2. As long as b is not 0
     *    2.1 c = a % b
     *    2.2 a = b
     *    2.3 b = c
     * 3. Return a
     *
     * @param a first integer a
     * @param b second integer b
     * @return GCD of a and b
     */
    public static int gcd(final int a, final int b) {
        int tempA = a;
        int tempB = b;
        int tempC = 0;

        // 1. Make sure that a > b (change the place if necessary)
        if (a <= b) {
            int temp = tempA;
            tempA = tempB;
            tempB = temp;
        }

        while (tempB != 0) {
            tempC = tempA % tempB;
            tempA = tempB;
            tempB = tempC;
        }

        return tempA;
    }

/**
 * Prints a mixed fraction represented by an integer array.
 *  @param parts an integer array representing a mixed fraction, where index 0 is the integer part, index 1 is the numerator of the fractional part, and index 2 is the denominator
 */
    public static void printFraction(final int[] parts) {
        if (parts == null) {
            System.out.println("Error");
            return;
        }

        if (parts[0] != 0) {
            System.out.print(parts[0]);
            if (parts[1] != 0) {
                System.out.printf(" %d/%d", parts[1], parts[2]);
            }
        } else if (parts[1] != 0) {
            System.out.printf("%d/%d", parts[1], parts[2]);
        } else {
            System.out.println(0);
        }
    }
}