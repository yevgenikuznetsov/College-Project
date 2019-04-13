import java.util.InputMismatchException;
import java.util.Scanner;

public class Guitar extends StringInstrument {
	String typeOfGuitar;
	
	/* default constructor */
	public Guitar(double price, String nameOfManufacturerCompany, int numberOfStrings, String typeOfGuitar) {
		super(price, nameOfManufacturerCompany, numberOfStrings);
		setTypeOfGuitar(typeOfGuitar);
	}
	
	/* scanner constructor */
	public Guitar(Scanner scanner) {
		super(scanner);
		setTypeOfGuitar(scanner.next());
	}

	/* get guitar type method */
	public String getTypeOfGuitar() {
		return typeOfGuitar;
	}

	/* set guitar type method */
	public void setTypeOfGuitar(String typeOfGuitar) throws InputMismatchException {
		this.typeOfGuitar = typeOfGuitar;
		
		if (!(typeOfGuitar.equals(GuitarType.Electric) || typeOfGuitar.equals(GuitarType.Acoustic) || typeOfGuitar.equals(GuitarType.Classic))) {
			throw new IllegalArgumentException("The type of guitar is not correct!");
		}
		
		if (typeOfGuitar.equals(GuitarType.Electric)) {
			if ((getNumberOfStrings() < 6 || getNumberOfStrings() > 8))
				throw new IllegalArgumentException("Electric guitar number of strings is a number between 6 and 8");
		}
		else {
			if (getNumberOfStrings() != 6) {
				throw new IllegalArgumentException(getTypeOfGuitar() + " " + "Guitars have 6 strings, not 2");
			}
		}
	}
	
	/* toString method */
	@Override
	public String toString () {
		return super.toString() + String.format("  Type: %s", getTypeOfGuitar());
	}

	/* equals method */
	@Override
	public boolean equals(Object obj) {
		// check object type and compare string instruments variables
		if (obj instanceof Guitar && super.equals(obj)) {
			return ((Guitar) obj).getTypeOfGuitar() == typeOfGuitar;
		}
		
		return false;
}
 }