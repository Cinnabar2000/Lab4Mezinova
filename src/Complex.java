public class Complex {
    private double re, im;
    Complex(){
        re=0;
        im=0;
    }
    Complex(double x, double y){
        re=x;
        im=y;
    }
    Complex Pow(){

        return(new Complex(re*re-im*im, 2*re*im));

    }
    Complex Plus(Complex complex){

        return(new Complex(complex.re+re, complex.im+im));

    }
    double modul(){
        return(re*re+im*im);
    }
}