package MDSolEc;

import javax.swing.JOptionPane;

public class FalsaPosicion {
    double a, b, m, f, fa, fb, fm, Ea, Es, mant;
    int iter, intI, intF;
    String ec, aux1;
    Condicionales c = new Condicionales();

    void inicio() {
        System.out.println("Método de Falsa Posición");
    }

    void datos() {
        aux1 = "Introduce el punto inicial:";
        intI = Integer.parseInt(JOptionPane.showInputDialog(aux1));
        aux1 = "Introduce el punto final:";
        intF = Integer.parseInt(JOptionPane.showInputDialog(aux1));
        iter = 0;
        a = intI;
        b = intF;
        Es = 0.05;
        mant = 0;
        m = c.mFs(a, b, fa, fb);
        Ea = c.ea(m, mant);
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
        ec = "f(x)=e^-x - x";
        f = Math.exp(-x) - x;
        return f;
    }

    public static void main(String[] args) {
        FalsaPosicion fp = new FalsaPosicion();
        fp.inicio();
        fp.datos();
        fp.calculos();
        fp.resultados();
    }
}
