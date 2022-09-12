/* 
 * Name: Anthony Nuccio
 * Date: September 11th, 2022
 * Project: Bingo Card Verifier (Java)
 * Class: CSC 1800 | Organization of Programming Languages
 */

import java.util.Scanner; // Needed to Scan User Input
import java.util.ArrayList; // Needed to Store Numbers Called (Need Dynamic List as Size is Unknown)

public class BingoCardChecker {

    // Declare Necessary Variables to Store User-Input
    public static int[][] bingoPattern = new int[5][5]; // Non-Dyanmic 2D Array to Store Bingo Pattern
    public static int[][] bingoCard = new int[5][5]; // Non-Dynamic 2D Array to Store Player's Card
    public static int[][] bingoTracker = new int[5][5]; // Non-Dynamic 2D Array to Track Player's Pattern
    public static ArrayList<Integer> numbersCalled = new ArrayList<>(); // Dynamic ArrayList to Store Called Numbers

    public static int patternCount;		// Used to Determine Number of Characters in Pattern
    public static int matchCount = 0;	// Used in Boolean Methods to Compare to patternCount
    public static int numCount = 0;		// Used in Boolean Methods to Compare to patternCount

    public static void main(String[] args) {

        // Initialize Scanner Class
        Scanner sc = new Scanner(System.in);

        // Read In Bingo Pattern
        for (int i = 0; i < 5; i++) { // Traverse Each Row
            for (int j = 0; j < 5; j++) { // Traverse Each Column
                bingoPattern[i][j] = sc.nextInt(); } } // Store Value in Current Position

        // Skip Empty Lines in User Input
        sc.nextLine(); // To End of Current Line
        sc.nextLine(); // To End of Entirely Empty Line

        /*
         * " nextLine(); " Method Found on Geeks for Geeks
         * According to Article, Method is a Better Choice than ".startsWith(" ")"
         * https://www.geeksforgeeks.org/scanner-nextline-method-in-java-with-examples/
         * https://stackoverflow.com/questions/42685795/how-to-skip-reading-a-line-with
         * -scanner
         */

        // Read In Called Numbers
        String[] numInput = sc.nextLine().split(" "); // Store Values in String Array

        for (int i = 0; i < numInput.length; i++) { // Traverse Each Index
            numbersCalled.add(Integer.parseInt(numInput[i])); } // Store Values in ArrayList

        /*
         * String Array Method Found on Stack Overflow
         * According to Answers, It Is the Best Way for Creating an ArrayList
         * ArrayList is Necessary as size needs to be dynamic (unknown prior to user input)
         * https://stackoverflow.com/questions/10530353/convert-string-array-to-
         * arraylist
         */

        // Read In Player's Board Numbers
        for (int i = 0; i < 5; i++) { // Traverse Each Row
            for (int j = 0; j < 5; j++) { // Traverse Each Column
                bingoCard[i][j] = sc.nextInt(); } } // Store Value in Current Position

        // Search Array for Pattern Markers
        for (int i = 0; i < 5; i++) { // Traverse Each Row
            for (int j = 0; j < 5; j++) { // Traverse Each Column
                if (bingoPattern[i][j] == 1 || bingoPattern[i][j] == 4) {
                    patternCount++;	} } } // Add to Count of Characters in Pattern
        
        // copmpares callNumbers to Players Bingo Board.
        for (int i = 0; i < 5; i++) {	// Traverse Each Row
            for (int j = 0; j < 5; j++) {	// Traverse Each Column
                if (numbersCalled.contains(bingoCard[i][j])) {
                    bingoTracker[i][j] = 1;	// Mirror bingoPattern Input Array
                } else {
                    bingoTracker[i][j] = 0; } } }

        /*
         * ".contains" Method Found on Stack Overflow
         * Even though efficiency is not the concern of the project, I am not sure if it
         * is the most efficient method (maybe hash table would be better)
         * https://stackoverflow.com/questions/18920622/how-arraylist-contains-method-
         * work
         */

        // Set Middle Space as Free Space in Array
        bingoTracker[2][2] = 1;

        if (verticalCheck() == true || horizontalCheck() == true) {	// If Patttern Found Horizontally or Vertically
            System.out.println("VALID BINGO");
        } else if (diagnalCheck(bingoTracker.length - 1, -1) == true) {	// If Pattern Found Diagnally
            System.out.println("VALID BINGO");
        } else if (crazyCheck() == true) {		// If Crazy Pattern Found
            System.out.println("VALID BINGO");
        } else {
            System.out.println("NO BINGO"); } }

    public static boolean horizontalCheck() {

        int lastNumCalled = numbersCalled.get(numbersCalled.size() - 1);

        /*
         * "numbersCalled.size{}" Method Found on Stack Overflow
         * Tried to Declare in Public, but Can't
         * Requires user input to initialize and can not be put outside the main void (where user input is scanned)
         * https://stackoverflow.com/questions/687833/how-to-get-the-last-value-of-an-
         * arraylist
         */

        for (int i = 0; i < bingoTracker.length; i++) {	// Traverse Each Row
            for (int j = 0; j < bingoTracker[i].length; j++) { // Traverse Each Column
                if (bingoTracker[i][j] == 1) {
                    matchCount += 1;	// Add to Count
                } else {
                    break; }
                if (bingoCard[i][j] == lastNumCalled) {
                    numCount += 1; }	// Add to Count of Last Number Found in Pattern
                if (matchCount == 5 && numCount == 1) {
                    return true; } }
            matchCount = 0;
            numCount = 0; } // Reset Count for Next Iteration
        return false;

        /*
         * Method Found on Geeks for Geeks
         * https://www.geeksforgeeks.org/check-horizontal-vertical-symmetry-binary-matrix/
         */
    }

    public static boolean verticalCheck() {

        int lastNumCalled = numbersCalled.get(numbersCalled.size() - 1);

        for (int i = 0; i < bingoTracker.length; i++) {	// Traverse Each Row
            for (int j = 0; j < bingoTracker[i].length; j++) {	// Traverse Each Column
                if (bingoTracker[j][i] == 1) {
                    matchCount += 1;	// Add to Count
                } else {
                    break; }
                if (bingoCard[j][i] == lastNumCalled) {
                    numCount += 1; } // Add to Count if Last Number Found in Pattern
                if (matchCount == 5 && numCount == 1) {
                    return true; } }
            matchCount = 0;
            numCount = 0; } // Reset Count for Next Iteration
        return false;

        /*
         * Method Found on Geeks for Geeks
         * https://www.geeksforgeeks.org/check-horizontal-vertical-symmetry-binary-matrix/
         */
    }

    public static boolean diagnalCheck(int var, int x) {

        int lastNumCalled = numbersCalled.get(numbersCalled.size() - 1);

        for (int i = 0; i < bingoTracker.length; i++) {	// Traverse Each Row
            for (int j = var; j < bingoTracker[i].length; j++) { // Traverse Each Column
                if (bingoTracker[i][j] == 1) {
                    matchCount += 1;
                } else {
                    break; }
                if (bingoCard[i][j] == lastNumCalled) {
                    numCount += 1; }	// Add to Count if Last Number Found in Pattern
                if (matchCount == 5 && numCount == 1) {
                    return true; }
                var += x;	// Increment to Skip Diagnlly
                break; }
            matchCount = 0;
            numCount = 0; } // Reset Count for Next Iteration
        return false;

        /*
         * Methoud Found on Youtube from Geeks for Geeks
         * https://www.youtube.com/watch?v=vd6Ly5rGBo0
         */
    }

    public static boolean crazyCheck() {

        int lastNumCalled = numbersCalled.get(numbersCalled.size() - 1);

        // Count for Matched Patterns for Each of the Rotations
        int zeroCount = 0;
        int ninetyCount = 0;
        int oneEightyCount = 0;
        int twoSeventyCount = 0;

        for (int i = 0; i < 5; i++) { // Traverse Each Row
            for (int j = 0; j < 5; j++) { // Traverse Each Column
                if (bingoPattern[i][j] == 4 && bingoTracker[i][j] == 1) { // If Crazy 4 is Indicated Where Pattern Is
                    if (bingoCard[i][j] == lastNumCalled) { // Check for Last Number Prescence
                        numCount++; }
                    zeroCount++; // Increment Count for Crazy Pattern
                }
                turnArray(bingoPattern); // Call Method to Rotate Array
                if (bingoPattern[i][j] == 4 && bingoTracker[i][j] == 1) { // If Crazy 4 is Indicated Where Pattern Is
                    if (bingoCard[i][j] == lastNumCalled) { // Check for Last Number Prescence
                        numCount++; 
                    }
                    ninetyCount++; // Increment Count for Crazy Pattern
                }
                turnArray(bingoPattern); // Call Method to Rotate Array
                if (bingoPattern[i][j] == 4 && bingoTracker[i][j] == 1) { // If Crazy 4 is Indicated Where Pattern Is
                    if (bingoCard[i][j] == lastNumCalled) { // Check for Last Number Prescence
                        numCount++;
                    }
                    oneEightyCount++; // Increment Count for Crazy Pattern
                }
                turnArray(bingoPattern);
                if (bingoPattern[i][j] == 4 && bingoTracker[i][j] == 1) { // If Crazy 4 is Indicated Where Pattern Is
                    if (bingoCard[i][j] == lastNumCalled) { // Check for Last Number Prescence
                        numCount++;
                    }
                    twoSeventyCount++; // Increment Count for Crazy Pattern
                } 
                turnArray(bingoPattern);
            }
        }
        
        if (patternCount == zeroCount && numCount > 0)
        	return true;
        if (patternCount == ninetyCount && numCount > 0)
        	return true;
        if (patternCount == oneEightyCount && numCount > 0)
        	return true;
        if (patternCount == twoSeventyCount && numCount > 0)
            return true;

        return false;
        
        
        /*
         * Created Method Based on Reading on Similar Topic
         * https://dev.to/alisabaj/rotating-a-matrix-90-degrees-4a49
         */
    }

    static void turnArray(int a[][]) {
    	
        for (int i = 0; i < 5 / 2; i++) {
            for (int j = i; j < 5 - i - 1; j++) {
                int temp = a[i][j];
                a[i][j] = a[5 - 1 - j][i];
                a[5 - 1 - j][i] = a[5 - 1 - i][5 - 1 - j];
                a[5 - 1 - i][5 - 1 - j] = a[j][5 - 1 - i];
                a[j][5 - 1 - i] = temp;
            }
        }
        
        /*
         * Method Found on Geeks for Geeks
         * https://www.geeksforgeeks.org/rotate-a-matrix-by-90-degree-in-clockwise-direction-without-using-any-extra-space/
         */
    }
}
