
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.ListIterator;

/* create new array list   */
public class newListIterator implements ListIterator<String> {

	private final int RECORD = CommandButton.RECORD_SIZE;

	private int currentIndex = 0;
	private int lastIndex = -1;
	private int numberOfObject = 0;

	private RandomAccessFile raf;

	public newListIterator(RandomAccessFile raf, int index) throws IOException {
		setRaf(raf);
		currentIndex = index;
		numberOfObject = (int) (raf.length() / (RECORD * 2)); // get number of sring in address book
	}

	public int getCurrentIndex() {
		return currentIndex;
	}

	public void setCurrentIndex(int currentIndex) {
		this.currentIndex = currentIndex;
	}

	public int getLastIndex() {
		return lastIndex;
	}

	public void setLastIndex(int lastIndex) {
		this.lastIndex = lastIndex;
	}

	public RandomAccessFile getRaf() {
		return raf;
	}

	public void setRaf(RandomAccessFile raf) {
		this.raf = raf;
	}

	@Override
	public void add(String e) {
		ArrayList<String> temp = new ArrayList<>();// use to array list to temp

		if (currentIndex < 0)
			return;
		else {

			addToArray(temp);  //add string from file to array
			temp.add(currentIndex, e); // add to array list
			copyToAddress(temp);

			numberOfObject++;
			currentIndex++;
			lastIndex = -1;
		}
	}

	@Override
	public boolean hasNext() {
		return (currentIndex < numberOfObject); // check if the next string exists
	}

	@Override
	public boolean hasPrevious() {
		if (currentIndex <= 0) // check if the previous string exists
			return false;
		else
			return true;
	}

	@Override
	public String next() {
		String sentence = null;
		if (!this.hasNext())
			return null;
		else {
			try {
				raf.seek(currentIndex * RECORD * 2); // get the current pointer
				sentence = FixedLengthStringIO.readFixedLengthString(RECORD, raf); // read from file
				lastIndex = currentIndex;
				currentIndex++;

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sentence;
	}

	@Override
	public int nextIndex() { // get the next index in file
		return currentIndex;
	}

	@Override
	public String previous() {
		String sentence = null;
		if (!this.hasPrevious())
			return null;
		else {
			currentIndex--;
			try {
				raf.seek(currentIndex * RECORD * 2); // get the current pointer
				sentence = FixedLengthStringIO.readFixedLengthString(RECORD, raf);// read from file
			} catch (IOException e) {
				e.printStackTrace();
			}
			lastIndex = currentIndex;

		}
		return sentence;
	}

	@Override
	public int previousIndex() { // get the previous index
		return currentIndex - 1;
	}

	@Override
	public void remove() {
		ArrayList<String> temp = new ArrayList<>(); // use to array list to temp
		if (lastIndex == -1) // check the last index
			return;
		else {

			addToArray(temp); // add string from file to array(temp)

			temp.remove(lastIndex); // remove index that accepted from next or previos method

			copyToAddress(temp); // add string to file from array

			numberOfObject--;
			currentIndex = lastIndex;
			lastIndex = -1;
		}

	}

	@Override
	public void set(String e) {
		if (lastIndex == -1)
			return;
		try {
			raf.seek(lastIndex * (RECORD * 2)); // get the current pointer in file
			FixedLengthStringIO.writeFixedLengthString(e, RECORD, raf); // write the new string instead of the last
																		// string in this place
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	/* read the string from file */
	public void addToArray(ArrayList<String> temp) {

		try {
			raf.seek(0);
			for (long i = 0; i < getRaf().length(); i += (RECORD * 2)) {
				temp.add(FixedLengthStringIO.readFixedLengthString(RECORD, raf));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/* write string to file */
	public void copyToAddress(ArrayList<String> temp) {
		try {
			raf.seek(0);
			raf.setLength(0);
			for (String sentence : temp) {
				FixedLengthStringIO.writeFixedLengthString(sentence, RECORD, raf);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
