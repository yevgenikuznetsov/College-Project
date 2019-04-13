
/**
 * Yevgeni kuznetsov
 * id :319498580
 */
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class ButtomLow {

	private Button add;
	private Button delete;
	private Button clear;

	public ButtomLow() {
		add = new Button("Add");
		delete = new Button("Delete");
		clear = new Button("Clear");
	}

	public Button getAdd() {
		return add;
	}

	public void setAdd(Button add) {
		this.add = add;
	}

	public Button getDelete() {
		return delete;
	}

	public void setDelete(Button delete) {
		this.delete = delete;
	}

	public Button getClear() {
		return clear;
	}

	public void setClear(Button clear) {
		this.clear = clear;
	}

	// create a Vbox
	public VBox createlowLayers() {
		VBox lowLayers = new VBox(ViewConf.space);
		lowLayers.setPadding(new Insets(ViewConf.space));
		HBox function = new HBox(ViewConf.space);
		function.getChildren().addAll(getAdd(), getDelete(), getClear());
		function.setAlignment(Pos.CENTER);

		lowLayers.getChildren().addAll(function, clockAndDate());

		return lowLayers;

	}

	// create clock and text
	public Text clockAndDate() {
		Text sentence = new Text();
		Timeline currentTime = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
			sentence.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
					+ " Afeka Instrumnets Music Store $$$ ON SALE!!! $$$ Guitars, Basses, Flutes, Saxophones and more!");
		}));

		sentence.setFill(Color.RED);
		sentence.setFont(Font.font("Ariel", FontWeight.BOLD, ViewConf.size));

		currentTime.setCycleCount(Animation.INDEFINITE);
		currentTime.play();
		animationSentens(sentence);

		return sentence;
	}

	// animation the text with clock
	public void animationSentens(Text text) {
		Timeline timeline;

		Duration startDuration = Duration.ZERO;
		Duration endDuration = Duration.seconds(ViewConf.durationTime);
		KeyValue startValue = new KeyValue(text.translateXProperty(), -(ViewConf.width));
		KeyValue endKeyValue = new KeyValue(text.translateXProperty(), ViewConf.width);

		KeyFrame start = new KeyFrame(startDuration, startValue);
		KeyFrame end = new KeyFrame(endDuration, endKeyValue);

		timeline = new Timeline(start, end);
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.setAutoReverse(true);
		timeline.play();

		text.setOnMouseEntered(e -> timeline.pause());
		text.setOnMouseExited(e -> timeline.play());
	}

}
