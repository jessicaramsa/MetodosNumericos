package MDSolEc;

import javax.swing.JOptionPane;

public class Secante {
    double a, b, m, fa, fb, fm, f, Ea, Es, intI, intF, mant;
    int iter;
    String ec, aux1;
    Condicionales c = new Condicionales();

    void inicio() {
        System.out.println("Método de la Secante");
    }

    void datos() {
        aux1 = "Introduce el punto inicial:";
        intI = Double.parseDouble(JOptionPane.showInputDialog(aux1));
        aux1 = "Introduce el punto final:";
        intF = Double.parseDouble(JOptionPane.showInputDialog(aux1));
        iter = 0;
        a = intI;
        b = intF;
        Es = 0.05;
        mant = 0;
        m = c.mS(a, b, fa, fb);
        Ea = c.ea(m, mant);
    }

    void calculos() {
        do {
            fa = ec(a);
            fb = ec(b);
            m = c.mFs(a, b, fa, fb);
            Ea = c.ea(m, mant);
            a = b;
            b = m;
            mant = m;
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
        Secante s = new Secante();
        s.inicio();
        s.datos();
        s.calculos();
        s.resultados();
    }
}
