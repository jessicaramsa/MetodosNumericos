package MDSolSisEc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Jacobi {
    public Jacobi() {}

    public void MetodoGaussSeidel() {
        double x0, x1, x2, x0a, x1a, x2a, x3a, tol, e;
        int i, j, iter;
        double a[][] = new double[3][4];
        System.out.println("Tolerancia: 0.05");
        tol = 0.05;
        System.out.println("Método de Jacobi");
        System.out.println("Ingrese tolerancia");
        tol = lee();
        System.out.println("Ingrese coeficientes");

        for (i = 0; i < 3; i++) {
            System.out.println("Renglon " + (i + 1));
            for (j = 0; j <= 3; j++) {
                System.out.println(" Ingrese a " + (i + 1) + " " + (j + 1));
                a[i][j] = lee();
            }
        }

        x0 = 0.0;
        x1 = 0.0;
        x2 = 0.0;
        iter = 0;

        do {
            e = x1;
            x0a = (a[0][3] - x1 * a[0][1] - x2 * a[0][2]) / a[0][0];
            x1a = (a[1][3] - x0 * a[1][0] - x2 * a[1][2]) / a[1][1];
            x2a = (a[2][3] - x0 * a[2][0] - x1 * a[2][1]) / a[2][2];
            x1 = x1a;
            x2 = x2a;
            x0 = x0a;
            iter++;
        } while (Math.abs(e - x1) > tol);
        
        CondicionalesSis c = new CondicionalesSis();
        System.out.println("X1 = " + c.cs(x0));
        System.out.println("X2 = " + c.cs(x1));
        System.out.println("X3 = " + c.cs(x2));
        System.out.println("Iteración: " + iter);

        System.out.println("\nMétodo por Gauss-Seidel");
        x1 = 0.0;
        x2 = 0.0;
        iter = 0;

        do {
            e = x1;
            x0 = (a[0][3] - x1 * a[0][1] - x2 * a[0][2]) / a[0][0];
            x1 = (a[1][3] - x0 * a[1][0] - x2 * a[1][2]) / a[1][1];
            x2 = (a[2][3] - x0 * a[2][0] - x1 * a[2][1]) / a[2][2];
            iter++;
        } while (Math.abs(e - x1) > tol);
        
        System.out.println("X1 = " + c.cs(x0));
        System.out.println("X2 = " + c.cs(x1));
        System.out.println("X3 = " + c.cs(x2));
        System.out.println("Iteración: " + iter);
    }

    public double lee() {
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

    public int leeint() {
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

    public static void main(String[] args) {
        Jacobi j = new Jacobi();
        j.MetodoGaussSeidel();
    }
}
