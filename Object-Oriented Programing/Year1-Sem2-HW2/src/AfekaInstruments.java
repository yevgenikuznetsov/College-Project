import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

//name : yevgeni kuznetsov
//id: 319498580

public class AfekaInstruments {

	private static final Scanner consoleScanner = new Scanner(System.in);

	public static void main(String[] args) {
		ArrayList<MusicalInstrument> allInstruments = new ArrayList<MusicalInstrument>();
		File file = getInstrumentsFileFromUser();

		loadInstrumentsFromFile(file, allInstruments);

		if (allInstruments.size() == 0) {
			System.out.println("There are no instruments in the store currently");
			return;
		}

		printInstruments(allInstruments);

		int different = getNumOfDifferentElements(allInstruments);

		System.out.println("\n\nDifferent Instruments: " + different);

		MusicalInstrument mostExpensive = getMostExpensiveInstrument(allInstruments);

		System.out.println("\n\nMost Expensive Instrument:\n" + mostExpensive + "\n");

		startInventoryMenu(allInstruments);

		consoleScanner.close();

	}

	public static File getInstrumentsFileFromUser() {
		boolean stopLoop = true;
		File file;

		do {
			System.out.println("Please enter instruments file name / path:");
			String filepath = consoleScanner.nextLine();
			file = new File(filepath);
			stopLoop = file.exists() && file.canRead();

			if (!stopLoop)
				System.out.println("\nFile Error! Please try again\n\n");
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
			scanner.close();
		}
		System.out.println("\nInstruments loaded from file successfully!\n");

	}

	public static ArrayList<Guitar> loadGuitars(Scanner scanner) {
		int numOfInstruments = scanner.nextInt();
		ArrayList<Guitar> guitars = new ArrayList<Guitar>(numOfInstruments);

		for (int i = 0; i < numOfInstruments; i++)
			guitars.add(new Guitar(scanner));

		return guitars;
	}

	public static ArrayList<Bass> loadBassGuitars(Scanner scanner) {
		int numOfInstruments = scanner.nextInt();
		ArrayList<Bass> bassGuitars = new ArrayList<Bass>(numOfInstruments);

		for (int i = 0; i < numOfInstruments; i++)
			bassGuitars.add(new Bass(scanner));

		return bassGuitars;
	}

	public static ArrayList<Flute> loadFlutes(Scanner scanner) {
		int numOfInstruments = scanner.nextInt();
		ArrayList<Flute> flutes = new ArrayList<Flute>(numOfInstruments);

		for (int i = 0; i < numOfInstruments; i++)
			flutes.add(new Flute(scanner));

		return flutes;
	}

	public static ArrayList<Saxophone> loadSaxophones(Scanner scanner) {
		int numOfInstruments = scanner.nextInt();
		ArrayList<Saxophone> saxophones = new ArrayList<Saxophone>(numOfInstruments);

		for (int i = 0; i < numOfInstruments; i++)
			saxophones.add(new Saxophone(scanner));

		return saxophones;
	}

	public static void addAllInstruments(ArrayList<MusicalInstrument> instruments, ArrayList<?> moreInstruments) {
		for (int i = 0; i < moreInstruments.size(); i++) {
			instruments.add((MusicalInstrument) moreInstruments.get(i));
		}
	}

	public static void printInstruments(ArrayList<MusicalInstrument> instruments) {
		for (int i = 0; i < instruments.size(); i++)
			System.out.println(instruments.get(i).toString());
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
		Number maxPrice = 0;
		MusicalInstrument mostExpensive = (MusicalInstrument) instruments.get(0);

		for (int i = 0; i < instruments.size(); i++) {
			MusicalInstrument temp = (MusicalInstrument) instruments.get(i);

			if (temp.getPrice().doubleValue() > maxPrice.doubleValue()) {
				maxPrice = temp.getPrice();
				mostExpensive = temp;
			}
		}

		return mostExpensive;
	}

	public static void startInventoryMenu(ArrayList<MusicalInstrument> instrument) {
		int option = 0;
		int temp;
		boolean toRemove;
		final int minChoose = 1;
		final int maxChoose = 7;

		AfekaInventory<MusicalInstrument> checkInventory = new AfekaInventory<>();

		do {
			printmenu();

			System.out.print("\nYour Option : ");
			option = consoleScanner.nextInt();

			switch (option) {
			case 1: 
				checkInventory.addAllStringInstrument(instrument, checkInventory.getInventory());
				if (!checkInventory.getInventory().isEmpty()) {
					System.out.println("\nAll String Instruments Added Successfully!\n");
				}
				break;
				
			case 2:
				checkInventory.addAllWindInstruments(instrument, checkInventory.getInventory());
				if (!checkInventory.getInventory().isEmpty()) {
					System.out.println("\nAll Wind Instruments Added Successfully!\n");
				}
				break;
				
			case 3:
				checkInventory.sortByBrandAndPrice(checkInventory.getInventory());
				if (checkInventory.isSorted == true) {
					System.out.println("\nInstruments Sorted Successfully!\n");
				}
				break;
				
			case 4:
				System.out.println("\nSEARCH INSTRUMENT:");
				temp = getIndexByBrandAndPrice(checkInventory);
				
				if (temp == -1) {
					System.out.println("Instrument Not Found!\n");
				} else {
					System.out.println("Result:\n" + "       " + checkInventory.getInventory().get(temp) + "\n");
				}
				break;
				
			case 5:
				System.out.println("\nDELETE INSTRUMENTS:");
				temp = getIndexByBrandAndPrice(checkInventory);
				
				if (temp == -1) {
					System.out.println("Instrument Not Found!\n");
				} else {
					System.out.println("Result:\n" + "      " + checkInventory.getInventory().get(temp));
					toRemove = yesNoChoies();
					if (toRemove) {
						checkInventory.removeInstrument(checkInventory.getInventory(),
								checkInventory.getInventory().get(temp));
						System.out.println("Instrument Deleted Successfully!\n");
					}
				}
				
				break;
				
			case 6:
				System.out.println("\nDELETE ALL INSTRUMENTS:");
				toRemove = yesNoChoies();
				
				if (toRemove) {
					checkInventory.removeAll(checkInventory.getInventory());
					System.out.println("\nAll Instruments Deleted Successfully!\n");
				}
				break;
				
			case 7:
				System.out.println(checkInventory.toString());
				break;
				
			default:
				System.out.println("Finished!");
			}
			
		} while (option >= minChoose && option <= maxChoose);

	}

 // print menu of inventory
	public static void printmenu() {
		System.out.println("-------------------------------------------------------------------------");
		System.out.println("AFEKA MUSICAL INSTRUMENT INVENTORY MENU");
		System.out.println("-------------------------------------------------------------------------");
		System.out
				.println("1. Copy All String Instruments To Inventory\n" + "2. Copy All Wind Instruments To Inventory\n"
						+ "3. Sort Insruments by Brand And Price\n" + "4. Search Instrument By Brand And Price\n"
						+ "5. Delete Instruments\n" + "6. Delete all Instruments\n" + "7. Print Inventory Instruments\n"
						+ "Choose your option or any other key to EXIT");

	}

	//Search for musical instruments inventory by company name and price
	public static int getIndexByBrandAndPrice(AfekaInventory<MusicalInstrument> afekaArrey) {
		String bradChoice;
		double priceChoice = 0;
		System.out.print("Brand:");
		bradChoice = consoleScanner.next();
		try {
			System.out.print("Price:");
			priceChoice = Double.parseDouble(consoleScanner.next());
		} catch (NumberFormatException e) {}

		int temp = afekaArrey.binnarySearchByBrandAndPrice(afekaArrey.getInventory(), bradChoice, priceChoice);

		return temp;
	}
 
	//Select the user if yes or no
	public static boolean yesNoChoies() {
		boolean appropriateChar = false;
		boolean toRemove = false;

		while (!appropriateChar) {

			System.out.print("Are You Sure? (Y/N) ");
			char delete = consoleScanner.next().charAt(0);
			if (delete == 'y' || delete == 'Y') {
				toRemove = true;
				appropriateChar = true;
			} else if (delete == 'n' || delete == 'N') {
				appropriateChar = true;
			}
		}
		
		return toRemove;
	}
}
