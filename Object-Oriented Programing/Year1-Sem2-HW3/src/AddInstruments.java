
/**
 * Yevgeni kuznetsov
 * id :319498580
 */
import java.util.InputMismatchException;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AddInstruments extends Stage {

	private final Text brand = new Text("Brand:");
	private final Text price = new Text("Price:");
	private final String[] typeInstruments = { "Guitar", "Bass", "Flute", "Saxophone" };
	private Scene scene;
	private ComboBox<String> allOptionOfInstruments;
	private GridPane gridPane = new GridPane();
	private TextField Brand = new TextField();
	private TextField Price = new TextField();
	private Button add = new Button("Add");
	private VBox secondpanel = new VBox(ViewConf.space);

	PanelGui panel;

	WindInstrumentsGrid grid = new WindInstrumentsGrid();
	StringInstrumentsGrid newString = new StringInstrumentsGrid();

	public AddInstruments(PanelGui panel) {

		allOptionOfInstruments = new ComboBox<>();
		allOptionOfInstruments.getItems().addAll(typeInstruments);
		allOptionOfInstruments.setPromptText("Choose Instruments Type Here");

		secondpanel.getChildren().addAll(allOptionOfInstruments);
		secondpanel.setAlignment(Pos.CENTER);

		scene = new Scene(secondpanel, ViewConf.widthsecond, ViewConf.Lengthsecond);

		allOptionOfInstruments.setOnAction(e -> {
			secondpanel.getChildren().removeAll(gridPane, add);
			clearTextFild();
			updateGridPane();
			secondpanel.getChildren().addAll(gridPane, add);

		});

		this.panel = panel;

		getAdd().setOnAction(e -> {
			createNewInstrument();

		});

		this.setTitle("Afeka Instruments - Add New Instrument");
		this.setScene(scene);
		this.show();

	}

	public VBox getSecondpanel() {
		return secondpanel;
	}

	public void setSecondpanel(VBox secondpanel) {
		this.secondpanel = secondpanel;
	}

	public TextField getBrand() {
		return Brand;
	}

	public void setBrand(TextField brand) {
		Brand = brand;
	}

	public TextField getPrice() {
		return Price;
	}

	public void setPrice(TextField price) {
		Price = price;
	}

	public Button getAdd() {
		return add;
	}

	public void setAdd(Button add) {
		this.add = add;
	}

	public ComboBox<String> getAllOptionOfInstruments() {
		return allOptionOfInstruments;
	}

	public void setAllOptionOfInstruments(ComboBox<String> allOptionOfInstruments) {
		this.allOptionOfInstruments = allOptionOfInstruments;
	}

	public GridPane getGridPane() {
		return gridPane;
	}

	public void setGridPane(GridPane gridPane) {
		this.gridPane = gridPane;
	}

	public void updateGridPane() {
		gridPane.getChildren().clear();

		gridPane.setVgap(15);
		gridPane.setHgap(15);
		gridPane.setAlignment(Pos.CENTER);

		GridPane.setConstraints(brand, 0, 0);
		GridPane.setConstraints(getBrand(), 1, 0);

		GridPane.setConstraints(price, 0, 1);
		GridPane.setConstraints(getPrice(), 1, 1);

		gridPane.getChildren().addAll(brand, getBrand(), price, getPrice());

		switch (allOptionOfInstruments.getValue()) {

		case "Guitar":
			getBrand().setPromptText("Ex: Gibson");
			getPrice().setPromptText("Ex: 7500");

			gridPane.getChildren().addAll(newString.getStringGrid("Guitar"));
			break;

		case "Bass":
			getBrand().setPromptText("Ex: Fender Jazz");
			getPrice().setPromptText("Ex: 7500");

			gridPane.getChildren().addAll(newString.getStringGrid("Bass"));
			break;

		case "Flute":
			getBrand().setPromptText("Ex: Levit");
			getPrice().setPromptText("Ex: 300");

			gridPane.getChildren().addAll(grid.getWindGrid());

			break;
		case "Saxophone":
			getBrand().setPromptText("Ex: Jupiter ");
			getPrice().setPromptText("Ex: 5490 ");

		default:

			break;
		}
	}

	// creat new instruments
	public void createNewInstrument() {
		if (getBrand().getText().isEmpty()) {
			printAlert("The brand is empty");
			return;
		} else if (getPrice().getText().isEmpty()) {
			printAlert("The price is empty");
			return;
		}

		MusicalInstrument instrument = null;

		try {
			if (allOptionOfInstruments.getValue() == "Flute")
				instrument = grid.addInstrumentLogic(getBrand().getText(), getPrice().getText());

			else if (allOptionOfInstruments.getValue() == "Saxophone")
				instrument = creatSaxophone();

			else
				instrument = newString.addStringInstrument(getBrand().getText(), getPrice().getText(),
						allOptionOfInstruments.getValue());

		} catch (InputMismatchException | IllegalArgumentException e) {
			printAlert(e.getMessage());
		}

		if (instrument == null) {
			return;
		} else

		panel.setIndex(0);
		panel.getInstrumentsToShow().add(instrument);
		panel.showInstrument();

		this.close();
	}

	// creat new Saxophone
	public MusicalInstrument creatSaxophone() {
		Saxophone newsaxophone = new Saxophone(getBrand().getText(), Double.valueOf(getPrice().getText()));

		return newsaxophone;
	}

	// print alert
	public static void printAlert(String string) {
		Alert emptyText = new Alert(AlertType.ERROR);
		emptyText.setTitle("Error");
		emptyText.setContentText(string);
		emptyText.showAndWait();
	}

	// clear the text fild
	public void clearTextFild() {
		getBrand().setText("");
		getPrice().setText("");
	}
}
