package MDSolSisEc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.JOptionPane;

public class GaussSeidel {
    double x[] = new double[4];
    double tol, e;
    double a[][] = new double[4][5];
    int iter, ec;
    String aux1;
    CondicionalesSis cs = new CondicionalesSis();

    void inicio() {
        aux1 = "Método de solución de ecuaciones\nGauss-SeidelS";
        System.out.println(aux1);
    }

    void capturaM() {
        iter = 0;
        for (int i = 0; i < 4; i++)
            x[i] = 0.0;

        System.out.println("Ingrese tolerancia:");
        tol = leeD();
        System.out.println("Ingrese número de ecuaciones:");
        ec = leeInt();
        System.out.println("Ingrese coeficientes:");

        for (int i = 0; i < ec; i++) {
            System.out.println("Ecuación " + (i + 1));
            for (int j = 0; j < (ec + 1); j++) {
                System.out.println("Ingrese posición a" + (i + 1) + " " + (j + 1));
                a[i][j] = leeD();
            }
        }

        valM(a, ec);
    }

    void valM(double m[][], int x) {
        boolean c1, c2;
        c1 = cs.condGS1(m, x);
        c2 = cs.condGS2(m, x);
        if (c1 == true || c2 == true)
            metodoGaussSeidel();
        else {
            aux1 = "La ecuación no se puede resolver por GaussSeidel.";
            System.out.println(aux1);
            navegabilidad();
        }
    }

    public void metodoGaussSeidel() {
        do {
            e = x[0];
            x[0] = (a[0][4] - x[1] * a[0][1] - x[2] * a[0][2] - x[3] * a[0][3]) / a[0][0];
            x[1] = (a[1][4] - x[0] * a[1][0] - x[2] * a[1][2] - x[3] * a[1][3]) / a[1][1];
            x[2] = (a[2][4] - x[0] * a[2][0] - x[1] * a[2][1] - x[3] * a[2][3]) / a[2][2];
            x[3] = (a[3][4] - x[0] * a[3][0] - x[1] * a[3][1] - x[2] * a[3][2]) / a[3][3];
            iter++;
        } while (Math.abs(e - x[0]) > tol);
        resultados();
    }

    public double leeD() {
        double num;
        try {
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);
            String sdato;
            sdato = br.readLine();
            num = Double.parseDouble(sdato);
        } catch (IOException ioe) {
            num = 0.0;
        }
        return num;
    }

    public int leeInt() {
        int num;
        try {
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);
            String sdato;
            sdato = br.readLine();
            num = Integer.parseInt(sdato);
        } catch (IOException ioe) {
            num = 0;
        }
        return num;
    }

    void resultados() {
        System.out.println("Ecuaciones:");

        for (int i = 0; i < ec; i++) {
            for (int j = 0; j < 4; j++)
                aux1 += a[i][j] + "X" + (j + 1) + " ";
            aux1 += " = " + a[i][ec + 1];
            System.out.println(aux1);
        }

        System.out.println("Raíces:");
        for (int i = 0; i < ec; i++)
            System.out.println("X" + (i + 1) + " = " + x[i]);

    }

    void navegabilidad() {
        int mp;

        aux1 = "¿Deseas calcular otras raíces?\n1.Si\n2.No";
        mp = Integer.parseInt(JOptionPane.showInputDialog(aux1));
        switch (mp) {
            case 1:
                capturaM();
                metodoGaussSeidel();
                break;
            case 2:
                System.exit(0);
        }
    }

    public static void main(String[] args) {
        GaussSeidel gs = new GaussSeidel();
        gs.inicio();
        gs.capturaM();
        gs.navegabilidad();
    }
}
