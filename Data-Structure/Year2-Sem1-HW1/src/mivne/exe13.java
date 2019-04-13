package mivne;

public class exe13 {

	public static void main(String[] args) {
		int arr[] = new int[] {1,2,3,4,5};
		int k = 2;
		
		System.out.println(findElementI(arr, 0, arr.length - 1, k));
	
	}	   

	private static int findElementI(int[] array, int firstIndex, int lastIndex, int ithElement) { 
		/* our piva is the median index */
		int pivotIndex = firstIndex + (lastIndex - firstIndex) / 2;
		int pivotElement = array[pivotIndex];
		
		/* oreder the array - if element smaller then the pivaElement it's should be in smaller index
		 * the upside for bigger element */
		int temp = array[lastIndex];
		array[lastIndex] = pivotElement;
		array[pivotIndex] = temp;
		
		int newPivaIndex = firstIndex;
		for (int j = firstIndex; j<=lastIndex - 1; j++) {
			/* check if we should do a swap */
			if (array[j] <= pivotElement) {
				temp = array[newPivaIndex];
				array[newPivaIndex] = array[j];
				array[j] = temp;
				
				newPivaIndex++;
			}
		}
		
		/* swap between last index to pivot element */
		temp = array[newPivaIndex];
		array[newPivaIndex] = array[lastIndex];
		array[lastIndex] = temp;
		
		/* in this array and sorted array the newPivaIndex is the same for piva element, it's the (newPivaIndex + 1)th element */
		if (newPivaIndex - firstIndex + 1 == ithElement)
			return pivotElement;
		
		/* the ith element smaller then the piva element */
		else if (newPivaIndex >= ithElement) {
			return findElementI(array, firstIndex, newPivaIndex - 1, ithElement);
		}
		
		/* the ith element biger then the piva - check in the bigger elments the ith - newPiva element */
		else {
			return findElementI(array, newPivaIndex + 1, lastIndex, ithElement - newPivaIndex - 1);
		}
	}
}
