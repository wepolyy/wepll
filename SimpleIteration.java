public class SimpleIteration {

    static double phi(double x) {
        return Math.cos(x);
    }

    static double dphi(double x) {
        return -Math.sin(x);
    }

    static double f(double x) {
        return x - Math.cos(x);
    }

    public static void main(String[] args) {
        double a = 0.0;
        double b = 1.0;
        double eps = 1e-4;
        double x0 = 0.5;
        int maxIter = 1000;

        double q = 0.0;
        for (double x = a; x <= b; x += 0.001) {
            q = Math.max(q, Math.abs(dphi(x)));
        }
        if (q >= 1) {
            System.out.println("Условие сходимости не выполнено: q = " + q);
            return;
        }
        System.out.printf("q = %.4f — сходимость обеспечена%n%n", q);

        double x1;
        int n = 0;

        System.out.printf("%-4s %-12s %-12s %-12s%n",
                "n", "x_n", "x_(n+1)", "|x_(n+1)-x_n|");

        do {
            x1 = phi(x0);
            n++;
            System.out.printf("%-4d %-12.6f %-12.6f %-12.6f%n",
                    n, x0, x1, Math.abs(x1 - x0));
            if (Math.abs(x1 - x0) < eps) break;
            x0 = x1;
        } while (n < maxIter);

        System.out.println();
        System.out.printf("Корень: x* = %.6f%n", x1);
        System.out.printf("Число итераций: %d%n", n);
        System.out.printf("Проверка f(x*) = %.2e%n", f(x1));
    }
}
