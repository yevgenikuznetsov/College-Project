
/**
 * Yevgeni kuznetsov
 * id :319498580
 */
import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class SearchGui {
	private TextField search = new TextField();
	private Button buttonSearch;

	public SearchGui() {

		HBox.setHgrow(search, Priority.ALWAYS);
		search.setPromptText("Search...");
		search.setFocusTraversable(false);

		buttonSearch = new Button("Go!");
	}

	public TextField getSearch() {
		return search;
	}

	public void setSearch(TextField search) {
		this.search = search;
	}

	public Button getButtonSearch() {
		return buttonSearch;
	}

	public void setButtonSearch(Button buttonSearch) {
		this.buttonSearch = buttonSearch;
	}

	// creat Hbox
	public HBox createLayout() {
		HBox layout = new HBox(ViewConf.space);

		layout.getChildren().addAll(getSearch(), getButtonSearch());
		layout.setAlignment(Pos.CENTER);
		layout.setPadding(new Insets(ViewConf.space));

		return layout;
	}

	// search instruments in array
	public ArrayList<MusicalInstrument> getBySearch(String searchString, ArrayList<MusicalInstrument> allInstruments) {
		ArrayList<MusicalInstrument> filteredArray = new ArrayList<MusicalInstrument>();

		if (searchString == null || searchString.equals(""))
			return allInstruments;
		else {
			for (MusicalInstrument musicalInstrument : allInstruments) {
				if (musicalInstrument.toString().contains(searchString)) {
					filteredArray.add(musicalInstrument);
				}
			}
		}

		return filteredArray;
	}

	// find instrumets in array and show his index
	public int indexOfInstruments(ArrayList<MusicalInstrument> instruments) {
		int counter = 0;
		boolean find;
		int index = 0;
		for (int i = 0; i < instruments.size(); i++) {
			find = instruments.get(i).toString().contains(getSearch().getText());

			if (find) {
				index = counter;
				break;
			} else if (counter == instruments.size() - 1) {
				index = -1;
				break;
			} else
				counter++;
		}
		return index;
	}
}
