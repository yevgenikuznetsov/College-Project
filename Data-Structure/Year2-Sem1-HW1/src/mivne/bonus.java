package mivne;

public class bonus {

	public static void main(String[] args) {
		int[] part = new int[0];
		partition(7, 7, part);

	}

	private static void partition(int n, int max, int[] part) {
		if (part.length > max)
			return;

		else if (n == 0) {
			String partitionStr = "";
			for (int i = 0; i < part.length; i++) {
				partitionStr = partitionStr + part[i] + "+";
			}

			System.out.println(partitionStr.substring(0, partitionStr.length()-1));
			return;
			}
		else {
			int lastInPartition = part.length == 0 ? n : part[part.length -1];
			int i = Math.min(n%2 == 0 ? n-1 : n , lastInPartition);
			for( ; i >=1 ; i = i-2) {
				int[] newpatrition = new int[part.length + 1];
				for(int j = 0 ; j < part.length ; j++) {
					newpatrition[j] = part[j];
				}
				newpatrition[part.length] = i;
				partition(n-i, max, newpatrition);
			}
		}
		
	
		} 
		}

	