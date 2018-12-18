/*
    INSTITUTO TECNOLÓGICO DE LEÓN
    Ingeniería en Sistemas Computacionales
    Métodos Numéricos
    Jéssica Ramírez Sánchez
    16 Noviembre 2017
    Integración Numérica
*/

/*
    NOTA IMPORTANTE: Hay un problema al momento de aplicar el método cs(),
                     ya que manda una excepción de números infinitos.
                     Con printf funciona bien.
                     Tienes dos opciones: cambiar todo a printf o 
                     revisar el método porque en estos momentos no logro 
                     encontrar el error.
                     Lo más probable es que el DecimalFormat está manejando
                     números muy grandes y no pueda con más. Sin la coma no
                     funciona.
                     Revisar las APIS xd.
*/

package IntegracionNumerica;

import java.text.DecimalFormat;
import javax.swing.JOptionPane;

public class IntegracionNumerica{
    double x[]=new double[10];
    double y[]=new double[10];
    double area[]=new double[5];
    double er[]=new double[5];
    double ax;
    int a,b,n;
    String aux1;

    void inicio(){
        System.out.println("Integracion Numerica");
    }
    
    /*
        1.Valor inicial (a).
        2.Valor final (b).
        3.Número de repeticiones (n).
        4.Ecuación a resolver.
            >>Modificar para que el usuario pueda introducir su propia ecuación
            o bien presentar un menú con los tipos de ecuaciones
            (EQN calculadora).
    */
    public void datos(){
        System.out.println("Ecuacion: x^2 + 3x - 3");
        
        aux1="Ingrese el valor inicial a:";
        a=Integer.parseInt(JOptionPane.showInputDialog(aux1));
        System.out.println("a = "+a);
        aux1="Ingrese el valor final b:";
        b=Integer.parseInt(JOptionPane.showInputDialog(aux1));
        System.out.println("b = "+b);
        aux1="Ingrese el valor de n:";
        n=Integer.parseInt(JOptionPane.showInputDialog(aux1));
        System.out.println("n = "+n);
    }
    
    /*
        1.Calcular el incremento (Ax).
        2.Calcular valores de "xi" empezando desde el valor inicial (a).
        3.Con ayuda de la ecuación y los valores de "xi" previamente calculados
        calcular los valores de "yi".
        4.Calcular el valor real del área de la ecuación.
    
        >> Delimitar a 5 cifras significativas.
    */
    public void calculos(){
        ax=(b-a)/n;
        System.out.println("\nIncremento: "+ax);
        
        for(int i=0; i<n; i++) x[i]=cs(a+(i*ax));
        for(int j=0; j<n; j++) y[j]=(Math.pow(x[j],2)+(3*x[j])-3);
        
        System.out.println("Xi          Yi");
        for(int i=0; i<n; i++) System.out.println(x[i]+"        "+y[i]);
        
        area[0]=(Math.pow(b,3)/3)+(3*Math.pow(b,2)/2)-(3*b);
        area[0]=area[0]-(Math.pow(a,3)/3)-(3*Math.pow(a,2)/2)+(3*a);
        System.out.println("Area real: "+cs(area[0]));
        
        rectangular();
        trapezoidal();
        parabolico();
    }
    
    public double cs(double num){
        try{
            DecimalFormat c=new DecimalFormat("#,#####");
            return Double.valueOf(c.format(num));
        }catch(NumberFormatException ioe){
            System.out.println(ioe);
            return num;
        }
    }
    
    /*
        MÉTODO RECTANGULAR
        1.Calcular el valor del área por la izquierda (aizq).
        2.Calcular el valor del área por la derecha (ader).
        3.Se deben de calcular los márgenes de error.
            >> Si el margen de error por la izquierda y por la derecha se aleja
            demasiado del valor real, se debe de calcular el área central
            [(aizq+ader)/2].
    */
    void rectangular(){
        System.out.println("\n>> Metodo Rectangular");
        
        for(int i=0; i<(n-1); i++) area[1]+=y[i];
        area[1]=Math.abs(area[1])*ax;
        er[1]=Math.abs((area[0]-area[1])/area[0])*100;
        System.out.println("Area por la izquierda: "+cs(area[1]));
        
        for(int i=1; i<n; i++) area[2]+=y[i];
        area[2]=Math.abs(area[2])*ax;
        er[2]=Math.abs((area[0]-area[2])/area[0])*100;
        System.out.println("Area por la derecha: "+cs(area[2]));
        
        if(er[1]>0.05 || er[2]>0.05){
            area[3]=(area[1]+area[2])/2;
            System.out.println("Area central: "+cs(area[3]));
            er[3]=Math.abs((area[0]-area[3])/area[0])*100;
            System.out.println("Margen de error: "+cs(er[3]));
        }else{
            System.out.println("Error relativo aizq: "+cs(er[1])+"%");
            System.out.println("Error relativo ader: "+cs(er[2])+"%");
        }
    }
    
    /*
        MÉTODO TRAPEZOIDAL
        1.Sumamos los valores de yi.
        2.Calculamos el valor del área supuesta y su margen de error.
    */
    void trapezoidal(){
        System.out.println("\n>> Metodo Trapezoidal");
        
        for(int i=1; i<(n-1); i++) area[1]+=y[i];
        area[2]=(ax/2)*(y[0]+y[n]+2*area[1]);
        er[1]=Math.abs((area[0]-area[2])/area[0])*100;
        aux1="Area supuesta: "+cs(area[2])+"\nMargen de error: ";
        aux1+=cs(er[1])+"%.";
        System.out.println(aux1);
    }
    
    /*
        MÉTODO PARABÓLICO
        NOTA: Recordar que solo cuando "n" sea par podremos resolver la
        ecuación mediante este método ya que así se indica la fórmula
        de la parábola.
           
        1.Primer ciclo: se suman los valores yi impares (area[1]).
        2.Segundo ciclo: se suman los valores yi pares (area[2]).
            >> En los ciclos aumentamos de 2 en 2 para que se sumen solo
            impares con impares y pares con pares.
        3.Se calcula el área con la fórmula de la parábola (area[3]).
        4.Calculamos el error relativo respecto al valor real (%).
    
        PD: Si "n" es impar se lo hacemos saber al usuario.
    */
    void parabolico(){
        System.out.println("\n>> Metodo Parabólico");
        
        if(n%2==0){
            for(int i=1; i<(n-1); i+=2) area[1]+=y[i];
            for(int j=2; j<(n-2); j+=2) area[2]+=y[j];
            area[3]=(ax/3)*(y[0]+y[n]+4*area[1]+2*area[2]);
            er[1]=Math.abs(area[0]-area[3])*100;
            aux1="Area supuesta: "+cs(area[3])+"\nError relativo: ";
            aux1+=cs(er[1])+"%.";
            System.out.println(aux1);
        }else{
            aux1="La ecuacion no se puede resolver por este metodo.";
            aux1+="\nYa que n es impar ("+n+").";
            System.out.println(aux1);
        }
    }
    
    void navegabilidad(){
        int r;
        
        do{
            aux1="¿Deseas calcular para otro valor de n?\n1.Si\n2.No";
            r=Integer.parseInt(JOptionPane.showInputDialog(aux1));
            switch(r){
                case 1: datos(); calculos(); break;
                case 2: System.exit(0);
            }
        }while(r!=2);
    }
    
    public static void main(String[] args){
        IntegracionNumerica in=new IntegracionNumerica();
        in.inicio();
        in.datos();
        in.calculos();
//        in.navegabilidad();
    }
}
