package IntroduccionMD;

import java.text.DecimalFormat;
import javax.swing.JOptionPane;

public class IMC {
    int r;
    String aux1;
    Double p, e, imc, imcR;

    void inicio() {
        aux1 = "Una medidad de la obesidad se determina mediante el índice";
        aux1 += " de masa corporal (IMC),\nque calcula dividiendo los kilogramos";
        aux1 += " en peso por el cuadrado de la estatura\nen metros";
        aux1 += " (IMC = peso[kg] / estatura[m2]).";
        JOptionPane.showMessageDialog(null, aux1, "IMC", 1);
    }

    void datos() {
        aux1 = "Introduce tu peso en kilogramos:";
        p = Double.parseDouble(JOptionPane.showInputDialog(aux1));
        aux1 = "Introduce tu estatura en metros:";
        e = Double.parseDouble(JOptionPane.showInputDialog(aux1));
    }

    void calculos() {
        DecimalFormat cs = new DecimalFormat("#,##");
        imc = p / (Math.pow(e, 2));
        imcR = Double.parseDouble(cs.format(imc));
        estatus(imc);
    }

    String estatus(Double i) {
        if (i < 18.5)
            return "Peso inferior al normal";
        else if (i <= 24.9)
            return "Peso normal";
        else if (i <= 29.9)
            return "Peso superior al normal";
        else if (i <= 34.9)
            return "Obesidad Tipo 1";
        else if (i <= 39.9)
            return "Obesidad Tipo 2";
        return "Obesidad tipo 3";
    }

    void resultados() {
        aux1 = "Peso:\n" + p + "\nEstatura: " + e + "\nIMC= " + imcR + "\n" + estatus(imc);
        JOptionPane.showMessageDialog(null, aux1);
    }

    void navegabilidad() {
        aux1 = "¿Desea calcular otro IMC?\n";
        aux1 += "1.Si\n2.No";
        do {
            r = Integer.parseInt(JOptionPane.showInputDialog(null, aux1));
            switch (r) {
                case 1: datos(); break;
                case 2: break;
            }
        } while (r != 2);
    }

    public static void main(String[] args) {
        IMC imc = new IMC();

        imc.inicio();
        imc.datos();
        imc.calculos();
        imc.resultados();
        imc.navegabilidad();
    }
}
