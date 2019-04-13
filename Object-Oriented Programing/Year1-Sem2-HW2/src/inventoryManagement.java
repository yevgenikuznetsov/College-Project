import java.util.ArrayList;

//name : yevgeni kuznetsov
//id: 319498580

public interface inventoryManagement<T extends MusicalInstrument> {

	void addAllStringInstrument(ArrayList<? extends MusicalInstrument> copiesOfIt,
			ArrayList<? super MusicalInstrument> copyToItOnlyStringIns);

	void addAllWindInstruments(ArrayList<? extends MusicalInstrument> copiesOfIt,
			ArrayList<? super MusicalInstrument> copyToItOnlyWindIns);

	void sortByBrandAndPrice(ArrayList<T> toSort);

	int binnarySearchByBrandAndPrice(ArrayList<T> Instruments, String brand, Number price);

	void addInstrument(ArrayList<? super MusicalInstrument> instrumentsArray, MusicalInstrument instrument);

	boolean removeInstrument(ArrayList<T> toErase, MusicalInstrument instrument);

	boolean removeAll(ArrayList<T> toEraseAll);

}
