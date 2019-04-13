package mivne;

public class exe12 {
	private static final int N = 3;
	
	public static void main(String[] args) {
		String mat[][] = new String[N][N];
		int i, j;
		
		for (i = 0; i<N; i++) {
			for (j = 0; j<N; j++) {
				mat[i][j] = "a";
			}
		}
		
		for (i = 0; i<N; i++) {
			mat[i][N-i-1] = "b";
		}
		
		System.out.println(check(mat));
	}
	
	private static int check(String[][] mat) {
		if (mat.length == 0 || !mat[0][mat.length - 1].equals("b"))
			return 0;
		
		String smallerMatrix[][] = new String[mat.length-1][mat.length-1];
		/* copy the second row and under, copy first column till the lest (without the lest) */
		for(int i = 1; i < mat.length; i++) {
			for (int j = 0; j < mat.length - 1; j++) { 
				smallerMatrix[i - 1][j] = mat[i][j];
			}
		}
		
		int smallerArrowMat = check(smallerMatrix);
		
		int columnCounter;
		for (columnCounter = 0; columnCounter < smallerArrowMat; columnCounter++) {
			if (!mat[0][mat.length-2-columnCounter].equals("a")) 
				break;
		}
		
		int rowsCounter;
		for (rowsCounter = 0; rowsCounter < smallerArrowMat; rowsCounter++) {
			if (!mat[rowsCounter + 1][mat.length - 1].equals("a")) 
				break;
		}
		
		return 1 + Math.min(columnCounter, rowsCounter);
	}
}
