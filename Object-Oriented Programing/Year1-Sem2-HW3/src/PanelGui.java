
/**
 * Yevgeni kuznetsov
 * id :319498580
 */
import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class PanelGui {

	final private Label type;
	final private Label brand;
	final private Label price;

	private TextField inputType;
	private TextField inputBrand;
	private TextField inputPrice;
	private int index;
	private ArrayList<MusicalInstrument> instrumentsToShow;

	public PanelGui(ArrayList<MusicalInstrument> instruments) {
		type = new Label("Type:");
		inputType = new TextField();
		brand = new Label("Brand:");
		inputBrand = new TextField();
		price = new Label("Price:");
		inputPrice = new TextField();
		setIndex(0);

		instrumentsToShow = new ArrayList<MusicalInstrument>();
		instrumentsToShow.addAll(instruments);
	}

	public int getIndex() {
		return index;
	}

	public int setIndex(int index) {
		return this.index = index;
	}

	public Label getType() {
		return type;
	}

	public TextField getInputType() {
		return inputType;
	}

	public void setInputType(String inputType) {
		this.inputType.setText(inputType);
	}

	public Label getBrand() {
		return brand;
	}

	public TextField getInputBrand() {
		return inputBrand;
	}

	public void setInputBrand(String inputBrand) {
		this.inputBrand.setText(inputBrand);
	}

	public Label getPrice() {
		return price;
	}

	public TextField getInputPrice() {
		return inputPrice;
	}

	public void setInputPrice(Number number) {
		if (number == null)
			this.inputPrice.setText("");
		else
			this.inputPrice.setText(number.toString());
	}

	public ArrayList<MusicalInstrument> getInstrumentsToShow() {
		return instrumentsToShow;
	}

	public void setInstrumentsToShow(ArrayList<MusicalInstrument> instrumentsToShow) {
		this.instrumentsToShow = instrumentsToShow;
	}

	// creat grid panel for center lay
	public GridPane createLayoutCenter() {
		GridPane grid = new GridPane();
		grid.setVgap(ViewConf.Vgap);
		grid.setHgap(ViewConf.Hgap);

		GridPane.setConstraints(getType(), 0, 0);
		GridPane.setConstraints(getInputType(), 1, 0);
		GridPane.setConstraints(getBrand(), 0, 1);
		GridPane.setConstraints(getInputBrand(), 1, 1);
		GridPane.setConstraints(getPrice(), 0, 2);
		GridPane.setConstraints(getInputPrice(), 1, 2);

		grid.getChildren().addAll(type, inputType, brand, inputBrand, price, inputPrice);
		grid.setAlignment(Pos.CENTER);

		return grid;
	}

	// show instrument in grid center lay
	public void showInstrument() {
		if (!instrumentsToShow.isEmpty() && (!(index == -1))) {
			setInputType(instrumentsToShow.get(index).getClass().getName());
			setInputBrand(instrumentsToShow.get(index).getBrand());

			Number price = instrumentsToShow.get(index).getPrice();
			setInputPrice(price);
		}

		else {
			setInputType("");
			inputType.setPromptText("No items");

			setInputBrand("");
			inputBrand.setPromptText("No items");

			setInputPrice(null);
			inputPrice.setPromptText("No items");
		}
	}

	// update index in array
	public void updateIndex(String direct) {
		if (direct.equals("right")) {
			if (getIndex() == instrumentsToShow.size() - 1)
				setIndex(0);
			else
				setIndex(getIndex() + 1);
		}

		else {
			if (getIndex() == 0)
				setIndex(instrumentsToShow.size() - 1);
			else
				setIndex(getIndex() - 1);
		}
	}

	// delete instrumnets
	public void deleteInstruments() {
		instrumentsToShow.remove(getIndex());

		if (getIndex() == instrumentsToShow.size()) {
			setIndex(0);
		}
	}

	// delete all instrumnets
	public void deleteAllInstruments() {
		instrumentsToShow.removeAll(instrumentsToShow);
	}
}
