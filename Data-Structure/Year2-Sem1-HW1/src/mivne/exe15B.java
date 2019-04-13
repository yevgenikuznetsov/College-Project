package mivne;

public class exe15B {

	public static void main(String[] args) {
		int[] arr = new int[]{1,6,4,2,5,7,8};
		System.out.println(missingNum(arr, 0, arr.length-1));
	}
	
	private static int missingNum(int[] arr, int firstIndex, int lastIndex) {
		/* if we have only one number, we know what is the missing number */
		if (firstIndex == lastIndex) {
			if (arr[firstIndex] % 2 == 0) 
				return arr[firstIndex] - 1;
			
			else
				return arr[firstIndex] + 1;
		}
		
		/* calculate the middle number in range [1...2^k]*/
		int middleInRange = (lastIndex - firstIndex + 2)/2 + firstIndex;
		
		/* clculate our middle - in array. we will use the recursia from the dirst question */
		int middleIndex =  (lastIndex - firstIndex)/2;
		int middleInArray = findElementI(arr, firstIndex, lastIndex, middleIndex + 1);
		
		if (middleInArray > middleInRange) {
			return missingNum(arr, firstIndex, firstIndex + middleIndex - 1);
		}
		else {
			return missingNum(arr, firstIndex + middleIndex + 1, lastIndex);
		}
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
