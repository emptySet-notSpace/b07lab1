/**
 * This class is rational number, which is a numerator and a denominator.
 * It has method add, divide, equals, compare hashcode and compare
 */

package lab3;
import java.util.List;
import java.util.Objects;

public class RationalNumber extends SpecialNumber implements Comparable<RationalNumber> {
	//a. It has two fields of type int named numerator and denominator
	//b. It has a constructor that takes two arguments of type int and initializes numerator and
	//denominator accordingly. If the argument corresponding to the denominator is zero, the
	//constructor should throw a Lab3Exception with the message “Denominator cannot be
	//zero”
	
	private int numerator;
	private int denominator;
	
	private int cd(int a, int b) {
		if (b == 0) {
			return Math.abs(a);
		}
		return cd(b, a%b);
	}
	
	//simplify rational number
	public RationalNumber(int numerator, int denominator) throws Lab3Exception{
		if (denominator == 0) {
			throw new Lab3Exception("Denominator cannot be zero");
		}
		int cd = cd(numerator, denominator);
		this.numerator = numerator/cd;
		this.denominator = denominator/cd;
		
		if (denominator < 0) {
			numerator = - numerator;
			denominator = -denominator;
		}
		
		
	}
	
	// When implementing add, you need to make sure that the argument being added
	// is an instance of RationalNumber. Otherwise, a Lab3Exception should be thrown
	// with the message “Cannot add an incompatible type
	@Override
	public RationalNumber add(SpecialNumber num) throws Lab3Exception {
		if (!(num instanceof RationalNumber)) {
			throw new Lab3Exception("Cannot add an incompatible type");
		}
		RationalNumber otherRational = (RationalNumber) num;
		int commonDenominator = this.denominator * otherRational.denominator;
		int newNumerator = (this.numerator * otherRational.denominator) + (this.denominator * otherRational.numerator);
		return new RationalNumber(newNumerator, commonDenominator);
	}
	
	//When implementing divideByInt, you need to make sure that the argument is not
	//zero. Otherwise, a Lab3Exception should be thrown with the message “Cannot
	//divide by zero”
	public RationalNumber divideByInt(int divisor) throws Lab3Exception {
	    if (divisor == 0) {
	        throw new Lab3Exception("Cannot divide by zero");
	    }
	    return new RationalNumber(this.numerator, this.denominator * divisor);
	}
	
	@Override
	public boolean equals(Object obj) {
	    if (this == obj) {
	    	return true;
	    }
	    if (obj == null) {
	        return false;
	    }
	    if (getClass() != obj.getClass()) {
	        return false;
	    }
	    RationalNumber other = (RationalNumber) obj;

	    //cross multiplication
	    return this.numerator * other.denominator == this.denominator * other.numerator;
	}

	@Override
	public int hashCode() {
	    return Objects.hash(numerator * denominator);
	}

    public int compareTo(RationalNumber other) {
        // Cross-multiply
        int lh = this.numerator * other.denominator;
        int rh = this.denominator * other.numerator;
        return Integer.compare(lh, rh);
    }
    
    public String toString() {
        return numerator + "/" + denominator;
    }
}
