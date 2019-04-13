package mivne;

public class exe15 {

	public static void main(String[] args) {
		int M[] = {10, 35, 50}; 
		int P[] = {5, 100, 30};
		int k = 20;
		
		System.out.println(profit(M, P, k));
	}
	
	private static int profit(int[] M, int[] P, int k) {
		if (M.length == 1)
			return P[0];
		else if (M.length == 0)
			return 0;
		
		int withoutFirstM[] = new int[M.length - 1];
		int withoutFirstP[] = new int[P.length - 1];
		
		for (int i = 0; i < withoutFirstM.length; i++) {
			withoutFirstM[i] = M[i+1];
			withoutFirstP[i] = P[i+1];
		}
		
		int profitWithoutTheFirst = profit(withoutFirstM, withoutFirstP, k);
		
		int j;
		for (j = 1; j < M.length; j++) {
			if (M[j] - M[j-1] >= k)
				break;
		}
		
		int withoutTooCloseM[] = new int[M.length - j];
		int withoutTooCloseP[] = new int[P.length - j];
		
		for (int i = 0; i < withoutTooCloseM.length; i++) {
			withoutTooCloseM[i] = M[j + i];
			withoutTooCloseP[i] = P[j + i];
		}
		
		int profitWithoutTooClose = profit(withoutTooCloseM, withoutTooCloseP, k);
		
		return Math.max(profitWithoutTheFirst, profitWithoutTooClose + P[0]);
	}
}
