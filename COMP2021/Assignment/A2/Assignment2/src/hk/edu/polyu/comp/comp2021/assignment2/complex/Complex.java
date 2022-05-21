package hk.edu.polyu.comp.comp2021.assignment2.complex; // OOP_A2-2 Made by ZHANG Wengyu(21098431d)

public class Complex {

    // Task 3: add the missing fields
    private Rational real;
    private Rational imag;

    public Complex(Rational real, Rational imag) {
        // Task 4: complete the constructor
        this.real = real;
        this.imag = imag;
    }

    public Complex add(Complex other) {
        // Task 4: complete the method
        Rational outReal = real.add(other.real);
        Rational outImag = imag.add(other.imag);
        return new Complex(outReal,outImag);
    }

    public Complex subtract(Complex other) {
        // Task 4: complete the method
        Rational outReal = real.subtract(other.real);
        Rational outImag = imag.subtract(other.imag);
        return new Complex(outReal,outImag);
    }

    public Complex multiply(Complex other) {
        // Task 4: complete the method
        // based on (a+bi)(c+di)=(ac-bd)+(bc+ad)i
        Rational outReal = (real.multiply(other.real)).subtract(imag.multiply(other.imag));
        Rational outImag = (imag.multiply(other.real)).add(real.multiply(other.imag));
        return new Complex(outReal,outImag);
    }

    public Complex divide(Complex other) {
        // Task 4: complete the method
        // you may assume 'other' is never equal to '0+/-0i'.
        // based on (a+bi)/(c+di)=(ac+bd)/(c^2+d^2) +((bc-ad)/(c^2+d^2))i
        Rational outReal = ((real.multiply(other.real)).add(imag.multiply(other.imag))).divide(((other.real).multiply(other.real)).add((other.imag).multiply(other.imag)));
        Rational outImag = ((imag.multiply(other.real)).subtract(real.multiply(other.imag))).divide(((other.real).multiply(other.real)).add((other.imag).multiply(other.imag)));
        return new Complex(outReal,outImag);
    }

    public void simplify() {
        real.simplify();
        imag.simplify();
        // Task 4L complete the method
    }

    public String toString() {
        // Task 4: complete the method
        return "("+real+","+imag+")";
    }

    // ==================================

} // OOP_A2-2 Made by ZHANG Wengyu(21098431d)
