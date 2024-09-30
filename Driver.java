import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Driver {

    public static void main(String[] args) throws IOException{
        // 6 - 2x1 + 5x3
        double[] coeffs1 = {6, -2, 5};
        int[] exps1 = {0, 1, 3};
        Polynomial p1 = new Polynomial(coeffs1, exps1);

        System.out.println("Polynomial p1: " + p1);

        // 1 + 3x1 - 4x2
        double[] coeffs2 = {1, 3, -4};
        int[] exps2 = {0, 1, 2};
        Polynomial p2 = new Polynomial(coeffs2, exps2);

        System.out.println("Polynomial p2: " + p2);

        // Evaluate p1 at x = -1
        System.out.println("p1 evaluated at x = -1: " + p1.evaluate(-1));
        System.out.println("p1 evaluated at x = 2: " + p1.evaluate(2));
        // Check if p1 has a root at x = 1
        System.out.println("Does p1 have root x = 1? " + p1.hasRoot(1));

        // Add p1 and p2
        Polynomial sum = p1.add(p2);
        System.out.println("p1 + p2: " + sum);

        // 6 - 2x + 5x^3
        double[] coeffs3 = {6, -2, 5};
        int[] exps3 = {0, 1, 3};
        Polynomial p3 = new Polynomial(coeffs3, exps3);

        //1 + 3x - 4x^2
        double[] coeffs4 = {1, 3, -4};
        int[] exps4 = {0, 1, 2};
        Polynomial p4 = new Polynomial(coeffs4, exps4);

        Polynomial product = p3.multiply(p4);
        System.out.println("p3 * p4: " + product);

        System.out.println("start sonstructor test！");
        File tempFile = File.createTempFile("polynomial", ".txt");
        FileWriter writer = new FileWriter(tempFile);
        writer.write("6-2x+5x3"); // 6 - 2x + 5x^3
        writer.close();

        Polynomial polynomial = new Polynomial(tempFile);

        double[] coefficients = polynomial.getCoefficients();
        int[] exponents = polynomial.getExponents();
        System.out.println("the polynomial we have：");
        for (int i = 0; i < coefficients.length; i++) {
            System.out.println("coefficient：" + coefficients[i] + ", exponent：" + exponents[i]);
        }

        System.out.println("coefficient array：");
        for (double coef : coefficients) {
            System.out.print(coef + " ");
        }
        System.out.println();

        System.out.println("exponent array：");
        for (int expo : exponents) {
            System.out.print(expo + " ");
        }
        System.out.println();

        System.out.println("pass！");


        double[] coefficients1 = {6, -2, 5};
        int[] exponents1 = {0, 1, 3};
        Polynomial polynomial1 = new Polynomial(coefficients1, exponents1);

        String polyString = polynomial.toString();
        System.out.println("polystring：" + polyString);

        String fileName = "output_polynomial.txt";

        // saveToFile test
        polynomial1.saveToFile(fileName);
        System.out.println("be saved at：" + fileName);
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);
        if (scanner.hasNextLine()) {
            String fileContent = scanner.nextLine();
            System.out.println("content：" + fileContent);
        }
        scanner.close();

        // expect：6-2x1+5x3

    }
}