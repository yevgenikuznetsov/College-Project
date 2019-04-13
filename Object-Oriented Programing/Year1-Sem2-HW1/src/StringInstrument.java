import java.util.Scanner;

public class StringInstrument extends MusicalInstruments {
	private int numberOfStrings;
	
	/* default constructor */
	public StringInstrument(double price ,String nameOfManufacturerCompany ,int numberOfStrings) {
		super (price,nameOfManufacturerCompany);
		setNumberOfStrings(numberOfStrings);
	}
	
	/* scanner constructor */
	public StringInstrument(Scanner scanner) {
		super(scanner);
		
		setNumberOfStrings(Integer.parseInt(scanner.next()));	
	}

	/* get string number method */
	public int getNumberOfStrings() {
		return numberOfStrings;
	}

	/* set string number method */ 
	public void setNumberOfStrings(int numberOfStrings) throws IllegalArgumentException {
		if (numberOfStrings >= 0) {
			this.numberOfStrings = numberOfStrings;
		}
		else throw new IllegalArgumentException("Number of strings must be positive number!");
	}
	
	/* to string method */
	@Override
	public String toString() {
		return super.toString() + String.format( ", Number of strings:   %d|", getNumberOfStrings());
	}

	/* equals method */
	@Override
	public boolean equals(Object obj) {
		// check object type and compare musical instrument variables
		if (obj instanceof StringInstrument && super.equals(obj)) {
			// compare between numberOfStrings variable
			return ((StringInstrument) obj).getNumberOfStrings() == numberOfStrings;
		}
		
		return false;
	}
}
