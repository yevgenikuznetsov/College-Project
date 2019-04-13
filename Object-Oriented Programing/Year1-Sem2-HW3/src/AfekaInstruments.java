
/**
 * Yevgeni kuznetsov
 * id :319498580
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class AfekaInstruments extends Application {

	public static void main(String[] args) {

		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		ArrayList<MusicalInstrument> allInstruments = new ArrayList<MusicalInstrument>();

		File file = getInstrumentsFileFromUser();
		loadInstrumentsFromFile(file, allInstruments);

		BorderPane mainlayer = new BorderPane();
		Scene scene = new Scene(mainlayer, ViewConf.width, ViewConf.Length);
		SearchGui top = new SearchGui();
		PanelGui center = new PanelGui(allInstruments);
		ButtomLow low = new ButtomLow();
		StackPaneWithButton left = new StackPaneWithButton("<");
		StackPaneWithButton right = new StackPaneWithButton(">");

		center.showInstrument();

		setSearch(top, center, allInstruments);
		SetSideButton(left, right, center);
		setBottomButton(low, center);
		setKeybord(scene, allInstruments, center, top);

		primaryStage.setTitle("Afeka Instruments Music Store");
		mainlayer.setTop(top.createLayout());
		mainlayer.setCenter(center.createLayoutCenter());
		mainlayer.setLeft(left);
		mainlayer.setRight(right);
		mainlayer.setBottom(low.createlowLayers());

		primaryStage.setScene(scene);
		primaryStage.show();
	}

	// set searcch option
	public void setSearch(SearchGui searchGui, PanelGui panelGui, ArrayList<MusicalInstrument> allInstruments) {
		searchGui.getButtonSearch().setOnAction(e -> {
			panelGui.setInstrumentsToShow(searchGui.getBySearch(searchGui.getSearch().getText(), allInstruments));
			panelGui.setIndex(0);
			panelGui.showInstrument();
		});
	}

	// set side button option
	public void SetSideButton(StackPaneWithButton left, StackPaneWithButton right, PanelGui panelGui) {
		left.getButton().setOnAction(e -> {
			panelGui.updateIndex("left");
			panelGui.showInstrument();
		});

		right.getButton().setOnAction(e -> {
			panelGui.updateIndex("right");
			panelGui.showInstrument();
		});
	}

	// set a bottom button option
	public void setBottomButton(ButtomLow ButtomLow, PanelGui panelGui) {
		ButtomLow.getDelete().setOnAction(e -> {
			if (!panelGui.getInstrumentsToShow().isEmpty()) {
				panelGui.deleteInstruments();
				panelGui.showInstrument();
			}
		});

		ButtomLow.getClear().setOnAction(e -> {
			panelGui.deleteAllInstruments();
			panelGui.showInstrument();
		});

		ButtomLow.getAdd().setOnAction(e -> {
			new AddInstruments(panelGui);
		});
	}

	// set keybord option
	public void setKeybord(Scene scene, ArrayList<MusicalInstrument> allInstruments, PanelGui panel,
			SearchGui search1) {
		scene.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.ENTER) {
				panel.setIndex(search1.indexOfInstruments(allInstruments));
				panel.showInstrument();
			} else if (e.getCode() == KeyCode.DELETE) {
				if (!allInstruments.isEmpty()) {
					panel.deleteInstruments();
					panel.showInstrument();
				}
			} else if (e.getCode() == KeyCode.A) {
				new AddInstruments(panel);
			}
		});
	}

	public static File getInstrumentsFileFromUser() {
		boolean stopLoop = true;

		File file;
		TextInputDialog dialog = new TextInputDialog();
		Alert fileNotFound = new Alert(AlertType.ERROR);
		fileNotFound.setContentText("Cannot read from file, please try again");
		fileNotFound.setHeaderText("File Error!");
		dialog.setHeaderText("Load Instruments From File");
		dialog.setContentText("Please enter file name");

		do {
			Optional<String> fileInput1 = dialog.showAndWait();
			if (!fileInput1.isPresent())
				System.exit(1);

			file = new File(fileInput1.get());
			stopLoop = file.exists() && file.canRead();

			if (!stopLoop)
				fileNotFound.showAndWait();

		} while (!stopLoop);
		return file;
	}

	public static void loadInstrumentsFromFile(File file, ArrayList<MusicalInstrument> allInstruments) {
		Scanner scanner = null;

		try {

			scanner = new Scanner(file);

			addAllInstruments(allInstruments, loadGuitars(scanner));

			addAllInstruments(allInstruments, loadBassGuitars(scanner));

			addAllInstruments(allInstruments, loadFlutes(scanner));

			addAllInstruments(allInstruments, loadSaxophones(scanner));

		} catch (InputMismatchException | IllegalArgumentException ex) {
			System.err.println("\n" + ex.getMessage());
			System.exit(1);
		} catch (FileNotFoundException ex) {
			System.err.println("\nFile Error! File was not found");
			System.exit(2);
		} finally {
			// scanner.close();
		}
		System.out.println("\nInstruments loaded from file successfully!\n");

	}

	public static ArrayList<MusicalInstrument> loadGuitars(Scanner scanner) {
		int numOfInstruments = scanner.nextInt();
		ArrayList<MusicalInstrument> guitars = new ArrayList<MusicalInstrument>(numOfInstruments);
		for (int i = 0; i < numOfInstruments; i++)
			guitars.add(new Guitar(scanner));
		return guitars;
	}

	public static ArrayList<MusicalInstrument> loadBassGuitars(Scanner scanner) {
		int numOfInstruments = scanner.nextInt();
		ArrayList<MusicalInstrument> bassGuitars = new ArrayList<MusicalInstrument>(numOfInstruments);

		for (int i = 0; i < numOfInstruments; i++)
			bassGuitars.add(new Bass(scanner));

		return bassGuitars;
	}

	public static ArrayList<MusicalInstrument> loadFlutes(Scanner scanner) {
		int numOfInstruments = scanner.nextInt();
		ArrayList<MusicalInstrument> flutes = new ArrayList<MusicalInstrument>(numOfInstruments);

		for (int i = 0; i < numOfInstruments; i++)
			flutes.add(new Flute(scanner));

		return flutes;
	}

	public static ArrayList<MusicalInstrument> loadSaxophones(Scanner scanner) {
		int numOfInstruments = scanner.nextInt();
		ArrayList<MusicalInstrument> saxophones = new ArrayList<MusicalInstrument>(numOfInstruments);

		for (int i = 0; i < numOfInstruments; i++)
			saxophones.add(new Saxophone(scanner));

		return saxophones;
	}

	public static void addAllInstruments(ArrayList<MusicalInstrument> instruments,
			ArrayList<MusicalInstrument> moreInstruments) {
		for (int i = 0; i < moreInstruments.size(); i++) {
			instruments.add(moreInstruments.get(i));
		}
	}

	public static void printInstruments(ArrayList<MusicalInstrument> instruments) {
		for (int i = 0; i < instruments.size(); i++)
			System.out.println(instruments.get(i));
	}

	public static int getNumOfDifferentElements(ArrayList<MusicalInstrument> instruments) {
		int numOfDifferentInstruments;
		ArrayList<MusicalInstrument> differentInstruments = new ArrayList<MusicalInstrument>();
		System.out.println();

		for (int i = 0; i < instruments.size(); i++) {
			if (!differentInstruments.contains((instruments.get(i)))) {
				differentInstruments.add(instruments.get(i));
			}
		}

		if (differentInstruments.size() == 1)
			numOfDifferentInstruments = 0;

		else
			numOfDifferentInstruments = differentInstruments.size();

		return numOfDifferentInstruments;
	}

	public static MusicalInstrument getMostExpensiveInstrument(ArrayList<MusicalInstrument> instruments) {
		double maxPrice = 0;
		MusicalInstrument mostExpensive = (MusicalInstrument) instruments.get(0);
		for (int i = 0; i < instruments.size(); i++) {
			MusicalInstrument temp = (MusicalInstrument) instruments.get(i);
			if (temp.getPrice().doubleValue() > maxPrice) {
				maxPrice = temp.getPrice().doubleValue();
				mostExpensive = temp;
			}
		}
		return mostExpensive;
	}

	// HW2 Method
	public static void startMenu(ArrayList<MusicalInstrument> src) {
		AfekaInventory<MusicalInstrument> InstInventory = new AfekaInventory<MusicalInstrument>();
		boolean stopLoop = false;
		Scanner scn = new Scanner(System.in);
		do {
			System.out.println("\n-------------------------------------------------------------------------");
			System.out.println("AFEKA MUSICAL INSTRUMENT INVENTORY MENU");
			System.out.println("-------------------------------------------------------------------------");
			System.out.println("1. Copy All String Instruments To Inventory");
			System.out.println("2. Copy All Wind Instruments To Inventory");
			System.out.println("3. Sort Instruments By Brand And Price");
			System.out.println("4. Search Instrument By Brand And Price");
			System.out.println("5. Delete Instrument");
			System.out.println("6. Delete all Instruments");
			System.out.println("7. Print Inventory Instruments");
			System.out.println("Choose your option or any other key to EXIT\n");
			System.out.print("Your Option: ");
			String op = scn.next();
			String brand = "", input = "";
			Number price = 0;
			int index = -1;
			switch (op) {
			case "1":
				InstInventory.addAllStringInstruments(src, InstInventory.getArr());
				System.out.println("\nAll String Instruments Added Successfully!\n");
				break;
			case "2":
				InstInventory.addAllWindInstruments(src, InstInventory.getArr());
				System.out.println("\nAll Wind Instruments Added Successfully!\n");
				break;
			case "3":
				InstInventory.SortByBrandAndPrice(InstInventory.getArr());
				System.out.println("\nInstruments Sorted Successfully!\n");
				break;
			case "4":
				System.out.println("\nSEARCH INSTRUMENT:");
				System.out.print("Brand:");
				brand = scn.next();
				System.out.print("Price:");
				try {
					input = scn.next();
					price = Integer.parseInt(input);
				} catch (NumberFormatException e1) {
					try {
						price = Double.parseDouble(input);
					} catch (NumberFormatException e2) {
					}
				}
				index = InstInventory.binSearchByBrandAndPrice(InstInventory.getArr(), brand, price);
				if (index < 0)
					System.out.println("Instrument Not Found!\n");
				else
					System.out.printf("Result:\n\t%s\n", InstInventory.getArr().get(index));
				break;
			case "5":
				System.out.println("\nDELETE INSTRUMENT:");
				System.out.print("Brand:");
				brand = scn.next();
				System.out.print("Price:");
				try {
					input = scn.next();
					price = Integer.parseInt(input);
				} catch (NumberFormatException e1) {
					try {
						price = Double.parseDouble(input);
					} catch (NumberFormatException e2) {
					}
				}
				index = InstInventory.binSearchByBrandAndPrice(InstInventory.getArr(), brand, price);
				if (index < 0)
					System.out.println("Instrument Not Found!\n");
				else {
					System.out.printf("Result:\n\t%s\n", InstInventory.getArr().get(index));
					System.out.print("Are You Sure?(Y/N) ");
					op = scn.next();
					if (op.toLowerCase().equals("y")) {
						InstInventory.getArr().remove(index);
						System.out.println("Instrument Deleted Successfully!\n");
					} else
						System.out.println("Operation Canceled!");
				}
				break;
			case "6":
				System.out.println("\nDELETE ALL INSTRUMENTS:");
				System.out.print("Are You Sure?(Y/N) ");
				op = scn.next();
				if (op.toLowerCase().equals("y")) {
					InstInventory.removeAll(InstInventory.getArr());
					System.out.println("\nAll Instruments Deleted Successfully!\n");
				} else
					System.out.println("Operation Canceled!");
				break;
			case "7":
				System.out.println(InstInventory);
				break;
			default:
				stopLoop = true;
				System.out.println("Finished!");
				break;
			}

		} while (!stopLoop);
		scn.close();
	}
}
