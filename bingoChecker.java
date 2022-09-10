/* 
 * Name: Anthony Nuccio
 * Date: September 11th, 2022
 * Project: Bingo Card Verifier (Java)
 * Class: CSC 1800 | Organization of Programming Languages
 */

// Import Necessary Packages
import java.util.Scanner;	// Read User Input
import java.util.ArrayList;	// Store Called Numbers


public class bingoChecker {
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);	// Insatiate Scanner Class
		
		// Declare User Input Variables
		int[][] boardPattern = new int[5][5];	// Declare Non-Dynamic Array to Store Pattern
		int[][] actualPattern = new int[5][5];	// Declare Non-Dynamic Array to Store Player's Numbers
		ArrayList<Integer> numbersCalled = new ArrayList<>(); // Declare Dynamic Array to Store Numbers Called

		// Read-In boardPattern Array
		for (int i = 0; i < 5; i++) {		// Traverse Each Row
			for (int j = 0; j < 5; j++) {	// Traverse Each Column
				boardPattern[i][j] = scan.nextInt(); } }	// Store Current Number
		
		// Skip Empty Lines
		scan.nextLine();	// To End of Current Line
		scan.nextLine();	// To End of Entirely Empty Line
		
		
		// Read-In numbersCalled ArrayList
		String [] numInput = scan.nextLine().split(" ");	// String Array Separated by Space
		for (int i = 0; i < numInput.length; i++) {			// Traverse Each Index
			numbersCalled.add(Integer.parseInt(numInput[i]));	// Store in ArrayList
		}
		
		// Read-In actualPattern Array
		for (int i = 0; i < 5; i++) {		// Traverse Each Row
			for (int j = 0; j < 5; j++) {	// Traverse Each Columb
				actualPattern[i][j] = scan.nextInt(); } }	// Store Current Number
		
		
		}
	}
}