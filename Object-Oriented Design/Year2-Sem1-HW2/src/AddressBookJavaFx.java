
/* name: yevgeni kuznetsov  id: 319498580   */

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

public class AddressBookJavaFx extends Application implements FinalsForAddressbook {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// create max number of pane
		for (int i = 0; i < MAX_NUMBER_OF_OBJECT; i++) {

			AddressBookPane pane = AddressBookPane.gatInstance();
			if (pane != null) {
				Scene scene = new Scene(pane);
				scene.getStylesheets().add("styles.css");
				primaryStage.setTitle("AddressBook");
				primaryStage.setScene(scene);
				primaryStage.show();
				primaryStage.setAlwaysOnTop(true);
				primaryStage = new Stage();
			} else
				System.out.println("Singelton violation. Only 3 panes were created");

		}

	}
}

class AddressBookPane extends GridPane implements FinalsForAddressbook {
	private RandomAccessFile raf;
	private ArrayList<CommandButton> collactionButton = new ArrayList<CommandButton>();
	private FlowPane jpButton = new FlowPane();

	CareTaker careTaker = new CareTaker();
	Originator originator = new Originator();

	private static int number_of_objects = 0;
	// Text fields
	private TextField jtfName = new TextField();
	private TextField jtfStreet = new TextField();
	private TextField jtfCity = new TextField();
	private TextField jtfState = new TextField();
	private TextField jtfZip = new TextField();
	// Buttons
	private AddButton jbtAdd;
	private FirstButton jbtFirst;
	private NextButton jbtNext;
	private PreviousButton jbtPrevious;
	private LastButton jbtLast;
	private Redu jbtRedu;
	private Undo jbtUndo;

	public EventHandler<ActionEvent> ae = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent arg0) {
			((Command) arg0.getSource()).Execute();
		}
	};

	private AddressBookPane() {
		try {
			raf = new RandomAccessFile("address.dat", "rw");
		} catch (IOException ex) {
			System.out.print("Error: " + ex);
			System.exit(0);
		}
		jtfState.setAlignment(Pos.CENTER_LEFT);
		jtfState.setPrefWidth(25);
		jtfZip.setPrefWidth(60);
		jbtAdd = new AddButton(this, raf, careTaker, originator);
		jbtFirst = new FirstButton(this, raf);
		jbtNext = new NextButton(this, raf);
		jbtPrevious = new PreviousButton(this, raf);
		jbtLast = new LastButton(this, raf);
		jbtRedu = new Redu(this, raf, originator, careTaker);
		jbtUndo = new Undo(this, raf, originator, careTaker);

		collactionButton.add(jbtFirst);
		collactionButton.add(jbtNext);
		collactionButton.add(jbtPrevious);
		collactionButton.add(jbtLast);
		collactionButton.add(jbtAdd);
		collactionButton.add(jbtRedu);
		collactionButton.add(jbtUndo);

		Label state = new Label("State");
		Label zp = new Label("Zip");
		Label name = new Label("Name");
		Label street = new Label("Street");
		Label city = new Label("City");
		// Panel p1 for holding labels Name, Street, and City
		GridPane p1 = new GridPane();
		p1.add(name, 0, 0);
		p1.add(street, 0, 1);
		p1.add(city, 0, 2);
		p1.setAlignment(Pos.CENTER_LEFT);
		p1.setVgap(8);
		p1.setPadding(new Insets(0, 2, 0, 2));
		GridPane.setVgrow(name, Priority.ALWAYS);
		GridPane.setVgrow(street, Priority.ALWAYS);
		GridPane.setVgrow(city, Priority.ALWAYS);
		// City Row
		GridPane adP = new GridPane();
		adP.add(jtfCity, 0, 0);
		adP.add(state, 1, 0);
		adP.add(jtfState, 2, 0);
		adP.add(zp, 3, 0);
		adP.add(jtfZip, 4, 0);
		adP.setAlignment(Pos.CENTER_LEFT);
		GridPane.setHgrow(jtfCity, Priority.ALWAYS);
		GridPane.setVgrow(jtfCity, Priority.ALWAYS);
		GridPane.setVgrow(jtfState, Priority.ALWAYS);
		GridPane.setVgrow(jtfZip, Priority.ALWAYS);
		GridPane.setVgrow(state, Priority.ALWAYS);
		GridPane.setVgrow(zp, Priority.ALWAYS);
		// Panel p4 for holding jtfName, jtfStreet, and p3
		GridPane p4 = new GridPane();
		p4.add(jtfName, 0, 0);
		p4.add(jtfStreet, 0, 1);
		p4.add(adP, 0, 2);
		p4.setVgap(1);
		GridPane.setHgrow(jtfName, Priority.ALWAYS);
		GridPane.setHgrow(jtfStreet, Priority.ALWAYS);
		GridPane.setHgrow(adP, Priority.ALWAYS);
		GridPane.setVgrow(jtfName, Priority.ALWAYS);
		GridPane.setVgrow(jtfStreet, Priority.ALWAYS);
		GridPane.setVgrow(adP, Priority.ALWAYS);
		// Place p1 and p4 into jpAddress
		GridPane jpAddress = new GridPane();
		jpAddress.add(p1, 0, 0);
		jpAddress.add(p4, 1, 0);
		GridPane.setHgrow(p1, Priority.NEVER);
		GridPane.setHgrow(p4, Priority.ALWAYS);
		GridPane.setVgrow(p1, Priority.ALWAYS);
		GridPane.setVgrow(p4, Priority.ALWAYS);
		// Set the panel with line border
		jpAddress.setStyle("-fx-border-color: grey;" + " -fx-border-width: 1;" + " -fx-border-style: solid outside ;");
		// Add buttons to a panel
		// FlowPane jpButton = new FlowPane();
		jpButton.setHgap(5);

		jpButton.setAlignment(Pos.CENTER);
		GridPane.setVgrow(jpButton, Priority.NEVER);
		GridPane.setVgrow(jpAddress, Priority.ALWAYS);
		GridPane.setHgrow(jpButton, Priority.ALWAYS);
		GridPane.setHgrow(jpAddress, Priority.ALWAYS);
		// Add jpAddress and jpButton to the stage
		this.setVgap(5);
		this.add(jpAddress, 0, 0);
		this.add(jpButton, 0, 1);
		jbtAdd.setOnAction(ae);
		jbtFirst.setOnAction(ae);
		jbtNext.setOnAction(ae);
		jbtPrevious.setOnAction(ae);
		jbtLast.setOnAction(ae);
		jbtRedu.setOnAction(ae);
		jbtUndo.setOnAction(ae);
		jbtFirst.Execute();
	}

	public void actionHandled(ActionEvent e) {
		((Command) e.getSource()).Execute();
	}

	public void SetName(String text) {
		jtfName.setText(text);
	}

	public void SetStreet(String text) {
		jtfStreet.setText(text);
	}

	public void SetCity(String text) {
		jtfCity.setText(text);
	}

	public void SetState(String text) {
		jtfState.setText(text);
	}

	public void SetZip(String text) {
		jtfZip.setText(text);
	}

	public String GetName() {
		return jtfName.getText();
	}

	public String GetStreet() {
		return jtfStreet.getText();
	}

	public String GetCity() {
		return jtfCity.getText();
	}

	public String GetState() {
		return jtfState.getText();
	}

	public String GetZip() {
		return jtfZip.getText();
	}

	// get buttom to pane, the first pane have more button that other
	// use with decorator
	public static AddressBookPane gatInstance() {
		AddressBookPane newWindow = new AddressBookPane();
		number_of_objects++;

		if (number_of_objects == UPDATE_FILE) {
			Decorator.decorator(newWindow.jpButton, true, newWindow.collactionButton);
			return newWindow;
		} else if (number_of_objects <= MAX_NUMBER_OF_OBJECT) {
			Decorator.decorator(newWindow.jpButton, false, newWindow.collactionButton);
			return newWindow;
		} else
			return null;
	}

	public static void reduceNumberOfObjects() {
		number_of_objects--;
	}

	public static int getNumberOfObject() {
		return number_of_objects;
	}

	public static void resetNumberOfObject() {
		number_of_objects = 0;
	}

	// set all text field with nothing
	public void clearTextFields() {
		jtfName.setText("");
		jtfStreet.setText("");
		jtfCity.setText("");
		jtfState.setText("");
		jtfZip.setText("");
	}

}

interface Command {
	public void Execute();
}

class CommandButton extends Button implements Command, FinalsForAddressbook {
	public final static int NAME_SIZE = 32;
	public final static int STREET_SIZE = 32;
	public final static int CITY_SIZE = 20;
	public final static int STATE_SIZE = 2;
	public final static int ZIP_SIZE = 5;
	public final static int RECORD_SIZE = (NAME_SIZE + STREET_SIZE + CITY_SIZE + STATE_SIZE + ZIP_SIZE);
	protected AddressBookPane p;
	protected RandomAccessFile raf;
	protected boolean toUpdate;

	public CommandButton(AddressBookPane pane, RandomAccessFile r, boolean toUpdate) {
		super();
		p = pane;
		raf = r;
		this.toUpdate = toUpdate;
	}

	public void Execute() {
	}

	public boolean isToUpdate() {
		return toUpdate;
	}

	public void setToUpdate(boolean toUpdate) {
		this.toUpdate = toUpdate;
	}

	/** Write a record at the end of the file */
	public void writeAddress() {
		try {
			raf.seek(raf.length());
			FixedLengthStringIO.writeFixedLengthString(p.GetName(), NAME_SIZE, raf);
			FixedLengthStringIO.writeFixedLengthString(p.GetStreet(), STREET_SIZE, raf);
			FixedLengthStringIO.writeFixedLengthString(p.GetCity(), CITY_SIZE, raf);
			FixedLengthStringIO.writeFixedLengthString(p.GetState(), STATE_SIZE, raf);
			FixedLengthStringIO.writeFixedLengthString(p.GetZip(), ZIP_SIZE, raf);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/** Read a record at the specified position */
	public void readAddress(long position) throws IOException {
		raf.seek(position);
		String name = FixedLengthStringIO.readFixedLengthString(NAME_SIZE, raf);
		String street = FixedLengthStringIO.readFixedLengthString(STREET_SIZE, raf);
		String city = FixedLengthStringIO.readFixedLengthString(CITY_SIZE, raf);
		String state = FixedLengthStringIO.readFixedLengthString(STATE_SIZE, raf);
		String zip = FixedLengthStringIO.readFixedLengthString(ZIP_SIZE, raf);
		p.SetName(name);
		p.SetStreet(street);
		p.SetCity(city);
		p.SetState(state);
		p.SetZip(zip);
	}

	// initiation memento array
	public void showPreviousStatues(Originator originator, CareTaker careTaker) {
		try {
			for (int i = 0; i < raf.length(); i += RECORD_SIZE * 2) {
				raf.seek(i);
				String s = FixedLengthStringIO.readFixedLengthString(RECORD_SIZE, raf);
				originator.setState(s);
				careTaker.add(originator.saveStateToMemento());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

class AddButton extends CommandButton {
	Originator originator;
	CareTaker careTaker;

	public AddButton(AddressBookPane pane, RandomAccessFile r, CareTaker careTaker, Originator originator) {
		super(pane, r, true);
		this.setText("Add");
		this.careTaker = careTaker;
		this.originator = originator;
	}

	@Override
	public void Execute() {

		String addres;
		try {

			// check if we have text but and our memento is empty
			if (raf.length() > 0 && careTaker.isEmpty() == 0) {
				showPreviousStatues(originator, careTaker);
			}

			// write string from file
			writeAddress();
			raf.seek(raf.length() - 2 * RECORD_SIZE);
			addres = FixedLengthStringIO.readFixedLengthString(RECORD_SIZE, raf);

			// add string from the file to memnto array
			originator.setState(addres);
			careTaker.add(originator.saveStateToMemento());

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

class NextButton extends CommandButton {
	public NextButton(AddressBookPane pane, RandomAccessFile r) {
		super(pane, r, false);
		this.setText("Next");
	}

	@Override
	public void Execute() {
		try {
			// set nothing if the file is empty
			if (raf.length() == 0) {
				p.clearTextFields();
			}
			
			long currentPosition = raf.getFilePointer();
			if (currentPosition < raf.length())
				readAddress(currentPosition);


		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}

class PreviousButton extends CommandButton {
	public PreviousButton(AddressBookPane pane, RandomAccessFile r) {
		super(pane, r, false);
		this.setText("Previous");
	}

	@Override
	public void Execute() {
		try {
			if(raf.length() == 0) {
				p.clearTextFields();
			}
			
			long currentPosition = raf.getFilePointer();
			if(currentPosition > raf.length()) {
				currentPosition = raf.length();
			}
			if (currentPosition - 2 * 2 * RECORD_SIZE >= 0)
				readAddress(currentPosition - 2 * 2 * RECORD_SIZE);
			

		

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}

class LastButton extends CommandButton {
	public LastButton(AddressBookPane pane, RandomAccessFile r) {
		super(pane, r, false);
		this.setText("Last");
	}

	@Override
	public void Execute() {
		try {
			long lastPosition = raf.length();
			if (lastPosition > 0)
				readAddress(lastPosition - 2 * RECORD_SIZE);

			// set nothing if the file is empty
			if (raf.length() == 0) {
				p.clearTextFields();
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}

class FirstButton extends CommandButton {
	public FirstButton(AddressBookPane pane, RandomAccessFile r) {
		super(pane, r, false);
		this.setText("First");
	}

	@Override
	public void Execute() {
		try {
			if (raf.length() > 0)
				readAddress(0);
			// set nothing if the file is empty
			else {
				p.clearTextFields();
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}

class Redu extends CommandButton {
	Originator originator;
	CareTaker careTaker;

	public Redu(AddressBookPane pane, RandomAccessFile r, Originator originator, CareTaker careTaker) {
		super(pane, r, true);
		this.setText("Redu");
		this.careTaker = careTaker;
		this.originator = originator;

	}

	@Override
	public void Execute() {

		try {
			// check if memento arrey is empty
			if (careTaker.isEmpty() == 1) {

				if (raf.length() == 0) {
					writeToPanel(ZERO, ZERO);
				}

				else {
					// check if we have next element in the array
					if (careTaker.getNext() != null) {
						originator.getStateFromMemento(careTaker.getCurrent());
						writeToPanel(raf.length(), raf.length());
					}

				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// write the string from arrey to panel after the user press redu
	public void writeToPanel(long initionalPoint, long nextPoinet) {

		try {
			raf.seek(initionalPoint);
			FixedLengthStringIO.writeFixedLengthString(originator.getState(), RECORD_SIZE, raf);

			readAddress(nextPoinet);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}

class Undo extends CommandButton {
	Originator originator;
	CareTaker careTaker;

	public Undo(AddressBookPane pane, RandomAccessFile r, Originator originator, CareTaker careTaker) {
		super(pane, r, true);
		this.setText("Undo");
		this.careTaker = careTaker;
		this.originator = originator;
	}

	@Override
	public void Execute() {

		try {
			// check if we have text but and our memento is empty
			if (raf.length() > 0 && careTaker.isEmpty() == 0) {
				showPreviousStatues(originator, careTaker);
			}

			long lastPointer = raf.length();
			if (lastPointer > 0) {
				originator.getStateFromMemento(careTaker.getPrev());
				raf.setLength(lastPointer - 2 * RECORD_SIZE);
			}

			if (raf.length() > 0) {
				readAddress(0);
			} else {
				raf.setLength(0);
				p.clearTextFields();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

// memento class
class Memento {
	private String addres;

	public Memento(String addres) {
		this.addres = addres;
	}

	public String getAddres() {
		return addres;
	}

	public void setAddres(String addres) {
		this.addres = addres;
	}
}

// caretaker class
class CareTaker {
	private List<Memento> mementoList = new ArrayList<Memento>();
	private int index;

	public CareTaker() {
		index = mementoList.size();
	}

	public List<Memento> getMementoList() {
		return mementoList;
	}

	public void setMementoList(List<Memento> mementoList) {
		this.mementoList = mementoList;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public void add(Memento state) {
		if (state != null) {
			mementoList.add(state);
			index = mementoList.size() - 1;
		}
	}

	public int isEmpty() {
		int indicaion = 1;
		if (mementoList.isEmpty()) {
			indicaion = 0;
			return indicaion;
		}

		return indicaion;

	}

	public Memento getPrev() {
		if (mementoList.isEmpty() || index <= 0) {
			return null;
		}
		return mementoList.get(--index);
	}

	public Memento getCurrent() {
		if (mementoList.isEmpty()) {
			return null;
		}
		return mementoList.get(index);
	}

	public Memento getNext() {
		if (mementoList.isEmpty() || index >= mementoList.size() - 1) {
			return null;
		}
		return mementoList.get(++index);
	}

}

// originator class
class Originator {
	private String state;

	public void setState(String state) {
		this.state = state;
	}

	public String getState() {
		return state;
	}

	public Memento saveStateToMemento() {
		return new Memento(state);
	}

	public void getStateFromMemento(Memento memento) {
		if (memento != null) {
			this.state = memento.getAddres();
		}
	}
}
