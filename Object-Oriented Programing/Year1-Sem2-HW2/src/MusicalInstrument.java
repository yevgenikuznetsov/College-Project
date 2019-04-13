
import java.util.InputMismatchException;
import java.util.Scanner;

//name : yevgeni kuznetsov
//id: 319498580

public abstract class MusicalInstrument implements InstrumentFunc {
	private Number price;
	private String brand;

	public MusicalInstrument(String brand, Number price) {
		setBrand(brand);
		setPrice(price);
	}

	public MusicalInstrument(Scanner scanner) {
		double price = 0;
		String brand;

		try {
			price = scanner.nextDouble();
		} catch (InputMismatchException ex) {
			throw new InputMismatchException("Price not found!");
		}
		setPrice(price);
		scanner.nextLine();
		brand = scanner.nextLine();
		setBrand(brand);
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Number getPrice() {
		return price;
	}

	public void setPrice(Number price) {
		double check;
		if (price.doubleValue() > 0) {
			check = price.doubleValue() - price.intValue();
			if (check == 0) {
				this.price = new Integer(price.intValue());
			} else
				this.price = new Double(price.doubleValue());
		}

		else
			throw new InputMismatchException("Price must be a positive number!");

	}

	protected boolean isValidType(String[] typeArr, String material) {
		for (int i = 0; i < typeArr.length; i++) {
			if (material.equals(typeArr[i])) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || !(o instanceof MusicalInstrument))
			return false;

		MusicalInstrument otherInstrument = (MusicalInstrument) o;

		return getPrice().equals(otherInstrument.getPrice()) && getBrand().equals(otherInstrument.getBrand());
	}

	@Override
	public String toString() {
		if (getPrice() instanceof Integer)
			return String.format("%-8s %-9s| Price: %7d,", getBrand(), getClass().getCanonicalName(),
					getPrice().intValue());

		else
			return String.format("%-8s %-9s| Price: %7.1f,", getBrand(), getClass().getCanonicalName(),
					getPrice().doubleValue());
	}

	@Override
	public int compareTo(MusicalInstrument o) {
		int comperIntBrad = this.getBrand().compareTo(o.getBrand());

		if (comperIntBrad > 0)
			return 1;
		else if (comperIntBrad < 0)
			return -1;
		else {
			if (this.getPrice().doubleValue() > o.getPrice().doubleValue())
				return 1;
			else if (this.getPrice().doubleValue() < o.getPrice().doubleValue())
				return -1;
			else
				return 0;
		}
	}
}
