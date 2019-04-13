
/**
 * Yevgeni kuznetsov
 * id :319498580
 */
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

public class StackPaneWithButton extends StackPane {
	private Button button;

	// creat side button
	public StackPaneWithButton(String sight) {
		button = new Button(sight);
		button.setAlignment(Pos.CENTER);
		setPadding(new Insets(ViewConf.space));

		this.getChildren().addAll(button);
	}

	public Button getButton() {
		return button;
	}

	public void setButton(Button button) {
		this.button = button;
	}
}
