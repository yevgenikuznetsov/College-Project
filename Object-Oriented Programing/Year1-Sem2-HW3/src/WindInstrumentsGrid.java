
/**
 * Yevgeni kuznetsov
 * id :319498580
 */
import java.util.ArrayList;

import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class WindInstrumentsGrid {

	private final Text typeMaterial = new Text("Material:");
	private final Text fluteType1 = new Text("Flute Type:");
	private final String[] fluteMaterial = { "Wood", "Metal", "Plastic" };
	private final String[] fluteType = { "Flute", "Recorder", "Bass" };

	private ComboBox<String> inputMaterial = new ComboBox<>();
	private ComboBox<String> inputType = new ComboBox<>();

	public ComboBox<String> getInputMaterial() {
		return inputMaterial;
	}

	public void setInputMaterial(ComboBox<String> inputMaterial) {
		this.inputMaterial = inputMaterial;
	}

	public ComboBox<String> getInputType() {
		return inputType;
	}

	public void setInputType(ComboBox<String> inputType) {
		this.inputType = inputType;
	}

	// creat grid windows for flute
	public ArrayList<Node> getWindGrid() {
		ArrayList<Node> nodeToAdd = new ArrayList<>();
		inputMaterial.getItems().removeAll(fluteMaterial);
		inputType.getItems().removeAll(fluteType);

		inputMaterial.setPromptText("Material");
		inputMaterial.getItems().addAll(fluteMaterial);

		GridPane.setConstraints(typeMaterial, 0, 2);
		nodeToAdd.add(typeMaterial);

		GridPane.setConstraints(inputMaterial, 1, 2);
		nodeToAdd.add(inputMaterial);

		inputType.setPromptText("Type");
		inputType.getItems().addAll(fluteType);

		GridPane.setConstraints(fluteType1, 0, 3);
		GridPane.setConstraints(inputType, 1, 3);

		nodeToAdd.add(fluteType1);
		nodeToAdd.add(inputType);

		return nodeToAdd;
	}

	// craet new flute
	public Flute addInstrumentLogic(String brand, String price) {
		if (getInputMaterial().getValue() == null) {
			AddInstruments.printAlert("The material is empty");
			return null;

		}

		if (getInputType().getValue() == null) {
			AddInstruments.printAlert("The type is empty");
			return null;
		}

		return new Flute(brand, Double.valueOf(price), getInputMaterial().getValue(), getInputType().getValue());
	}

}
