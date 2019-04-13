package mivne;

public class exe11 {

	public static void main(String[] args) {
		int[] arr = { 1, 2, 3, 2, 6, 7, 8, 9 };
		int point = 0;
		int[] results = { 0, 0 };

		maxAndFirs(results, arr, point);
		System.out.println(results[0]);
		System.out.println(results[1]);
	}

	private static int[] maxAndFirs(int[] resolt, int[] arr, int x) {
		if (x >= arr.length) {
			resolt[0] = 0;
			resolt[1] = 0;
			return resolt;
		} else if (x == arr.length - 1) {
			resolt[0] = 1;
			resolt[1] = 1;
			return resolt;
		} else {
			int count = 0;
			while (x + 1 < arr.length && arr[x + 1] > arr[x]) {
				x++;
				count++;
			}
			x++;
			count++;
			maxAndFirs(resolt, arr, x);

			resolt[0] = Math.max(resolt[0], count);

			resolt[1] = count;

			return resolt;

		}
	}

}
