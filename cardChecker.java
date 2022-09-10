/* 
 * Name: Anthony Nuccio
 * Date: September 11th, 2022
 * Project: Bingo Card Verifier (Java)
 * Class: CSC 1800 | Organization of Programming Languages
 */

// Import Necessary Packages
import java.util.Scanner;	// Read-In User Input
import java.util.ArrayList; // Read-In Called Numbers


public class cardChecker {
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);	// Insatiate Scanner Class
		
		// Declare User Input Variables
		int[][] boardPattern = new int[5][5];	// Non-Dynamic Array to Store Pattern
		int[][] actualPattern = new int[5][5];	// Non-Dynamic Array to Store Player's Card
		ArrayList<Integer> numbersCalled = new ArrayList<>(); // Dynamic Array to Store Numbers Called
		
		// Read-In boardPattern Array
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				boardPattern[i][j] = scan.nextInt(); } }
		
		// Skip Empty Lines
		scan.nextLine();
		scan.nextLine();
		
		// Read-In numbersCalled ArrayList
		String [] numInput = scan.nextLine().split(" ");
		
		for (int i = 0; i < numInput.length; i++) {
			numbersCalled.add(Integer.parseInt(numInput[i])); }
		
		// Read-In actualPattern Array
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				actualPattern[i][j] = scan.nextInt(); } }
	}
}