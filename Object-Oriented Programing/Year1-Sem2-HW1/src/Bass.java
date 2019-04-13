import java.util.InputMismatchException;
import java.util.Scanner;

public class Bass extends StringInstrument {
	boolean fretless;

	/* default constructor */
	public Bass(double price, String nameOfManufacturerCompany , int numberOfStrings , boolean fretless) {
		super(price , nameOfManufacturerCompany ,numberOfStrings);
		setFretless(fretless);
	}
	
	/* scanner constructor */
	public Bass(Scanner scanner)throws InputMismatchException {
		super(scanner);
		
		String isFretless = scanner.next();
		// check if the value is TRUE
		if (isFretless.equals("True")) {
			setFretless(true);
		}
		// check if the value is FALSE
		else if (isFretless.equals("False")) {
			setFretless(false);
		}
		else throw new InputMismatchException("Whether a bass is fretless or not is boolean, any other string than \"True\" or \"Flase\" is not acceptable");
		
		checkStringNumber();
	}

	/* get isFretless method */
	public boolean isFretless() {
		return fretless;
	}

	/* set isFretles method */
	public void setFretless(boolean fretless) {
		this.fretless = fretless;
	}
	
	/* toString method */
	@Override
	public String toString() {
		return super.toString() + String.format("  Fretless: %s", fretless ? "Yes" : "No");
	}
	
	/* equals method */
	@Override
	public boolean equals(Object obj) {
		// check object type and stringInstrumet variables
		if (obj instanceof Bass && super.equals(obj)) {
			// compare isFretless variable
			return ((Bass) obj).isFretless() == fretless;
		}
		
		return false;
	}

	/* check string number */
	private void checkStringNumber() throws IllegalArgumentException {
		// legal string is between 4 to 6
		if (getNumberOfStrings() < 4 || getNumberOfStrings() > 6) { 
			throw new IllegalArgumentException("Bass number of strings is a number between 4 and 6");
		}
	}
}
