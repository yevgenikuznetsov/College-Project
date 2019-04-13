import java.util.InputMismatchException;
import java.util.Scanner;

public class Flute extends WindInstruments {
	private String typeOfFlutes;
	
	/* default constructor */
	public Flute(double price ,String nameOfManufacturerCompany, String theMaterialProduced, String typeOfFlutes) {
		super(price, nameOfManufacturerCompany , theMaterialProduced);
		setTypeOfFlutes(typeOfFlutes);
	}

	/* scanner constructor */
	public Flute(Scanner scanner) {
		super(scanner);
		setTypeOfFlutes(scanner.next());
	}

	/* get flutes type method */
	public String getTypeOfFlutes() {
		return typeOfFlutes;
	}

	/* set flutes type method */
	public void setTypeOfFlutes(String typeOfFlutes) throws InputMismatchException {
		// check is the type if acceptable (one of: bass, flute, recorder type) 
		if (typeOfFlutes.equals(FlutesType.bass) || typeOfFlutes.equals(FlutesType.Flute) || typeOfFlutes.equals(FlutesType.Recorder)) {
			this.typeOfFlutes = typeOfFlutes;
		}
		else throw new InputMismatchException("The type of flute is not correct!");
	}
		
	/* toString method */	
	@Override
	public String toString() {
		return super.toString() + String.format("  Type: %s", getTypeOfFlutes());
	}

	/* equals method */
	@Override
	public boolean equals(Object obj) {
		// check object type and wind Instrument variables
		if (obj instanceof Flute && super.equals(obj)) {
			return ((Flute) obj).getTypeOfFlutes() == typeOfFlutes;
		}
		
		return false;
	}
}
