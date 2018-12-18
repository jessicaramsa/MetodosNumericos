package MDSolEc;

import java.text.DecimalFormat;

public class Condicionales {
    double m, EaPor, xi;

    public double cs(double n) {
        DecimalFormat c = new DecimalFormat("#,#####");
        n = Double.valueOf(c.format(n));
        return n;
    }

    public double mB(double a, double b) {
        m = (a + b) / 2;
        cs(m);
        return m;
    }

    public double mFs(double a, double b, double fa, double fb) {
        m = (b * fa - a * fb) / (fa - fb);
        cs(m);
        return m;
    }

    public double mS(double a, double b, double fa, double fb) {
        m = (a * fb - b * fa) / (fb - fa);
        cs(m);
        return m;
    }

    public double mNr(double xant, double fxant, double f2xant) {
        xi = xant - (fxant / f2xant);
        cs(xi);
        return xi;
    }

    public double ea(double mact, double mant) {
        EaPor = Math.abs((mact - mant) / mact) * 100;
        return (EaPor);
    }
}
