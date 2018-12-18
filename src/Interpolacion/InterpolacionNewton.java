package Interpolacion;

public class InterpolacionNewton {
    private static double[] b;
    private static boolean[][] yaCalculado;
    private static double[][] calculoPrevio;
    private static double[] x;
    private static double[] fx;

    public static double[] solucionar(double[] x, double[] fx) {
        int n = x.length;
        b = new double[n];
        yaCalculado = new boolean[n][n];
        calculoPrevio = new double[n][n];
        InterpolacionNewton.x = x;
        InterpolacionNewton.fx = fx;
        b[0] = fx[0];
        diferenciasDivididas(x.length - 1, 0);
        return b;
    }

    public static double diferenciasDivididas(int i, int k) {
        if (i == k)
            return fx[i];

        double f1 = 0;
        if (yaCalculado[i - 1][k])
            f1 = calculoPrevio[i - 1][k];
        else
            f1 = diferenciasDivididas(i - 1, k);

        double f2 = 0;
        if (yaCalculado[i][k + 1])
            f2 = calculoPrevio[i][k + 1];
        else
            f2 = diferenciasDivididas(i, k + 1);

        double dd = (f1 - f2) / (x[k] - x[i]);
        yaCalculado[i][k] = true;
        calculoPrevio[i][k] = dd;

        if (k == 0)
            b[i] = dd;
        return dd;
    }

    public static double calcularValor(double x, double[] b, double[] xs) {
        double res = 0;
        for (int i = 0; i < b.length; i++) {
            double acum = b[i];
            for (int j = 0; j < i; j++)
                acum *= (x - xs[j]);
            res += acum;
        }
        return res;
    }

    public void calculos() {
        double[] x = {1, 2, 3, 5};
        double[] fx = {3, 6, 19, 99};
        double[] b = solucionar(x, fx);

        System.out.println("-------------------------------------");
        System.out.println("| Método de Interpolación de Newton |");
        System.out.println("-------------------------------------");
        System.out.println("X     |   F(x)");
        System.out.println("--------------");
        for (int j = 0; j < x.length; j++)
            System.out.println(x[j] + "   |   " + fx[j]);
        for (int i = 0; i < b.length; i++)
            System.out.printf("\nb" + i + "   =   %.4f", b[i]);
        System.out.println("\n\nCalcular valor:");
        double valor = calcularValor(4, b, x);
        System.out.printf("f(" + 4 + ") = %.4f", valor);
        double ea;
        ea = Math.abs(((fx[2] - valor) / fx[2])) * 100;
        System.out.print("\n%E = ");
        System.out.printf("%.4f", ea);
        System.out.println("");
    }

    public static void main(String[] args) {
        InterpolacionNewton n = new InterpolacionNewton();
        n.calculos();
    }
}
