/*
 * Class: CMSC203 
 * Instructor: Huseyin Aygun
 * Description: This class tests the two dim ragged array utility class
 * Due: 11/17/2025
 * Platform/compiler: JDK version 21.0.7, Eclipse version 2025-03
 * I pledge that I have completed the programming 
 * assignment independently. I have not copied the code 
 * from a student or any source. I have not given my code 
 * to any student.
   Print your Name here: Izzat Ismailov
*/

import static org.junit.jupiter.api.Assertions.*;
import java.io.*;
import org.junit.jupiter.api.Test;
import java.util.Scanner;

public class TwoDimRaggedArrayUtilityTestStudent {
	//testing data
	private double[][] dataSet2 = {{7,2,9,4},{5},{8,1,3},{11,6,7,2}};
	private double[][] dataSet3 = {{7.2, 2.5, 9.3, 4.8},{5.9},{8.1,1.7,3.3},{11.6,6.9,7.3,2.7}};
	
	//this method tests if the readFile function is working properly
	@Test
	public void testReadFile() throws FileNotFoundException {
		File file = new File("src/dataSet3.txt"); //file to be read from (using my path)
		
		for (int row = 0; row < dataSet3.length; row++) { //comparing each entry in the actual dataSet3 ragged array of doubles to what got returned from reading data set 3 txt
			for (int col = 0; col < dataSet3[row].length; col++) {
				assertEquals(dataSet3[row][col], TwoDimRaggedArrayUtility.readFile(file)[row][col]);
			}
		}
	}
	
	//this method tests the writing to the file method and checks if it is working properly
	@Test
	public void testWriteToFile() throws FileNotFoundException {
		File file = new File("src/output.txt"); //I am writing data set 3 to this file
		File file2 = new File("src/dataSet3.txt"); //I am using this file to check if the contents in the output match exactly how they should appear based on the dataSet3.txt
		
		TwoDimRaggedArrayUtility.writeToFile(dataSet3, file); //writing all the data in the ragged array to output.txt
		
		//checking the output format of the output.txt file to make sure it is correct
		//It should be the same exact contents as the dataSet3.txt file
		
		Scanner inputFile = new Scanner(file);
		Scanner inputFile2 = new Scanner(file2);
		//reading from both files to compare line by line
		
		String[] inputFileLines = new String[4];
		String[] inputFile2Lines = new String[4];
		//these are the string arrays to compare each line of each file
		
		int i = 0; //index variable
		//this while loop runs until both files have been fully read and stored into the respective arrays line by line 
		while(inputFile.hasNextLine() && inputFile2.hasNextLine()) {
			inputFileLines[i] = inputFile.nextLine();
			inputFile2Lines[i] = inputFile2.nextLine();
			i++;
		}
		
		for (int row = 0; row < 4; row++) { //4 rows in each file
			if (!inputFileLines[row].equals(inputFile2Lines[row])) { //if the rows are not the same (incorrect file writing)
				System.out.println("INVALID FILE WRITING"); //this will only print if the write to function didn't work properly/the lines don't equal each other
				break;
			}
		}
		
		//nothing was printed, so the file output.txt is formatted correctly 
	}
	
	//below I am testing all the remaining public methods of the class with dataSet2 (highest in col/row, testing totals, average, etc)
	@Test
	public void testGetTotal() {
		assertEquals(65.0, TwoDimRaggedArrayUtility.getTotal(dataSet2));
	}
	
	@Test 
	public void testGetAverage() {
		assertEquals(5.416, TwoDimRaggedArrayUtility.getAverage(dataSet2), .001);
	}
	
	@Test
	public void testGetRowTotal() {
		assertEquals(5.0, TwoDimRaggedArrayUtility.getRowTotal(dataSet2, 1));
		assertEquals(26.0, TwoDimRaggedArrayUtility.getRowTotal(dataSet2, 3));
	}
	
	@Test
	public void testGetColumnTotal() {
		assertEquals(9.0, TwoDimRaggedArrayUtility.getColumnTotal(dataSet2, 1));
		assertEquals(6.0, TwoDimRaggedArrayUtility.getColumnTotal(dataSet2, 3));
	}
	
	@Test 
	public void testGetHighestInRow() {
		assertEquals(5.0, TwoDimRaggedArrayUtility.getHighestInRow(dataSet2, 1));
		assertEquals(9.0, TwoDimRaggedArrayUtility.getHighestInRow(dataSet2, 0));
	}
	
	@Test 
	public void testGetHighestInRowIndex() {
		assertEquals(0, TwoDimRaggedArrayUtility.getHighestInRowIndex(dataSet2, 1));
		assertEquals(2, TwoDimRaggedArrayUtility.getHighestInRowIndex(dataSet2, 0));
	}
	
	@Test 
	public void testGetLowestInRow() {
		assertEquals(2.0, TwoDimRaggedArrayUtility.getLowestInRow(dataSet2, 3));
		assertEquals(5.0, TwoDimRaggedArrayUtility.getLowestInRow(dataSet2, 1));
	}
	
	@Test 
	public void testGetLowestInRowIndex() {
		assertEquals(3, TwoDimRaggedArrayUtility.getLowestInRowIndex(dataSet2, 3));
		assertEquals(0, TwoDimRaggedArrayUtility.getLowestInRowIndex(dataSet2, 1));
	}
	
	@Test 
	public void testGetHighestInColumn() {
		assertEquals(11.0, TwoDimRaggedArrayUtility.getHighestInColumn(dataSet2, 0));
		assertEquals(9.0, TwoDimRaggedArrayUtility.getHighestInColumn(dataSet2, 2));
	}
	
	@Test 
	public void testGetHighestInColumnIndex() {
		assertEquals(3, TwoDimRaggedArrayUtility.getHighestInColumnIndex(dataSet2, 0));
		assertEquals(0, TwoDimRaggedArrayUtility.getHighestInColumnIndex(dataSet2, 2));
	}
	
	@Test 
	public void testGetLowestInColumn() {
		assertEquals(5.0, TwoDimRaggedArrayUtility.getLowestInColumn(dataSet2, 0));
		assertEquals(3.0, TwoDimRaggedArrayUtility.getLowestInColumn(dataSet2, 2));
	}
	
	@Test 
	public void testGetLowestInColumnIndex() {
		assertEquals(1, TwoDimRaggedArrayUtility.getLowestInColumnIndex(dataSet2, 0));
		assertEquals(2, TwoDimRaggedArrayUtility.getLowestInColumnIndex(dataSet2, 2));
	}
	
	@Test 
	public void testGetHighestInArray() {
		assertEquals(11.0, TwoDimRaggedArrayUtility.getHighestInArray(dataSet2));
	}
	
	@Test 
	public void testGetLowestInArray() {
		assertEquals(1.0, TwoDimRaggedArrayUtility.getLowestInArray(dataSet2));
	}
}