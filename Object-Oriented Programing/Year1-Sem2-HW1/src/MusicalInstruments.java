import java.util.Scanner;

public class MusicalInstruments {
	
	private double price;
	private String nameOfManufacturerCompany;

	/* default constructor */
	public MusicalInstruments(double price , String nameOfManufacturerCompany) {
		setNameOfManufacturercompany(nameOfManufacturerCompany);
		setPrice(price);
	}

	/* scanner constructor */
	public MusicalInstruments(Scanner scanner) {
		setPrice(Double.parseDouble(scanner.next()));
		setNameOfManufacturercompany(scanner.next());
	}

	/* get price method */
	public double getPrice() {
		return price;
	}
	
	/* set price method */
	public void setPrice(Double price)throws IllegalArgumentException {
		if (price > 0) this.price = price;
		else throw new IllegalArgumentException("Price must be positive number!");
	}
	
	/* get company name method */
	public String getNameOfManufacturercompany() {
		return nameOfManufacturerCompany;
	}
	
	/* set company name method */
	public void setNameOfManufacturercompany(String nameOfManufacturercompany) {
		this.nameOfManufacturerCompany = nameOfManufacturercompany;
	}

	/* toString method */
	public String toString() {
		return String.format("%-9s%-9s| Price: %8.2f", getNameOfManufacturercompany(), getClass().getCanonicalName(), getPrice());
	}

	/* equals method */
	public boolean equals(Object obj) {
		// check object type
		if (obj instanceof MusicalInstruments) {
			MusicalInstruments objAsInstrument = (MusicalInstruments) obj;
			
			// compare musical instruments fields
			return (objAsInstrument.getPrice() == price &&
					objAsInstrument.getNameOfManufacturercompany().equals(nameOfManufacturerCompany));
		}
		
		return false;
	}
}
