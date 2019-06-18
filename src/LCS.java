/**
 * Given two strings, find the longest common subsequences.
 * Unlike substrings, subsequences are not required to occupy consecutive positions within the original sequences.
 * DYNAMIC PROGRAMMING APPROACH
 */

public class LCS {
	
	/*** enable extra printing from lcs method. ***/
	// print out dp table if true
	static boolean printTable = true;
	// print out lcs string if true
	static boolean printLCS = true;

	public static void main(String[] args) {
		String A = generateRandomString(5);
		String B = generateRandomString(8);
		System.out.printf("String A: %s\n", A);
		System.out.printf("String B: %s\n", B);
		
		System.out.printf("The length of the Longest Common Subsequence is: %d\n", lcs(A, B));

	}
	
	private static int lcs(String s1, String s2) {
		int[][] table = new int[s2.length()+1][s1.length()+1];
		int i, j;
		// initialize row 0 and col 0 to be zero.
		for(j = 0; j < s1.length()+1; j++)
			table[0][j] = 0;
		for(i = 0; i < s2.length()+1; i++)
			table[i][0] = 0;
		
		// create dynamic programming table from row1 col1 -> row1 colEnd -> rowEnd col1 -> rowEnd colEnd
		for(i = 1; i < s2.length()+1; i++) {
			for(j = 1; j < s1.length()+1; j++) {
				if(s2.charAt(i-1) == s1.charAt(j-1))
					table[i][j] = table[i-1][j-1] + 1;
				else
					table[i][j] = Math.max(table[i-1][j], table[i][j-1]);
			}
		}
		
		/**** Print the Dynamic Programming table if enabled ****/
		if(printTable) {
			for(i = 0; i < s2.length()+1; i++) {
				if(i == 0) {
					System.out.print("  ");
					for(j = 0; j < s1.length(); j++) {
						if(j == 0)
							System.out.print("  ");
						System.out.printf("%c ", s1.charAt(j));
					}
					System.out.println();
					System.out.print("  ");
				}
				for(j = 0; j < s1.length()+1; j++) {
					System.out.printf("%d ", table[i][j]);
				}
				System.out.println();
				if(i < s2.length())
					System.out.printf("%c ", s2.charAt(i));
			}
		}
		
		/**** trace back the table to print out LCS string if enabled ****/
		if(printLCS) {
			String lcs = "";
			int currentMax = table[s2.length()][s1.length()];
			for(i = s2.length(); i >= 0; i--) {
				for(j = s1.length()-1; j >= 0; j--) {
					if(table[i][j] < currentMax) {
						lcs = s1.charAt(j) + lcs;
						currentMax = table[i-1][j];
						i--;
					}
				}
			}
			System.out.printf("The Longest Common Subsequence is: '%s'\n", lcs);
		}
		
		return table[s2.length()][s1.length()];
	}
	
	private static String generateRandomString(int length) {
		String randString = "";
		for(int i = 0; i < length; i++) {
			randString += (char)((int)(Math.random() * 4) + 65);   // 65~68 = A~D
		}
		return randString;
	}
}
