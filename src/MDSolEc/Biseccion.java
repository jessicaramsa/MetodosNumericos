package MDSolEc;

import javax.swing.JOptionPane;

public class Biseccion {
    double a, b, m, fa, fb, fm, f, intI, intF, Ea, Es, mant;
    int iter;
    String ec, aux1;
    Condicionales c = new Condicionales();

    void inicio() {
        System.out.println("Método de Bisección");
    }

    void datos() {
        aux1 = "Introduzca el punto inicial:";
        intI = Double.valueOf(JOptionPane.showInputDialog(aux1));
        aux1 = "Introduzca el punto final";
        intF = Double.valueOf(JOptionPane.showInputDialog(aux1));
        a = intI;
        b = intF;
        Es = 0.05;
        mant = 0;
        m = c.mB(a, b);
        Ea = c.ea(m, mant);
        iter = 0;
    }

    void calculos() {
        do {
            fa = ec(a);
            fb = ec(b);
            m = c.mFs(a, b, fa, fb);
            fm = ec(m);
            if (fa * fm > 0) {
                a = m;
                Ea = c.ea(m, mant);
                mant = m;
            } else {
                b = m;
                Ea = c.ea(m, mant);
                mant = m;
            }
            m = c.mFs(a, b, fa, fb);
            iter++;
        } while (Ea > Es);
    }

    void resultados() {
        aux1 = ec + "\nIntervalo [" + intI + "," + intF + "]\n#Iteración: " + iter;
        aux1 += "\n%Ea: " + c.cs(Ea) + "\nRaíz: " + c.cs(m);
        System.out.println(aux1);
    }

    public double ec(double x) {
        ec = "f(x)=x^3 - 3x + 1";
        f = Math.pow(x, 3) - 3 * x + 1;
        return f;
    }

    public static void main(String[] args) {
        Biseccion mb = new Biseccion();
        mb.inicio();
        mb.datos();
        mb.calculos();
        mb.resultados();
    }
}
