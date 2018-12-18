package IntegracionNumerica;

import javax.swing.JOptionPane;

public class Diferencia {
    double x[] = new double[10];
    double y[] = new double[10];
    double er[] = new double[5];
    int a, b, n;
    double ax, areal, areaS, non, par, xi, h, xh, xih, fp;
    double f[] = new double[3];
    String aux;

    void inicio() {
        System.out.println("Integración Numérica");
    }

    void datos() {
        non = 0;
        par = 0;

        System.out.println("\n>> Diferencia Numérica");
        System.out.println("Ecuación f(x) = x^2 - 4x + 5");
        System.out.println("f'(x) = 2x - 4");

        aux = "Ingrese el valor de x:";
        xi = Double.parseDouble(JOptionPane.showInputDialog(aux));
        aux = "Ingrese el valor de h:";
        h = Double.parseDouble(JOptionPane.showInputDialog(aux));
    }

    void diferenciaN() {
        fp = (2 * xi) - 4;

        xh = xi - h;
        xih = xi + h;

        y[0] = (Math.pow(xh, 2)) - (4 * xh) + 5;
        y[1] = (Math.pow(xi, 2)) - (4 * xi) + 5;
        y[2] = (Math.pow(xih, 2)) - (4 * xih) + 5;

        f[0] = (y[2] - y[1]) / h;
        er[0] = (Math.abs((fp - f[0]) / fp)) * 100;

        f[1] = (y[1] - y[0]) / h;
        er[1] = (Math.abs((fp - f[1]) / fp)) * 100;

        f[2] = (y[2] - y[0]) / (2 * h);
        er[2] = (Math.abs((fp - f[2]) / fp)) * 100;
    }

    void desplegar() {
        System.out.println("\nXi = " + xi);
        System.out.println("h = " + h);
        System.out.printf("f'(xi) = %.3f", fp);

        System.out.println("\n\nX          f(x)");
        System.out.printf("%.3f", xh);
        System.out.printf("     %.3f", y[0]);
        System.out.printf("\n%.3f", xi);
        System.out.printf("     %.3f", y[1]);
        System.out.printf("\n%.3f", xih);
        System.out.printf("     %.3f", y[2]);

        System.out.printf("\n\nDerecha = %.3f", f[0]);
        System.out.printf("         Er = %.3f", er[0]);
        System.out.printf("\nIzquierda = %.3f", f[1]);
        System.out.printf("         Er = %.3f", er[1]);
        System.out.printf("\nCentral = %.3f", f[2]);
        System.out.printf("         Er = %.3f", er[2]);
        System.out.println();
    }

    public static void main(String[] args) {
        Diferencia d = new Diferencia();
        d.inicio();
        d.datos();
        d.diferenciaN();
        d.desplegar();
    }
}
