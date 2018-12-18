package MDSolSisEc;

import java.text.DecimalFormat;

public class CondicionalesSis {
    double EaPor, suma;

    public double cs(double n) {
        DecimalFormat c = new DecimalFormat("#,#####");
        n = Double.valueOf(c.format(n));
        return n;
    }

    public double ea(double xact, double xant) {
        EaPor = Math.abs((xact - xant) / xact) * 100;
        return (EaPor);
    }

    public boolean condGS1(double a[][], int ec) {
        suma = 0;

        for (int j = 1; j < ec; j++) {
            a[0][j] = Math.abs(a[0][j]);
            suma += a[0][j];
        }

        return suma >= a[0][0];
    }

    public boolean condGS2(double a[][], int ec) {
        boolean r[] = new boolean[ec];
        boolean c;
        suma = 0;

        if (condGS1(a, ec) == true)
            return true;
        else {
            r[0] = false;

            for (int i = 1; i < ec; i++) {
                for (int j = 0; j < ec; j++)
                    suma += a[i][j];
                suma -= a[i][i + 1];
                if (suma == a[i][i + 1])
                    r[i] = true;
            }

            for (int i = 0; i < ec; i++) {
                if (r[i] == true) {
                    i = ec;
                    return true;
                }
            }
        }
        return false;
    }
}
