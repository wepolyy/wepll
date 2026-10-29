public class NewtonAndSecant {

    // Исходная функция f(x) = 2x - 1/tg(x)
    public static double f(double x) {
        return 2 * x - 1.0 / Math.tan(x);
    }

    // Производная f'(x) = 2 + 1/sin^2(x) (для метода Ньютона)
    public static double df(double x) {
        double sinX = Math.sin(x);
        return 2.0 + 1.0 / (sinX * sinX);
    }

    // МЕТОД НЬЮТОНА
    public static void newtonMethod(double x0, double eps, int maxIter) {
        System.out.println("--- МЕТОД НЬЮТОНА ---");
        System.out.printf("%-5s %-15s %-15s %-15s%n", "n", "x_n", "f(x_n)", "|x_(n+1)-x_n|");
        
        double x = x0;
        for (int n = 1; n <= maxIter; n++) {
            double fx = f(x);
            double dfx = df(x);
            
            if (Math.abs(dfx) < 1e-15) {
                System.out.println("Производная близка к нулю. Метод остановлен.");
                return;
            }

            double xNext = x - fx / dfx;
            double diff = Math.abs(xNext - x);
            
            System.out.printf("%-5d %-15.6f %-15.6f %-15.6f%n", n, x, fx, diff);

            if (diff < eps) {
                System.out.printf("%nКорень найден: x = %.6f%n", xNext);
                System.out.println("Итераций затрачено: " + n + "\n");
                return;
            }
            x = xNext;
        }
        System.out.println("Достигнут лимит итераций.\n");
    }

    // МЕТОД ХОРД
    public static void secantMethod(double a, double b, double eps, int maxIter) {
        System.out.println("--- МЕТОД ХОРД ---");
        System.out.printf("%-5s %-15s %-15s %-15s%n", "n", "x_n", "f(x_n)", "|x_(n+1)-x_n|");

        // Защита от деления на ноль на границе a=0
        double x0 = (a == 0) ? 0.5 : a;
        double x1 = b;

        for (int n = 1; n <= maxIter; n++) {
            double fx0 = f(x0);
            double fx1 = f(x1);
            
            if (Math.abs(fx1 - fx0) < 1e-15) {
                System.out.println("Деление на ноль в знаменателе. Метод остановлен.");
                return;
            }
            
            // Формула метода хорд
            double xNext = x1 - fx1 * (x1 - x0) / (fx1 - fx0);
            double diff = Math.abs(xNext - x1);
            
            System.out.printf("%-5d %-15.6f %-15.6f %-15.6f%n", n, x1, fx1, diff);

            if (diff < eps) {
                System.out.printf("%nКорень найден: x = %.6f%n", xNext);
                System.out.println("Итераций затрачено: " + n + "\n");
                return;
            }
            
            x0 = x1;
            x1 = xNext;
        }
        System.out.println("Достигнут лимит итераций.\n");
    }

    public static void main(String[] args) {
        double a = 0.0;
        double b = 1.0;
        double eps = 0.0001;
        double x0 = 0.7; // Начальное приближение для Ньютона
        int maxIter = 1000;

        newtonMethod(x0, eps, maxIter);
        secantMethod(a, b, eps, maxIter);
    }
}
