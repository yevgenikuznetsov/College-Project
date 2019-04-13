/*yevgeni kuznetsov  */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AfekaInstrumetnts {

	public static void main(String[] args) {
		ArrayList musicalInstruments  = new ArrayList(); 
		Scanner scanner = new Scanner(System.in);

		/* get instrument file */
		File instrumentFile = null;		
		do {
			try {
				instrumentFile = getFile(scanner);
			} catch (FileNotFoundException e) {
				System.out.println("\n" + e.getMessage() + "\n");
			}
		}
		while(instrumentFile == null);
	
		/* get instruments from instruments file */
		try {
			scanner = new Scanner(instrumentFile);

			// import instruments info from file, and add instruments to instrument array
			addAllIinstruments(importGuitarsnformation(scanner), musicalInstruments);
			addAllIinstruments(importbassGuitarsnformation(scanner), musicalInstruments);
			addAllIinstruments(importFlutesinformation(scanner), musicalInstruments);
			addAllIinstruments(importSaxophonesinformation(scanner), musicalInstruments);
			
			System.out.println("\nInstruments loaded from file successfully!\n");

			// print all the instruments
			instrumentInTheStore(musicalInstruments);
			
		} catch (NumberFormatException e) {
			System.err.println("\nYour input value type is invalid");
			System.exit(1);
		} catch (FileNotFoundException e) {
				e.printStackTrace();
		} catch (IllegalArgumentException | InputMismatchException e) {
			System.err.println("\n" + e.getMessage());
			System.exit(1);
		} finally {
			scanner.close();
		}

	}

	/***************************************
	 * convert file name to File object   
	 * @param scanner					  
	 * @return found file by name		  
	 **************************************/
	public static File getFile(Scanner userScanner) throws FileNotFoundException { 
		File instrumentFile = null;
		
		// scan file name from the user
		System.out.println("Please enter instrument file name / path:");
		String filename = userScanner.nextLine();
		
		instrumentFile = new File(filename);
		
		// check if file exists
		if (!instrumentFile.exists())
			throw new FileNotFoundException("File Error! Please try again:");
		
		return instrumentFile;
	}

	/******************************************
	 * import all guitars from instrument file   
	 * @param scanner					  
	 * @return array of guitars		  
	 *****************************************/
	public static ArrayList importGuitarsnformation(Scanner fileScanner) {
		// get guitar number
		int numOfGuitar = fileScanner.nextInt();
		ArrayList guitarsArray = new ArrayList(numOfGuitar);
		
		// go over the guitar in file and add it to collection
		for (int i=0 ; i < numOfGuitar; i++) {
			guitarsArray.add(new Guitar(fileScanner));
		}
		
		return guitarsArray;
	}
	
	/***********************************************
	 * import all bass guitars from instrument file   
	 * @param scanner					  
	 * @return array of bass guitars		  
	 **********************************************/
	public static ArrayList importbassGuitarsnformation(Scanner fileScanner) {
		// get bass guitar number
		int numOfBassGuitars = fileScanner.nextInt();
		ArrayList guitarBassArray = new ArrayList(numOfBassGuitars);
		
		// go over the bass guitars in file and add it to collection 
		for (int i=0 ; i < numOfBassGuitars; i++) {
			guitarBassArray.add(new Bass(fileScanner));			
		}
		
		return guitarBassArray;
	}

	/******************************************
	 * import all flutes from instrument file   
	 * @param scanner					  
	 * @return array of flutes	  
	 *****************************************/
	public static ArrayList importFlutesinformation(Scanner fileScanner) {
		// get flutes number
		int numOfFlutes = fileScanner.nextInt();
		ArrayList flute = new ArrayList(numOfFlutes);
		
		// go over the flutes in file and add it to collection
		for (int i=0 ; i < numOfFlutes; i++) {
			flute.add(new Flute(fileScanner));
		}
		
		return flute;
	}
	
	/******************************************
	 * import all saxophone from instrument file   
	 * @param scanner					  
	 * @return array of saxopones	  
	 *****************************************/
	public static ArrayList importSaxophonesinformation(Scanner fileScanner) {
		// get saxophone number
		int numOfSaxophones = fileScanner.nextInt();
		ArrayList saxophones = new ArrayList(numOfSaxophones);
		
		// go over the flutes in file and add it to collection
		for (int i=0 ; i < numOfSaxophones; i++) {
			saxophones.add(new Saxophone(fileScanner));
		}
		
		return saxophones;
	}
	

	/**************************************************************
	 * copy objects from on array to other
	 * @param musicalInstrumentsToAdd - instruments we want to add
	 * @param musicalInstruments - array for adding instruments
	 * @return array with added instruments
	 *************************************************************/
	public static ArrayList addAllIinstruments(ArrayList musicalInstrumentsToAdd, ArrayList  musicalInstruments) {
		// go over the instruments to add, and add them to other array
		for (int i = 0 ; i < musicalInstrumentsToAdd.size() ; i++) {
			musicalInstruments.add(musicalInstrumentsToAdd.get(i));
		}
		
		return musicalInstruments;
	}
	
	/*************************************************
	 * print store instruments
	 * @param musicalInstruments - store instruments 
	 *************************************************/
	public static void instrumentInTheStore (ArrayList musicalInstruments) {
		// check if the store has instruments
		if (!musicalInstruments.isEmpty()) {
			// call to print method
			printInstruments(musicalInstruments);
			
			// print all different instruments in the store
			System.out.println("\nDifferent Instrumenrs: " + getDifferentElements( musicalInstruments) +"\n");
			
			// print the most expensive instrument
			System.out.print(getMostExpensiveInstrument(musicalInstruments));
		}
		else System.out.println("There are no instruments in the store currently\n");				 
	}
	
	/********************************************************************
	 * print array elements
	 * @param musicalInstruments - array for adding instruments to print 
	 ********************************************************************/
	public static void printInstruments (ArrayList musicalInstruments) {
		// go over the instruments, and print each of them
		for(int i = 0 ; i < musicalInstruments.size() ; i++) {
			System.out.println(musicalInstruments.get(i).toString());
		}	
	}
	
	/******************************************************
	 * find the most expansive instrument in array
	 * @param musicalInstruments - array of instruments 
	 *****************************************************/
	public static MusicalInstruments getMostExpensiveInstrument(ArrayList  musicalInstruments) {
		MusicalInstruments instrumentWithMaxPrice = null;
		double max = -1;
			
		// go over the instruments
		for (int i = 0 ; i < musicalInstruments.size() ; i++) {
			// compare between max price and current instrument price
			if (((MusicalInstruments) musicalInstruments.get(i)).getPrice() > max) {
				max = ((MusicalInstruments)musicalInstruments.get(i)).getPrice();
				instrumentWithMaxPrice = (MusicalInstruments) musicalInstruments.get(i);
			}
		}
		
		return instrumentWithMaxPrice;
	}


	/************************************************************
	 * calculate the number of different instruments
	 * @param musicalInstruments - array of instrument
	 * @return number of different instruments from input array 
	 ************************************************************/
	public static int getDifferentElements(ArrayList musicalInstruments) {
		// we will save duplicate instrument instance in this array
		ArrayList duplicateInstruments = new ArrayList();
		int counter = 0;
		int countDifference;
		
		for (int i = 0 ; i <musicalInstruments.size() ; i++) {
			countDifference = 0;
			
			for (int j = 0 ; j <musicalInstruments.size() ;j++) {
				// check if the instruments are equal (we dont want compare object to itself)
				if (i != j && musicalInstruments.get(i).equals(musicalInstruments.get(j))) {
					countDifference++;
				}
			}
			
			// if the instrument is unique, count it
			if (musicalInstruments.size() - countDifference == 1 ) counter++;
			// else if the instrument is duplicated
			else {
				boolean isExist = false;
				// check if we already count this instrument
				for (int k = 0 ; k < duplicateInstruments.size() ; k ++) {
					if (musicalInstruments.get(i).equals(duplicateInstruments.get(k))) {
						isExist =true;
						break;
					}
				}
				
				// if it's the first instance of duplicate instrument, we need add it to duplicateInstruments and count it
				if( !isExist) {
					duplicateInstruments.add((MusicalInstruments) musicalInstruments.get(i));
					counter++;
				}
			}
		}
		
		return counter;	
	}
}
