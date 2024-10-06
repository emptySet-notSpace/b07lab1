package lab3;
import java.util.Objects;

public class ComplexNumber extends SpecialNumber implements Comparable<ComplexNumber>{
	private double real;
	private double imaginary;
	
	public ComplexNumber(double real, double imaginary) {
		this.real = real;
		this.imaginary = imaginary;
	}
	
	@Override
	public ComplexNumber add(SpecialNumber other) throws Lab3Exception {
		// the argument being added is an instance of ComplexNumber
		// Otherwise, a Lab3Exception should be thrown with the message “Cannot add an incompatible type”
		if (!(other instanceof ComplexNumber)) {
			throw new Lab3Exception("Cannot add an incompatible type");
		}
		ComplexNumber otherComplex = (ComplexNumber) other;
		return new ComplexNumber(this.real + otherComplex.real, this.imaginary + otherComplex.imaginary);
	}
	
	public ComplexNumber divideByInt(int num) throws Lab3Exception {
		// When implementing divideByInt, you need to make sure that the argument is not
		// zero. Otherwise, a Lab3Exception should be thrown with the message “Cannot
		// divide by zero”
		if (num == 0) {
			throw new Lab3Exception("Cannot devide by zero");
		}
		return new ComplexNumber(this.real/num, this.imaginary/num);
	}
	
	/**
	* This method compares two ComplexNumber objects
	* @param anotherComplexNumber the complex number to be compared
	* @return -1 if anotherComplexNumber is less than this ComplexNumber, 0 if they are
	* equal, and 1 otherwise
	*/
	public int compareTo(ComplexNumber that) {
		//i.e. !𝑟𝑒𝑎𝑙 ! + 𝑖𝑚𝑎𝑔𝑖𝑛𝑎𝑟𝑦 !
		// Complex numbers are to be compared using their magnitudes
		double thisMag = Math.sqrt(this.real * this.real + this.imaginary * this.imaginary);
		double otherMag = Math.sqrt(that.real * that.real + that.imaginary * that.imaginary);
		return Double.compare(thisMag, otherMag);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		ComplexNumber ob = (ComplexNumber)obj;
		return Double.compare(this.real, ob.real) == 0 && Double.compare(this.imaginary, ob.imaginary) == 0;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(real, imaginary);
	}
	
}
