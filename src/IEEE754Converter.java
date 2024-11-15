import java.util.Scanner;

public class IEEE754Converter {

    public static String floatToIEEE754(double value, String precision) {
        StringBuilder binaryRepresentation = new StringBuilder();
        String sign, exponent, mantissa;

        if ("single".equalsIgnoreCase(precision)) {
            int bits = Float.floatToIntBits((float) value);
            binaryRepresentation.append(String.format("%32s", Integer.toBinaryString(bits)).replace(' ', '0'));

            sign = binaryRepresentation.substring(0, 1);
            exponent = binaryRepresentation.substring(1, 9);
            mantissa = binaryRepresentation.substring(9);

        } else if ("double".equalsIgnoreCase(precision)) {
            long bits = Double.doubleToLongBits(value);
            binaryRepresentation.append(String.format("%64s", Long.toBinaryString(bits)).replace(' ', '0'));

            sign = binaryRepresentation.substring(0, 1);
            exponent = binaryRepresentation.substring(1, 12);
            mantissa = binaryRepresentation.substring(12);

        } else {
            throw new IllegalArgumentException("Precision must be 'single' or 'double'.");
        }

        System.out.println("Binary Representation: " + binaryRepresentation);
        System.out.println("Sign: " + sign + ", Exponent: " + exponent + ", Mantissa: " + mantissa);

        return binaryRepresentation.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a float/double value: ");
        double value = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter precision (single/double): ");
        String precision = scanner.nextLine().trim().toLowerCase();

        System.out.println("IEEE 754 representation (" + precision + " precision):");
        try {
            floatToIEEE754(value, precision);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        scanner.close();
    }
}
