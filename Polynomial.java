import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Polynomial {
    private double[] coefficients;   // array for non-zero coefficients
    private int[] exponents;        // Array for corresponding

    // Constructor to initialize the polynomial with coefficients and exponents
    public Polynomial(double[] coefficients, int[] exponents) {
        this.coefficients = coefficients;
        this.exponents = exponents;
    }

    public Polynomial() {
        this.coefficients = new double[]{0};
        this.exponents = new int[]{0};
    }

    // constructor
    public Polynomial(File file) throws IOException {
        //  ArrayList temporaily store the poly 
        ArrayList<Double> coefList = new ArrayList<>();
        ArrayList<Integer> expoList = new ArrayList<>();

        Scanner scanner = new Scanner(file);
        if (scanner.hasNextLine()) {
            String polynomial = scanner.nextLine();
            parsePolynomial(polynomial, coefList, expoList);
        }
        scanner.close();

        // concert to array
        this.coefficients = new double[coefList.size()];
        this.exponents = new int[expoList.size()];
        for (int i = 0; i < coefList.size(); i++) {
            this.coefficients[i] = coefList.get(i);
            this.exponents[i] = expoList.get(i);
        }
    }

    // analysize
    private void parsePolynomial(String polynomial, ArrayList<Double> coefList, ArrayList<Integer> expoList) {
        polynomial = polynomial.replace("-", "+-"); 
        String[] parts = polynomial.split("\\+");
        for (String part : parts) {
            if (!part.isEmpty()) {
                double coefficient = 1;
                int exponent = 0;

                if (part.contains("x")) {
                    String[] term = part.split("x");
                    // handle coefficient
                    if (!term[0].isEmpty() && !term[0].equals("+") && !term[0].equals("-")) {
                        coefficient = Double.parseDouble(term[0]);
                    } else if (term[0].equals("-")) {
                        coefficient = -1;
                    } else if (term[0].equals("+") || term[0].isEmpty()) {
                        coefficient = 1;
                    }

                    // handle exponent
                    if (term.length > 1 && !term[1].isEmpty()) {
                        exponent = Integer.parseInt(term[1]);
                    } else {
                        exponent = 1;
                    }
                } else {
                    coefficient = Double.parseDouble(part);
                    exponent = 0;
                }
                
                coefList.add(coefficient);
                expoList.add(exponent);
            }
        }
    }

    // Getter 
    public double[] getCoefficients() {
        return coefficients;
    }

    public int[] getExponents() {
        return exponents;
    }


    public Polynomial add(Polynomial other) {
        int maxExponent = Math.max(this.exponents[this.exponents.length - 1], other.exponents[other.exponents.length - 1]);

        double[] resultCoefficients = new double[maxExponent + 1];

        for (int i = 0; i < this.coefficients.length; i++){
            int exp = this.exponents[i];
            resultCoefficients[exp] = this.coefficients[i];
        }

        int nonZeroCount = 0;
        for (double coefficient : resultCoefficients){
            if (coefficient != 0) {
                nonZeroCount++;
            }
        }

        double[] finalCoefficients = new double[nonZeroCount];
        int[] finalExponents = new int[nonZeroCount];
        int index = 0;
        for (int exp = 0; exp < resultCoefficients.length; exp++) {
            if (resultCoefficients[exp] != 0) {
                finalCoefficients[index] = resultCoefficients[exp];
                finalExponents[index] = exp;
                index++;
            }
        }
        return new Polynomial(finalCoefficients, finalExponents);
    }

    public double evaluate(double x) {
        double result = 0;
        for (int i = 0; i < coefficients.length; i++) {
            result += coefficients[i] * Math.pow(x, exponents[i]);
        }
        return result;
    }

    public boolean hasRoot(double x) {
        return evaluate(x) == 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
    
        for (int i = 0; i < coefficients.length; i++) {
            double coefficient = coefficients[i];
            int exponent = exponents[i];

            if (coefficient == 0) {
                continue; 
            }

            if (sb.length() > 0) {
                if (coefficient > 0) {
                    sb.append(" + ");
                } else {
                    sb.append(" - ");
                    coefficient = -coefficient;
                }
            } else if (coefficient < 0) {
                sb.append("-");
                coefficient = -coefficient;
            }

            if (exponent == 0) {
                sb.append(coefficient); 
            } else if (exponent == 1) {
                if (coefficient == 1) {
                    sb.append("x"); 
                } else {
                    sb.append(coefficient).append("x");
                }
            } else {
                if (coefficient == 1) {
                    sb.append("x^").append(exponent);
                } else {
                    sb.append(coefficient).append("x^").append(exponent);
                }
            }
        }

        return sb.toString();
    }

    public Polynomial multiply(Polynomial other) {
        Map<Integer, Double> resultMap = new HashMap<>();
        // Multiply each term of this polynomial with each term of the other polynomial
        for (int i = 0; i < this.coefficients.length; i++) {
            for (int j = 0; j < other.coefficients.length; j++) {
                int exp = this.exponents[i] + other.exponents[j];
                double coef = this.coefficients[i] * other.coefficients[j];

                resultMap.put(exp, resultMap.getOrDefault(exp, 0.0) + coef);  // Combine like terms
            }
        }

        // Convert the map to arrays
        int size = resultMap.size();
        double[] resultCoefficients = new double[size];
        int[] resultExponents = new int[size];

        int index = 0;
        for (Map.Entry<Integer, Double> entry : resultMap.entrySet()) {
            resultExponents[index] = entry.getKey();
            resultCoefficients[index] = entry.getValue();
            index++;
        }

        return new Polynomial(resultCoefficients, resultExponents);
    }

    public void saveToFile(String fileName) throws IOException {
        String polynomialStr = this.toString();

        FileWriter writer = new FileWriter(new File(fileName));
        writer.write(polynomialStr);
        writer.close();
    }

}
