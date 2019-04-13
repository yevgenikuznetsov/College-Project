import java.util.Scanner;

//name : yevgeni kuznetsov
//id: 319498580

public class Saxophone extends WindInstrument {
	public static final int METAL = 1;

	public Saxophone(String brand, Number price) {
		super(brand, price, WIND_INSTRUMENT_MATERIAL[METAL]);
	}

	public Saxophone(Scanner scanner) {
		super(scanner);

		if (!getMaterial().equals(WIND_INSTRUMENT_MATERIAL[METAL]))
			throw new IllegalArgumentException("Illegal Saxophone material: " + getMaterial());
	}

	@Override
	public boolean equals(Object o) {
		if (!super.equals(o))
			return false;

		return o instanceof Saxophone;
	}

	@Override
	protected Saxophone clone() throws CloneNotSupportedException {
		return new Saxophone(getBrand(), getPrice());
	}
}
