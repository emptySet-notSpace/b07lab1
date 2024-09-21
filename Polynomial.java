public class Polynomial {
    private double[] coefficients;

    public Polynomial() {
        this.coefficients = new double[]{0};
    }

    public Polynomial(double[] coefficients) {
        this.coefficients = coefficients;
    }

    public Polynomial add(Polynomial other) {
        int maxLength = Math.max(this.coefficients.length, other.coefficients.length);
        double[] resultCoefficients = new double[maxLength];

        for (int i = 0; i < maxLength; i++) {
            double thisCoefficient = (i < this.coefficients.length) ? this.coefficients[i] : 0;
            double otherCoefficient = (i < other.coefficients.length) ? other.coefficients[i] : 0;
            resultCoefficients[i] = thisCoefficient + otherCoefficient;
        }

        return new Polynomial(resultCoefficients);
    }

    public double evaluate(double x) {
        double result = 0;
        for (int i = 0; i < coefficients.length; i++) {
            result += coefficients[i] * Math.pow(x, i);
        }
        return result;
    }

    public boolean hasRoot(double x) {
        return evaluate(x) == 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = coefficients.length - 1; i >= 0; i--) {
            if (coefficients[i] != 0) {
                if (sb.length() > 0 && coefficients[i] > 0) {
                    sb.append(" + ");
                } else if (coefficients[i] < 0) {
                    sb.append(" - ");
                }

                double absValue = Math.abs(coefficients[i]);
                if (i == 0) {
                    sb.append(absValue);
                } else if (i == 1) {
                    sb.append(absValue).append("x");
                } else {
                    sb.append(absValue).append("x^").append(i);
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        double[] coeffs1 = {6, -2, 0, 5};
        Polynomial p1 = new Polynomial(coeffs1);

        System.out.println("Polynomial p1: " + p1);

        double[] coeffs2 = {1, 3, -4};
        Polynomial p2 = new Polynomial(coeffs2);

        System.out.println("Polynomial p2: " + p2);

        System.out.println("p1 evaluated at x = -1: " + p1.evaluate(-1));

        System.out.println("Does p1 have root x = 1? " + p1.hasRoot(1));

        Polynomial sum = p1.add(p2);
        System.out.println("p1 + p2: " + sum);
    }
}
