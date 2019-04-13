import java.util.ArrayList;

import javafx.scene.layout.FlowPane;

public class Decorator {

	public static void decorator(FlowPane addButton, boolean update, ArrayList<CommandButton> collactionButton) {

		if (update) {
			for (int i = 0; i < collactionButton.size(); i++) {
				addButton.getChildren().add(collactionButton.get(i));
			}
		} else {
			for (int i = 0; i < collactionButton.size(); i++) {
				if (!collactionButton.get(i).toUpdate) {
					addButton.getChildren().add(collactionButton.get(i));
				}
			}
		}

	}

}
