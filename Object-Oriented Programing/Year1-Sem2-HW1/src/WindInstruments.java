import java.util.InputMismatchException;
import java.util.Scanner;

public class WindInstruments extends MusicalInstruments {
	private String theMaterialProduced;
	
	/* default constructor */ 
	public WindInstruments (double price , String nameOfManufacturerCompany , String theMaterialProduced) {
		super (price , nameOfManufacturerCompany);
		setTheMaterialProduced(theMaterialProduced);
	}
	
	/* scanner constructor */
	public WindInstruments (Scanner scanner) {
		super(scanner);
		setTheMaterialProduced(scanner.next());
	}
	
	/* get material method */
	public String getTheMaterialProduced() {
		return theMaterialProduced;
	}

	/* set material method */
	public void setTheMaterialProduced(String theMaterialProduced) throws InputMismatchException {
		// check if the material is one of acceptable (metal, plastic, wood) and set it
		if (theMaterialProduced.equals(WindInstrumentMaterial.Metal) || 
				theMaterialProduced.equals(WindInstrumentMaterial.Plastic) || 
				theMaterialProduced.equals(WindInstrumentMaterial.Wood)) {
			this.theMaterialProduced = theMaterialProduced;
		}
		else throw new IllegalArgumentException("The type of material not appropriate");
	}
	
	/* to string method */
	@Override
	public String toString() {
		return super.toString() + String.format( ", Made of:  %12s|", getTheMaterialProduced());
	}
	
	/* equals method */
	@Override
	public boolean equals(Object obj) {
		// check object type and compare musical instruments variables
		if (obj instanceof WindInstruments && super.equals(obj)) {
			// compare material
			return ((WindInstruments) obj).getTheMaterialProduced().equals(theMaterialProduced);
		}
		
		return false;
	}
}
