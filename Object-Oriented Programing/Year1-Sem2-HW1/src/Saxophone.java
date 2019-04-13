import java.util.InputMismatchException;
import java.util.Scanner;

public class Saxophone extends WindInstruments {

	/* default constructor */
	public Saxophone(double price, String nameOfManufacturerCompany, String theMaterialProduced) {
		super(price, nameOfManufacturerCompany, theMaterialProduced);
	}
	
	/* scanner constructor */
	public Saxophone(Scanner scanner) {
		super(scanner);
		checkSaxophoneType();
	}
	
	/* toString method */
	@Override
	public String toString() {
		return super.toString();
	}
	
	/* equals method */
	@Override
	public boolean equals(Object obj) {
		// check object type and WindInstruments variables
		return (obj instanceof Saxophone && super.equals(obj));
	}

	/* check that the material is metal */
	private void checkSaxophoneType() throws InputMismatchException {
		if (!(getTheMaterialProduced().equals(WindInstrumentMaterial.Metal))) { 
			throw new IllegalArgumentException("The material sexophone produced is not appropriate");
		}
	}
}
