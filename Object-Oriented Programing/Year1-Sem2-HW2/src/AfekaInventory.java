import java.util.ArrayList;
import java.util.Comparator;

//name : yevgeni kuznetsov
//id: 319498580

public class AfekaInventory<T extends MusicalInstrument> implements inventoryManagement<T> {

	private ArrayList<T> inventory;
	private Double totalPrice;
	boolean isSorted;

	public AfekaInventory() {
		inventory = new ArrayList<>();
		setTotalPrice((double) 0);
		setSorted(false);
	}
	
	public ArrayList<T> getInventory() {
		return inventory;
	}

	public void setInventory(ArrayList<T> inventory) {
		this.inventory = inventory;
	}

	public double getTotalPrice() {
		if (totalPrice == null)
			return 0;
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	public void updateTotalPrice(Number price) {
		this.totalPrice = totalPrice(getTotalPrice(), price);
	}

	public boolean isSorted() {
		return isSorted;
	}

	public void setSorted(boolean isSorted) {
		this.isSorted = isSorted;
	}

	//add to inventory array only string instruments
	@Override
	public void addAllStringInstrument(ArrayList<? extends MusicalInstrument> copiesOfIt,
			ArrayList<? super MusicalInstrument> copyToItOnlyStringIns) {
		for (int i = 0; i < copiesOfIt.size(); i++) {
			if (copiesOfIt.get(i) instanceof StringInstrument) {
				addInstrument(copyToItOnlyStringIns, (MusicalInstrument) copiesOfIt.get(i));
			}
		}
	}

	//add to inventory array only wind instruments
	@Override
	public void addAllWindInstruments(ArrayList<? extends MusicalInstrument> copiesOfIt,
			ArrayList<? super MusicalInstrument> copyToItOnlyWindIns) {
		for (int i = 0; i < copiesOfIt.size(); i++) {
			if (copiesOfIt.get(i) instanceof WindInstrument) {
				addInstrument(copyToItOnlyWindIns, (MusicalInstrument) copiesOfIt.get(i));
			}
		}
	}

	//Sorting musical instruments according to company name and price
	@Override
	public void sortByBrandAndPrice(ArrayList<T> toSort) {
		toSort.sort(new Comparator<T>() {
			public int compare(T t1, T t2) {
				return ((MusicalInstrument) t1).compareTo((MusicalInstrument) t2);
			}
		});
		
		setSorted(true);
	}

	//Binary search on musical instruments according to company name and price
	@Override
	public int binnarySearchByBrandAndPrice(ArrayList<T> Instruments, String brand, Number price) {
		int brandIndex = -1;
		if (isSorted) {
			int low = 0;
			int high = Instruments.size() - 1;
			int mid;

			while (high >= low) {
				mid = (low + high) / 2;

				int compareValuesBrand = Instruments.get(mid).getBrand().compareTo(brand);
				if (compareValuesBrand < 0) {
					low = mid + 1;
				} else if (compareValuesBrand > 0) {
					high = mid - 1;
				} else {
					brandIndex = mid; //If one musical instrument is found with an exclusive company name
					double compareValuesPrice = Instruments.get(mid).getPrice().doubleValue();
					if (compareValuesPrice == price.doubleValue()) {
						return mid;
					} else if (compareValuesPrice < price.doubleValue()) {
						low = mid + 1;
					} else {
						high = mid - 1;
					}
				}
			}
		}
		
		return brandIndex;
	}

	// add instruments in inventory array
	@Override
	public void addInstrument(ArrayList<? super MusicalInstrument> instrumentsArray, MusicalInstrument instrument) {
		instrumentsArray.add(instrument);
		updateTotalPrice(instrument.getPrice().doubleValue());
		setSorted(false);
	}

	// Delete a musical instrument from the inventory array
	@Override
	public boolean removeInstrument(ArrayList<T> toErase, MusicalInstrument instrument) {
		updateTotalPrice(-(instrument.getPrice().doubleValue()));
		return toErase.remove(instrument);
	}
	
	// delete all instruments in inventory array
	@Override
	public boolean removeAll(ArrayList<T> toEraseAll) {
		setTotalPrice((double) 0);
		setSorted(false);
		return toEraseAll.removeAll(toEraseAll);
	}
	
	// calculate a total price of inventory array
	public <E extends Number> Double totalPrice(E price1, E price2) {
		return (price1.doubleValue() + price2.doubleValue());
	}

	//to string
	public String toString() {
		System.out.println("\n-------------------------------------------------------------------------\n"
				+ "AFEKA MUSICAL INSTRUMENT INVENTORY \n"
				+ "-------------------------------------------------------------------------");
		if (!inventory.isEmpty()) {
			for (int i = 0; i < inventory.size(); i++) {
				System.out.println(inventory.get(i));
			}
		} else
			System.out.println("There Is No Instruments To Show\n");

		return String.format("\nTotal price: %-12.2f  Sorted: %-8s\n", getTotalPrice(), isSorted());
	}

}
