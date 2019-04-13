
/**
 * Yevgeni kuznetsov
 * id :319498580
 */
import java.util.ArrayList;

import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class StringInstrumentsGrid {
	private final Text numberString = new Text("Number of Strings:");
	private final Text isFretless = new Text("Fretless:");
	private final Text guitarTypeText = new Text("Guitar Type:");
	private final String[] guitarTypeForCombo = { "Classic", "Acoustic", "Electric" };

	private TextField numStringInput = new TextField();
	private CheckBox fretless = new CheckBox();
	private ComboBox<String> guitarType = new ComboBox<>();

	public Text getGuitarTypeText() {
		return guitarTypeText;
	}

	public CheckBox getFretless() {
		return fretless;
	}

	public TextField getNumStringInput() {
		return numStringInput;
	}

	public Text getNumberString() {
		return numberString;
	}

	public Text getIsFretless() {
		return isFretless;
	}

	public ComboBox<String> getGuitarType() {
		return guitarType;
	}

	public void setGuitarType(ComboBox<String> guitarType) {
		this.guitarType = guitarType;
	}

	// creat grid panel for strings instruments
	public ArrayList<Node> getStringGrid(String instrument) {
		ArrayList<Node> nodeToAdd = new ArrayList<>();
		getNumStringInput().setText("");

		GridPane.setConstraints(numberString, 0, 2);
		nodeToAdd.add(numberString);

		GridPane.setConstraints(numStringInput, 1, 2);
		nodeToAdd.add(numStringInput);

		switch (instrument) {
		case "Guitar":
			guitarType.getItems().removeAll(guitarTypeForCombo);

			numStringInput.setPromptText("Ex: 6");
			guitarType.setPromptText("type");

			guitarType.getItems().addAll(guitarTypeForCombo);

			GridPane.setConstraints(guitarTypeText, 0, 3);
			GridPane.setConstraints(guitarType, 1, 3);

			nodeToAdd.add(guitarType);
			nodeToAdd.add(guitarTypeText);

			break;

		case "Bass":
			numStringInput.setPromptText("Ex: 4");
			fretless.setSelected(false);

			GridPane.setConstraints(isFretless, 0, 3);
			GridPane.setConstraints(fretless, 1, 3);
			nodeToAdd.add(isFretless);
			nodeToAdd.add(fretless);

			break;

		default:
			break;
		}

		return nodeToAdd;
	}

	// creat new stirings instruments
	public MusicalInstrument addStringInstrument(String stringBrand, String price, String instruments) {

		if (getNumStringInput().getText().isEmpty()) {
			AddInstruments.printAlert("The number of string is empty");
			return null;
		}

		switch (instruments) {
		case "Guitar":
			if (getGuitarType().getValue() == null) {
				AddInstruments.printAlert("The type is empty");
				return null;
			}
			return new Guitar(stringBrand, Double.valueOf(price), Integer.valueOf(getNumStringInput().getText()),
					getGuitarType().getValue());

		case "Bass":
			return new Bass(stringBrand, Double.valueOf(price), Integer.valueOf(getNumStringInput().getText()),
					Boolean.valueOf(getFretless().getText()));

		default:
			return null;
		}
	}

}
