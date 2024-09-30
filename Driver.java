import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Driver {

    public static void main(String[] args) throws IOException{
        // Example polynomial: 6 - 2x + 5x^3
        double[] coeffs1 = {6, -2, 5};
        int[] exps1 = {0, 1, 3};
        Polynomial p1 = new Polynomial(coeffs1, exps1);

        System.out.println("Polynomial p1: " + p1);

        // Another polynomial: 1 + 3x - 4x^2
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

        // 创建一个临时文件并写入多项式内容
        System.out.println("开始测试！");
        File tempFile = File.createTempFile("polynomial", ".txt");
        FileWriter writer = new FileWriter(tempFile);
        writer.write("6-2x+5x3"); // 示例多项式：6 - 2x + 5x^3
        writer.close();

        // 用文件初始化 Polynomial 对象
        Polynomial polynomial = new Polynomial(tempFile);

        // 获取系数和指数数组
        double[] coefficients = polynomial.getCoefficients();
        int[] exponents = polynomial.getExponents();

        // 输出系数和指数数组
        System.out.println("解析得到的多项式项：");
        for (int i = 0; i < coefficients.length; i++) {
            System.out.println("系数：" + coefficients[i] + ", 指数：" + exponents[i]);
        }

        // 输出完整的数组
        System.out.println("系数数组：");
        for (double coef : coefficients) {
            System.out.print(coef + " ");
        }
        System.out.println();

        System.out.println("指数数组：");
        for (int expo : exponents) {
            System.out.print(expo + " ");
        }
        System.out.println();

        System.out.println("测试完成！");

        // 创建多项式对象，示例多项式：6 - 2x + 5x^3
        double[] coefficients1 = {6, -2, 5};
        int[] exponents1 = {0, 1, 3};
        Polynomial polynomial1 = new Polynomial(coefficients1, exponents1);

        // 调用 toString 方法，输出多项式字符串
        String polyString = polynomial.toString();
        System.out.println("多项式字符串：" + polyString);

        // 指定保存的文件名
        String fileName = "output_polynomial.txt";

        // 调用 saveToFile 方法
        polynomial1.saveToFile(fileName);
        System.out.println("多项式已保存到文件：" + fileName);

        // 读取文件内容，验证结果
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);
        if (scanner.hasNextLine()) {
            String fileContent = scanner.nextLine();
            System.out.println("文件内容：" + fileContent);
        }
        scanner.close();

        // 手动验证输出的多项式字符串是否正确
        // 预期结果：6-2x+5x3

    }
}