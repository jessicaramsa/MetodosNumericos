package MDSolEc;

import javax.swing.JOptionPane;

public class NewtonRaphson {
    double xi, xant, fxi, fxi1, fxant, fxant1, f, f1, f2, Ea, Es;
    int iter, intI;
    String ec, ec1, ec2, aux1;
    Condicionales c = new Condicionales();

    void inicio() {
        System.out.println("Método de Newton-Raphson");
    }

    void datos() {
        aux1 = "Introduce el punto a evaluar:";
        intI = Integer.parseInt(JOptionPane.showInputDialog(aux1));
        xi = intI;
        xant = 0;
        Es = 0.05;
        Ea = c.ea(xi, xant);
        iter = 0;
    }

    void calculos() {
        do {
            fxi = ec(xi);
            fxi1 = ec1(xi);
            Ea = c.ea(xi, xant);
            xant = xi;
            fxant = fxi;
            fxant1 = fxi1;
            xi = c.mNr(xant, fxant, fxant1);
            iter++;
        } while (Ea > Es);
    }

    void resultados() {
        aux1 = ec + "\nPunto: " + intI + "\n#Iteración: " + iter;
        aux1 += "\n%Ea: " + c.cs(Ea) + "\nRaíz: " + c.cs(xi);
        System.out.println(aux1);
    }

    public double ec(double x) {
        ec = "f(x)=2x^3 - e^x";
        f = 2 * Math.pow(x, 3) - Math.exp(x);
        return f;
    }

    public double ec1(double x) {
        ec1 = "f'(x)=6x^2 - e^x";
        f1 = 6 * Math.pow(x, 2) - Math.exp(x);
        return f1;
    }

    public double ec2(double x) {
        ec2 = "f''(x)=12x - e^x";
        f2 = 12 * x - Math.exp(x);
        return f2;
    }

    public static void main(String[] args) {
        NewtonRaphson nr = new NewtonRaphson();
        nr.inicio();
        nr.datos();
        nr.calculos();
        nr.resultados();
    }
}
